package com.diploma.emailsender.service;

import com.diploma.emailsender.dto.EmailPropertiesDto;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {

    private final TemplateService templateService;
    private final JavaMailSender javaMailSender;


    @Autowired
    public EmailService(TemplateService templateService,
                        JavaMailSender javaMailSender) {
        this.templateService = templateService;
        this.javaMailSender = javaMailSender;
    }

    public String generateEmailBody(String templateName, Map<String, String> placeholders) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty("eventhandler.referenceinsertion.class",
                "org.apache.velocity.app.event.implement.EscapeXmlReference");

        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }

        VelocityContext velocityContext = new VelocityContext(context);
        velocityEngine.init();

        String path = templateService.constructTemplatePath(templateName);
        Template template = velocityEngine.getTemplate(path, StandardCharsets.UTF_8.name());

        StringWriter writer = new StringWriter();

        template.merge(velocityContext, writer);

        return writer.toString();
    }

    public void sendEmail(String emailBody, EmailPropertiesDto emailPropertiesDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailPropertiesDto.getTo());
        message.setSubject(emailPropertiesDto.getSubject());
        message.setText(emailBody);
        message.setFrom(emailPropertiesDto.getFrom());

        javaMailSender.send(message);
    }

}
