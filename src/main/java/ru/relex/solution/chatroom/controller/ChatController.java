package ru.relex.solution.chatroom.controller;

import lombok.AllArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.service.logic.ChatMessageService;
import ru.relex.solution.chatroom.service.model.chat.ChatHistoryDto;
import ru.relex.solution.chatroom.service.model.chat.ChatMessageDto;

import java.util.Objects;

@AllArgsConstructor
@Controller
public class ChatController {

    private SimpMessagingTemplate messagingTemplate;
    private ChatMessageService chatMessageService;
    private UserAccountRepository userAccountRepository;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        Authentication authentication = (Authentication) headerAccessor.getUser();
        if (authentication != null) {
            String nickname = authentication.getName();
            if (Objects.equals(nickname, chatMessage.getSenderNickname())) {
                chatMessageService.create(chatMessage);
                UserAccount recipient = userAccountRepository.getByNickname(chatMessage.getRecipientNickname());
                messagingTemplate.convertAndSendToUser(
                        recipient.getId().toString(), "/queue/messages",
                        chatMessage);
            }
        }

    }

    @MessageMapping("/history")
    public void getChatHistory(@Payload ChatHistoryDto chatHistoryDto, SimpMessageHeaderAccessor headerAccessor) {
        Authentication authentication = (Authentication) headerAccessor.getUser();
        if (authentication!=null) {
            String nickname = authentication.getName();
            UserAccount userAccount = userAccountRepository.getByNickname(nickname);
            messagingTemplate.convertAndSendToUser(
                    userAccount.getId().toString(), "/queue/messages",
                    chatMessageService.getChatHistory(chatHistoryDto, userAccount.getNickname()));
        }
    }
}