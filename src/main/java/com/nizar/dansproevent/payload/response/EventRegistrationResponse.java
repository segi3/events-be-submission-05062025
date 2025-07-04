package com.nizar.dansproevent.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRegistrationResponse {
    private Long id;
    private String userEmail;
    private String eventTitle;
    private String eventDescription;
    private LocalDateTime eventDate;
    private LocalDateTime registrationDate;
}
