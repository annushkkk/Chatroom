package ru.relex.solution.chatroom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.service.logic.ChatMessageService;
import ru.relex.solution.chatroom.service.model.ChatMessageDto;

@Controller
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;
    private ChatMessageService chatMessageService;
    private UserAccountRepository userAccountRepository;
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessage) {
        boolean saved = chatMessageService.create(chatMessage);
        UserAccount recipient= userAccountRepository.getByNickname(chatMessage.getRecipientNickname());
        messagingTemplate.convertAndSendToUser(
                recipient.getId().toString(),"/queue/messages",
                chatMessage);
    }
}