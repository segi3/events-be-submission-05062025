package com.nizar.dansproevent.repositories;

import com.nizar.dansproevent.models.Event;
import com.nizar.dansproevent.models.EventRegistration;
import com.nizar.dansproevent.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    List<EventRegistration> findByEvent(Event event);

    List<EventRegistration> findByUser(User user);

    Optional<EventRegistration> findByUserAndEvent(User user, Event event);

    boolean existsByUserAndEvent(User user, Event event);
}
