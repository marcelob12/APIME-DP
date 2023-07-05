package com.grupo12.multievents.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(exclude = {"event"})
@Entity
@Table(name = "tiers")
public class Tier {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "price")
    private double price;

    @Column(name = "visibility")
    private boolean visibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    public Tier(String name, int capacity, double price, Event event) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.event = event;
    }

    public String getEventTitle() {
        return event.getTitle();
    }
    public String getLocation() {
        return event.getLocation();
    }
    public Date getEvenDate() {
        return event.getDate();
    }
    public String getImage(){
        return event.getImage();
    }
}
