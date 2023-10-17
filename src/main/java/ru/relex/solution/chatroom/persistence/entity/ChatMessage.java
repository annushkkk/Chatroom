package ru.relex.solution.chatroom.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat_messages", schema = "chatroom_base")
public class ChatMessage {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   private String senderNickname;
   private String recipientNickname;
   private String content;
   private Date timestamp;
}