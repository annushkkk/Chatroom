package ru.relex.solution.chatroom.service.logic;

import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.entity.DeactivatedToken;

import java.util.UUID;

public interface DeactivatedTokenService {
    Boolean isExist(UUID id);
    Boolean create(DeactivatedToken token);
}
