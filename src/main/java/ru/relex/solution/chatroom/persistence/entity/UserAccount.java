package ru.relex.solution.chatroom.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.solution.chatroom.service.model.Role;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_accounts", schema = "chatroom_base")
public class UserAccount {
    @Id
    private UUID id;
    private String password;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
    private Boolean active;
    private List<Role> authorities;

}
