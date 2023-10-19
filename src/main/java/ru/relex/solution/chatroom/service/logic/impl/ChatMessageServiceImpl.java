package ru.relex.solution.chatroom.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.entity.ChatMessage;
import ru.relex.solution.chatroom.persistence.repository.ChatMessageRepository;
import ru.relex.solution.chatroom.service.logic.ChatMessageService;
import ru.relex.solution.chatroom.service.mapper.ChatMessageMapper;
import ru.relex.solution.chatroom.service.model.chat.ChatHistoryDto;
import ru.relex.solution.chatroom.service.model.chat.ChatMessageDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    @Override
    public boolean create(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage=chatMessageMapper.toEntity(chatMessageDto);
        chatMessageRepository.save(chatMessage);
        return true;
    }

    @Override
    public List<ChatMessageDto> getChatHistory(ChatHistoryDto chatHistoryDto, String requestingUserNickName) {
        return chatMessageRepository.findNextPageByRecipientNicknameAndSenderNicknameOrderByTimestampDesc(
                chatHistoryDto.getRecipientNickname(), requestingUserNickName,
                PageRequest.of(chatHistoryDto.getPageNumber(), chatHistoryDto.getPageSize()))
                .stream()
                .map(chatMessageMapper::fromEntity)
                .toList();


    }
}
