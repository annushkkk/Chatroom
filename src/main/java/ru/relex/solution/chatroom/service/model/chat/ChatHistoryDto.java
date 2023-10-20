package ru.relex.solution.chatroom.service.model.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameExist;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistoryDto {
    @NicknameExist
    private String recipientNickname;
    private Integer pageNumber;
    private Integer pageSize;

}
