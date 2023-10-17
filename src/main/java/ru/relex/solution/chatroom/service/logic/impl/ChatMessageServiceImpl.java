package ru.relex.solution.chatroom.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.repository.ChatMessageRepository;
import ru.relex.solution.chatroom.service.logic.ChatMessageService;
import ru.relex.solution.chatroom.service.model.ChatMessageDto;
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    @Override
    public boolean create(ChatMessageDto chatMessageDto) {
        return false;
    }
}
