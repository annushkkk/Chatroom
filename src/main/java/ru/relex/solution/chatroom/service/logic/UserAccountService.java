package ru.relex.solution.chatroom.service.logic;

import ru.relex.solution.chatroom.service.model.DeleteResponse;
import ru.relex.solution.chatroom.service.model.RegisterResponse;
import ru.relex.solution.chatroom.service.model.UserAccountDto;
import ru.relex.solution.chatroom.service.model.UserInfo;

public interface UserAccountService {
    RegisterResponse register(UserAccountDto userAccountDto);
    DeleteResponse deleteAcc();
    UserInfo update(UserInfo userInfo);
}
