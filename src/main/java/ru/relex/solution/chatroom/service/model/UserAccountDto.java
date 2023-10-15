package ru.relex.solution.chatroom.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {
    private String password;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
}
