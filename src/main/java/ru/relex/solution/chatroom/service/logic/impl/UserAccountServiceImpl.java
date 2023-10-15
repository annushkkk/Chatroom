package ru.relex.solution.chatroom.service.logic.impl;

import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.service.logic.UserAccountService;
import ru.relex.solution.chatroom.service.model.DeleteResponse;
import ru.relex.solution.chatroom.service.model.RegisterResponse;
import ru.relex.solution.chatroom.service.model.UserAccountDto;
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Override
    public RegisterResponse register(UserAccountDto userAccountDto) {
        return null;
    }

    @Override
    public DeleteResponse deleteAcc() {
        return null;
    }
}
