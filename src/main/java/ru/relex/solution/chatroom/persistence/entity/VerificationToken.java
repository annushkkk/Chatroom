package ru.relex.solution.chatroom.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "verification_tokens", schema = "chatroom_base")
public class VerificationToken {

  @Id
  private String token;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserAccount user;
}