package ru.relex.solution.chatroom.service.mapper;

import org.springframework.stereotype.Component;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.entity.VerificationToken;

@Component
public class VerificationTokenMapper {

  public VerificationToken toEntity(String verifyToken, UserAccount userAccount) {
    return VerificationToken.builder()
      .token(verifyToken)
      .user(userAccount)
      .build();
  }
}