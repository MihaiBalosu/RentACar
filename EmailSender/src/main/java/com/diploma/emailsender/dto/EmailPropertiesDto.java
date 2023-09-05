package com.diploma.emailsender.dto;

import lombok.Data;

import java.util.Map;

@Data
public class EmailPropertiesDto {

    private String from;
    private String to;
    private String subject;
    private String emailTemplate;
    private Map<String, String> placeholders;
}
