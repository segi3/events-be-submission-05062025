package com.nizar.dansproevent.services;

import com.nizar.dansproevent.exception.BusinessLogicException;
import com.nizar.dansproevent.models.Event;
import com.nizar.dansproevent.models.EventRegistration;
import com.nizar.dansproevent.models.User;
import com.nizar.dansproevent.payload.response.EventRegistrationResponse;
import com.nizar.dansproevent.repositories.EventRegistrationRepository;
import com.nizar.dansproevent.repositories.EventRepository;
import com.nizar.dansproevent.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

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
