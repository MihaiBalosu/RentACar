package com.diploma.rentacar.dto;

import com.diploma.rentacar.entity.User;
import com.diploma.rentacar.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private RoleEnum role;

    private String email;

    public static UserDto entityToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }
}
