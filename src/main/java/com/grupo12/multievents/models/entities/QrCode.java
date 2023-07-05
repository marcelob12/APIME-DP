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
@ToString(exclude = {"ticket"})
@Entity
@Table(name = "qr_codes")
public class QrCode {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "qr")
    private String qr;

    @Column(name = "date_generated")
    private Date generatedDate;

    @Column(name = "date_claimed")
    private Date claimedDate;

    @Column(name = "date_expired")
    private Date expiredDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    @JsonIgnore
    private Ticket ticket;

    public QrCode(String qr, Date generatedDate, Date claimedDate, Date expiredDate, Ticket ticket) {
        this.qr = qr;
        this.generatedDate = generatedDate;
        this.claimedDate = claimedDate;
        this.expiredDate = expiredDate;
        this.ticket = ticket;
    }
}
