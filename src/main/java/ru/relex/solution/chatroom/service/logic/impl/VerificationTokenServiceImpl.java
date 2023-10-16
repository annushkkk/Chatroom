package ru.relex.solution.chatroom.service.logic.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.repository.VerificationTokenRepository;
import ru.relex.solution.chatroom.service.logic.VerificationTokenService;
import ru.relex.solution.chatroom.service.mapper.VerificationTokenMapper;
import ru.relex.solution.chatroom.service.model.useraccount.VerificationResponse;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private final VerificationTokenRepository repository;

  private final VerificationTokenMapper mapper;

  @Override
  public String generateToken(UserAccount userAccount) {
    String verifyToken = RandomStringUtils.random(32, true, true);

    repository.save(mapper.toEntity(verifyToken, userAccount));

    return verifyToken;
  }

  @Override
  @Transactional
  public VerificationResponse verifyToken(String token) {
    UserAccount user = repository.findUserByToken(token)
      .orElseThrow(null);

    user.setActive(true);

    repository.deleteById(token);

    return new VerificationResponse();
  }
}