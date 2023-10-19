package ru.relex.solution.chatroom.persistence.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.solution.chatroom.persistence.entity.ChatMessage;

import java.util.List;
import java.util.UUID;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
    List<ChatMessage> findNextPageByRecipientNicknameAndSenderNicknameOrderByTimestampDesc(String recipientNickname,String senderNickname , Pageable pageable);
}
