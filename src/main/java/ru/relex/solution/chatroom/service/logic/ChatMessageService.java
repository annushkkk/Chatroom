package ru.relex.solution.chatroom.service.logic;

import ru.relex.solution.chatroom.service.model.ChatMessageDto;

public interface ChatMessageService {
    boolean create(ChatMessageDto chatMessageDto);
}
