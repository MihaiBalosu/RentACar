package com.diploma.rentacar.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailPropertiesDto {

    private String from;
    private String to;
    private String subject;
    private String emailTemplate;
    private Map<String, String> placeholders;
}
