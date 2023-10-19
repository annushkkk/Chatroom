package ru.relex.solution.chatroom.service.model.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistoryDto {
    private String recipientNickname;
    private Integer pageNumber;
    private Integer pageSize;

}
