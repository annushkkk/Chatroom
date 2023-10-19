package ru.relex.solution.chatroom.service.logic;

import ru.relex.solution.chatroom.service.model.chat.ChatHistoryDto;
import ru.relex.solution.chatroom.service.model.chat.ChatMessageDto;

import java.util.List;

public interface ChatMessageService {
    boolean create(ChatMessageDto chatMessageDto);

    List<ChatMessageDto> getChatHistory(ChatHistoryDto chatHistoryDto,String requestingUserNickName);
}
