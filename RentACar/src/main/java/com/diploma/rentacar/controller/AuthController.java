package com.diploma.rentacar.controller;

import com.diploma.rentacar.dto.UserDto;
import com.diploma.rentacar.entity.User;
import com.diploma.rentacar.enums.RoleEnum;
import com.diploma.rentacar.repository.UserRepository;
import com.diploma.rentacar.service.CustomUserDetailsService;
import com.diploma.rentacar.service.UserService;
import com.diploma.rentacar.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws ServletException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new ServletException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        User user = userService.findByUsername(authenticationRequest.getUsername());
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(new AuthenticationResponse(token, userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        SecurityContextHolder.clearContext();

        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout success");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        if(userDto.getRole() == null){
            userDto.setRole(RoleEnum.USER);
        }

        User user = userService.create(userDto);

        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(token, UserDto.entityToDto(user)));
        }

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationRequest {
    private String username;
    private String password;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AuthenticationResponse {
    private String token;
    private UserDto userDto;
}

