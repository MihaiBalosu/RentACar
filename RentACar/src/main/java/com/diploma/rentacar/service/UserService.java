package com.diploma.rentacar.service;

import com.diploma.rentacar.dto.UserDto;
import com.diploma.rentacar.entity.User;
import com.diploma.rentacar.enums.RoleEnum;
import com.diploma.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder  passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User create(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();

        if (user.getRole() == null){
            user.setRole(RoleEnum.USER);
        }

        return userRepository.save(user);
    }
}
