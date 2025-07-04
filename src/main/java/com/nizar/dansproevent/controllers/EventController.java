package com.nizar.dansproevent.controllers;

import com.nizar.dansproevent.exception.BusinessLogicException;
import com.nizar.dansproevent.models.Event;
import com.nizar.dansproevent.models.User;
import com.nizar.dansproevent.payload.request.EventCreateRequest;
import com.nizar.dansproevent.payload.response.EventRegistrationResponse;
import com.nizar.dansproevent.payload.response.EventResponse;
import com.nizar.dansproevent.payload.response.MessageResponse;
import com.nizar.dansproevent.repositories.EventRepository;
import com.nizar.dansproevent.repositories.UserRepository;
import com.nizar.dansproevent.services.EventRegistrationService;
import com.nizar.dansproevent.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EventRegistrationService eventRegistrationService;

    @PostMapping("events/{eventId}/register")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId) {

        LocalDateTime registrationDate = LocalDateTime.now();

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());

        try {
            EventRegistrationResponse response = eventRegistrationService.registerUserForEvent(
                    eventId, user.get().getId(), registrationDate
            );

            return ResponseEntity.ok(new MessageResponse("User registered successfully.", response));
        } catch (BusinessLogicException e) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MessageResponse("Error occurred during registration"));
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventResponse>> getAllEvents(@RequestParam(required = false) String title) {
        try {
            List<Event> events = new ArrayList<Event>();

            if (title == null) {
                eventRepository.findAll().forEach(events::add);
            } else {
                eventRepository.findByTitleContaining(title).forEach(events::add);
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
        Optional<Event> eventOptional = eventRepository.findById(id);

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
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventCreateRequest event) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> admin = userRepository.findById(userDetails.getId());

        try {
            Event _event = eventRepository.save(new Event(
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
                    .user(_event.getCreatedBy())
                    .build()
                    , HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
