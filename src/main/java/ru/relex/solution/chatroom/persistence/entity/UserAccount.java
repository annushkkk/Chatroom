package ru.relex.solution.chatroom.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.relex.solution.chatroom.service.model.Role;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_accounts", schema = "chatroom_base")
public class UserAccount implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String password;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Instant deletedAt;
    @Enumerated(EnumType.STRING)
    private List<Role> authorities;

    @Override
    public String getUsername() {
        return nickname;
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
        return active;
    }
}
