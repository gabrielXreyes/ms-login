package com.ms_login.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Getter
@Setter
@Builder
@Entity
@Table(name="USERS")
@AllArgsConstructor //Crea los constructors
@NoArgsConstructor
public class User implements UserDetails {
    @Getter
    @Id
    @Column(name="ID",  columnDefinition="NUMBER", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name="USERNAME", columnDefinition = "VARCHAR2", nullable=false)
    private String username;

    @Setter
    @Column(name="PASSWORD", columnDefinition = "VARCHAR2", nullable=false)
    private String password;

    @Column(name="EMAIL", columnDefinition = "VARCHAR2", nullable=false)
    private String email;

    @Column(name="ROLE", columnDefinition = "NUMBER", nullable=false)
    private int role;

    @CreationTimestamp
    @Column(name="REGISTER_DATE", columnDefinition = "DATE")
    private LocalDate registerdate;

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE", columnDefinition = "DATE")
    private LocalDate update;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
