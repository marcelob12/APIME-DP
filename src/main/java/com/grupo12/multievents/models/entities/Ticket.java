package com.grupo12.multievents.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(exclude = {"transfers", "order"})
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tier_id")
    private Tier tier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<QrCode> qrCodes;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transfer> transfers;

    public Ticket(Tier tier, Order order) {
        this.tier = tier;
        this.order = order;
    }
}
