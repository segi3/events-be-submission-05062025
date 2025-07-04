package com.nizar.dansproevent.payload.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.nizar.dansproevent.models.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreateRequest {
    private Long id;
    private String title;
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime date;
}
