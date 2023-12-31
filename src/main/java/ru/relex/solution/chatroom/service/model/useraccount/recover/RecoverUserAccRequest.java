package ru.relex.solution.chatroom.service.model.useraccount.recover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecoverUserAccRequest {
    private String password;
    private String nickname;
}
