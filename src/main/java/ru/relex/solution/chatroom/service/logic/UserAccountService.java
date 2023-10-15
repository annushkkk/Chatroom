package ru.relex.solution.chatroom.service.logic;

import ru.relex.solution.chatroom.service.model.DeleteResponse;
import ru.relex.solution.chatroom.service.model.RegisterResponse;
import ru.relex.solution.chatroom.service.model.UserAccountDto;

public interface UserAccountService {
    RegisterResponse register(UserAccountDto userAccountDto);
    DeleteResponse deleteAcc();
}
