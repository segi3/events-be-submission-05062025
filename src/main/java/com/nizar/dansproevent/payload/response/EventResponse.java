package com.nizar.dansproevent.payload.response;

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
    private long registrationCount;
}
