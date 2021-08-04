package com.fita.project.config.jwt;

import com.fita.project.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {
    private final String token;
    private UserDTO userDTO;
}
