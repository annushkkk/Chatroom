package ru.relex.solution.chatroom.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.solution.chatroom.persistence.entity.ChatMessage;

import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
}
