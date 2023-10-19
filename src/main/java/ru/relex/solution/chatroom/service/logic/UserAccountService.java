package ru.relex.solution.chatroom.service.logic;

import ru.relex.solution.chatroom.service.model.useraccount.DeleteResponse;
import ru.relex.solution.chatroom.service.model.useraccount.RegisterResponse;
import ru.relex.solution.chatroom.service.model.useraccount.UserAccountDto;
import ru.relex.solution.chatroom.service.model.useraccount.UserInfo;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordRequest;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordResponse;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccRequest;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccResponse;

public interface UserAccountService {
    RegisterResponse register(UserAccountDto userAccountDto);
    DeleteResponse deleteAcc(String nickname);
    UserInfo update(UserInfo userInfo);
    ChangePasswordResponse updatePassword(ChangePasswordRequest request);
    RecoverUserAccResponse recoverUserAcc(RecoverUserAccRequest request);
}

