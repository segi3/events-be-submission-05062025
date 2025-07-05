package com.nizar.dansproevent.controllers;

import com.nizar.dansproevent.exception.BusinessLogicException;
import com.nizar.dansproevent.models.Event;
import com.nizar.dansproevent.models.User;
import com.nizar.dansproevent.payload.request.EventCreateRequest;
import com.nizar.dansproevent.payload.response.EventRegistrationResponse;
import com.nizar.dansproevent.payload.response.EventResponse;
import com.nizar.dansproevent.payload.response.MessageResponse;
import com.nizar.dansproevent.payload.response.StatisticsResponse;
import com.nizar.dansproevent.services.EventService;
import com.nizar.dansproevent.services.UserDetailsImpl;
import com.nizar.dansproevent.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @PostMapping("events/{eventId}/register")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Register user to event", description = "Register user to event (User and Admin")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId) {

        LocalDateTime registrationDate = LocalDateTime.now();

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.findById(userDetails.getId());

        try {
            EventRegistrationResponse response = eventService.registerUserForEvent(
                    eventId, user.get().getId(), registrationDate
            );

            return ResponseEntity.ok(new MessageResponse("User registered to event successfully.", response));
        } catch (BusinessLogicException e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponse("Error occurred during registration"));
        }
    }

    @GetMapping("/events/stats")
    public ResponseEntity<?> getOverallStatistics() {
        try {
            StatisticsResponse stats = eventService.getOverallStatistics();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponse("Error occurred while fetching statistics", e.getMessage()));
        }
    }

    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(@RequestParam(required = false) String title) {
        try {
            List<Event> events = new ArrayList<Event>();

            if (title == null) {
                eventService.findAll().forEach(events::add);
            } else {
                eventService.findByTitleContaining(title).forEach(events::add);
            }

            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<EventResponse> response = events.stream()
                    .map(event -> EventResponse.builder()
                            .id(event.getId())
                            .title(event.getTitle())
                            .description(event.getDescription())
                            .date(event.getDate())
                            .build())
                    .toList();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponse> getByEventId(@PathVariable("id") long id) {
        Optional<Event> eventOptional = eventService.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return new ResponseEntity<>(EventResponse.builder()
                    .id(event.getId())
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .date(event.getDate())
                    .build()
                    , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new event", description = "Creates a new event (Admin only)")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createEvent(@Valid @RequestBody EventCreateRequest event) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> admin = userService.findById(userDetails.getId());

        try {
            Event _event = eventService.save(new Event(
                    event.getTitle(),
                    event.getDescription(),
                    event.getDate(),
                    admin.get()
            ));

            return new ResponseEntity<>(EventResponse.builder()
                    .id(_event.getId())
                    .title(_event.getTitle())
                    .description(_event.getDescription())
                    .date(_event.getDate())
                    .build()
                    , HttpStatus.OK);

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body(new MessageResponse("Failed to create event", e.getMessage()));
        }
    }
}
