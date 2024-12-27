package com.example.hireease.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Username can not be null")
    @Pattern(regexp = "^[a-z]{4,15}$", message = "Invalid username")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password can not be null")
//    @Pattern(regexp = "^[a-zA-Z0-9.,:; _?!+=/'\\\\\"*(){}\\[\\]\\-]{6,}$", message = "Invalid password")
    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Roles roles;

    @Column(name = "activation_code", unique = true)
    private String activationCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
