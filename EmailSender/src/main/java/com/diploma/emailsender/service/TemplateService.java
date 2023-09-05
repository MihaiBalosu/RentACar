package com.diploma.emailsender.service;

import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private static final String TEMPLATES_FOLDER = "templates";

    public String constructTemplatePath(String templateName) {
        return TEMPLATES_FOLDER.concat("/").concat(templateName);
    }

}
