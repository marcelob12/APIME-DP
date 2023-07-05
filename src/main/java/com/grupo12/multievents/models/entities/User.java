package com.grupo12.multievents.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@ToString(exclude = {"tokens", "myTransfers", "receivedTransfers", "accessList", "orders"})
@Entity
@Table(name = "users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1460435087476558985L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Access> accessList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "userFrom", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transfer> myTransfers;

    @OneToMany(mappedBy = "userTo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transfer> receivedTransfers;

    @Column(name = "confirmed", insertable = false)
    private Boolean confirmed;

    @Column(name = "confirmation_code")
    @JsonIgnore
    private String confirmationCode;

    @Column(name = "active", insertable = false)
    private Boolean active;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Token> tokens;

    public User(String email, String username, String password, Avatar avatar, String confirmationCode) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.confirmationCode = confirmationCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
