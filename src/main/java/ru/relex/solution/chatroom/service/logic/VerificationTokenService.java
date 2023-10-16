package ru.relex.solution.chatroom.service.logic;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.service.model.VerificationResponse;

@Validated
public interface VerificationTokenService {

  String generateToken(@NotNull UserAccount userAccount);

  VerificationResponse verifyToken(@NotNull String token);
}