package com.nizar.dansproevent.payload.response;

import com.nizar.dansproevent.models.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private User user;
}
