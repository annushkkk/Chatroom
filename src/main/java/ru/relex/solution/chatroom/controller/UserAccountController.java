package ru.relex.solution.chatroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.relex.solution.chatroom.service.logic.UserAccountService;
import ru.relex.solution.chatroom.service.model.TokenUser;
import ru.relex.solution.chatroom.service.model.useraccount.DeleteResponse;
import ru.relex.solution.chatroom.service.model.useraccount.RegisterResponse;
import ru.relex.solution.chatroom.service.model.useraccount.UserAccountDto;
import ru.relex.solution.chatroom.service.model.useraccount.UserInfo;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordRequest;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordResponse;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccRequest;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccResponse;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
@Validated
public class UserAccountController {
    private final UserAccountService service;


    @PostMapping
    public RegisterResponse register(@RequestBody @Valid UserAccountDto userAccountDto) {
        return service.register(userAccountDto);
    }
    @DeleteMapping
    public DeleteResponse deleteAcc(@AuthenticationPrincipal Object principal){
        TokenUser user= (TokenUser) principal;
        return service.deleteAcc(user.getUsername());
    }
    @PutMapping
    public UserInfo update(@RequestBody UserInfo userInfo){
        return service.update(userInfo);
    }
    @PutMapping(value ="/upd_password")
    public ChangePasswordResponse updatePassword(@RequestBody ChangePasswordRequest request){
        return service.updatePassword(request);
    }
    @PostMapping(value="/recover_account")
    public RecoverUserAccResponse recoverUserAcc(@RequestBody RecoverUserAccRequest request){
        return service.recoverUserAcc(request);
    }
    @GetMapping
    public String test(){
        return "test";
    }
}
