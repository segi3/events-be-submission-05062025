package com.nizar.dansproevent.repositories;

import com.nizar.dansproevent.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByTitle(String title);

    List<Event> findByTitleContaining(String title);
}