package ru.relex.solution.chatroom.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import ru.relex.solution.chatroom.service.model.Role;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_accounts", schema = "chatroom_base")
public class UserAccount implements UserDetails {
    @Id
    private UUID id;
    private String password;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
    private Boolean active;
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
