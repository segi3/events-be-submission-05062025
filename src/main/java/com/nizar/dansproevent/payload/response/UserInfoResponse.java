package com.nizar.dansproevent.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class UserInfoResponse {
    private String token;
    private Long id;
    private String name;
    private String email;
    private List<String> roles;
}
