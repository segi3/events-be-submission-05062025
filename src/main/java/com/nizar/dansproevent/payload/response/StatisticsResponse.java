package com.nizar.dansproevent.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {
    private long totalEvents;
    private long totalRegistrations;
    private double averageRegistrationsPerEvent;
    private List<EventResponse> topThreeEvents;
}
