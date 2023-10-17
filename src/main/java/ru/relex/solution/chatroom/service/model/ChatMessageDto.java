package ru.relex.solution.chatroom.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private String senderNickname;
    private String recipientNickname;
    private String content;
    private Date timestamp;
}
