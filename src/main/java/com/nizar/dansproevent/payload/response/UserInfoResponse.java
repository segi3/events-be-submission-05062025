package com.nizar.dansproevent.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private String token;
    private Long id;
    private String name;
    private String email;
    private List<String> roles;
}
