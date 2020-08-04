package com.icefire.icefirebackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UserDto {

    private Long userId;
    private String username;
    private String email;
    private String password;
}

