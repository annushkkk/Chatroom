package ru.relex.solution.chatroom.service.logic.impl;

import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.entity.DeactivatedToken;
import ru.relex.solution.chatroom.service.logic.DeactivatedTokenService;

import java.util.UUID;
@Service
public class DeactivatedTokenServiceImpl implements DeactivatedTokenService {
    @Override
    public Boolean isExist(UUID id) {
        return null;
    }

    @Override
    public Boolean create(DeactivatedToken token) {
        return null;
    }
}
