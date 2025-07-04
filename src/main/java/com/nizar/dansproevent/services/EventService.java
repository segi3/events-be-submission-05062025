package com.nizar.dansproevent.services;

import com.nizar.dansproevent.exception.BusinessLogicException;
import com.nizar.dansproevent.models.Event;
import com.nizar.dansproevent.models.EventRegistration;
import com.nizar.dansproevent.models.User;
import com.nizar.dansproevent.payload.response.EventRegistrationResponse;
import com.nizar.dansproevent.repositories.EventRegistrationRepository;
import com.nizar.dansproevent.repositories.EventRepository;
import com.nizar.dansproevent.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Event> findByTitle(String title){
        return eventRepository.findByTitle(title);
    }

    public List<Event> findByTitleContaining(String title){
        return eventRepository.findByTitleContaining(title);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public EventRegistrationResponse registerUserForEvent(Long eventId, Long userId, LocalDateTime registrationDate) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BusinessLogicException("Event not found with id: " + eventId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException("User not found with id: " + userId));

        // check existing registration
        if (eventRegistrationRepository.existsByUserAndEvent(user, event)) {
            throw new BusinessLogicException("User is already registered for this event");
        }

        EventRegistration registration = new EventRegistration(user, event,
                registrationDate != null ? registrationDate : LocalDateTime.now());

        EventRegistration savedRegistration = eventRegistrationRepository.save(registration);

        return mapToResponse(savedRegistration);
    }



    private EventRegistrationResponse mapToResponse(EventRegistration registration) {
        return new EventRegistrationResponse(
                registration.getId(),
                registration.getUser().getEmail(),
                registration.getEvent().getTitle(),
                registration.getEvent().getDescription(),
                registration.getEvent().getDate(),
                registration.getRegistrationDate()
        );
    }
}
