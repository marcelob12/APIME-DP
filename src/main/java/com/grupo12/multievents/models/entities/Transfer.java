package com.grupo12.multievents.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "init_date")
    private Date initDate;

    @Column(name = "transfer_date")
    private Date transferDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_from")
    private User userFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_to")
    private User userTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Transfer(Date initDate, Date transferDate, User userFrom, User userTo, Ticket ticket) {
        this.initDate = initDate;
        this.transferDate = transferDate;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.ticket = ticket;
    }
}
