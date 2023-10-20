package ru.relex.solution.chatroom.service.model.useraccount;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.relex.solution.chatroom.service.validation.constraint.EmailUnique;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameUnique;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String nickname;
    private String firstName;
    private String lastName;
}
