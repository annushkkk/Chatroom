package ru.relex.solution.chatroom.service.model.useraccount;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.solution.chatroom.service.validation.constraint.EmailUnique;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameUnique;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String password;
    @NotNull
    @Email
    @EmailUnique
    private String email;
    @NotNull
    @NicknameUnique
    private String nickname;
    private String firstName;
    private String lastName;
}
