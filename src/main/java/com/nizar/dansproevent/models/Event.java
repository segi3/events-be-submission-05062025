package com.nizar.dansproevent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String title;

    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull
    @Column(name = "event_date")
    private LocalDateTime date;

    // fk with user (admin)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    // fk with user (user registration)
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EventRegistration> eventRegistrations = new HashSet<>();

    public Event(String title, String description, LocalDateTime date, User createdBy) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.createdBy = createdBy;
    }
}