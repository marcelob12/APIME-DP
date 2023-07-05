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
@ToString(exclude = {"accessList"})
@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPrivilege;

    @Column(name = "name")
    private String name;

    @Column(name = "route")
    private String route;

    @OneToMany(mappedBy = "privilege", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Access> accessList;

    public Privilege(String name, String route) {
        this.name = name;
        this.route = route;
    }
}
