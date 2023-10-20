package ru.relex.solution.chatroom.service.logic;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import ru.relex.solution.chatroom.service.model.useraccount.DeleteResponse;
import ru.relex.solution.chatroom.service.model.useraccount.RegisterResponse;
import ru.relex.solution.chatroom.service.model.useraccount.UserAccountDto;
import ru.relex.solution.chatroom.service.model.useraccount.UserInfo;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordRequest;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordResponse;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccRequest;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccResponse;
@Validated
public interface UserAccountService {
    RegisterResponse register(@Valid UserAccountDto userAccountDto);
    DeleteResponse deleteAcc(String nickname);
    UserInfo update(@Valid UserInfo userInfo,String nickname);
    ChangePasswordResponse updatePassword(ChangePasswordRequest request);
    RecoverUserAccResponse recoverUserAcc(RecoverUserAccRequest request);
}

