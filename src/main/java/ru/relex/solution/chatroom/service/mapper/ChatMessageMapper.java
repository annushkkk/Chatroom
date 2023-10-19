package ru.relex.solution.chatroom.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.relex.solution.chatroom.persistence.entity.ChatMessage;
import ru.relex.solution.chatroom.service.model.chat.ChatMessageDto;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {

    ChatMessageDto fromEntity(ChatMessage chatMessage);
    @Mapping(target = "id", ignore = true)
    ChatMessage toEntity(ChatMessageDto dto);
}
