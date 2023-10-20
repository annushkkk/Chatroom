package ru.relex.solution.chatroom.service.model.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameExist;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    @NicknameExist
    private String senderNickname;
    @NicknameExist
    private String recipientNickname;
    private String content;
    private Date timestamp;
}
