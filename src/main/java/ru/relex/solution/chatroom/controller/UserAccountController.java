package ru.relex.solution.chatroom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

public class UserAccountController {
    private final UserAccountService service;

    @PostMapping
    public RegisterResponse register(@RequestBody UserAccountDto userAccountDto) {
        return service.register(userAccountDto);
    }
    @Operation(security = {@SecurityRequirement(name = "token")})
    @DeleteMapping
    public DeleteResponse deleteAcc(@AuthenticationPrincipal TokenUser principal){
                return service.deleteAcc(principal.getUsername());
    }
    @Operation(security = {@SecurityRequirement(name = "token")})
    @PutMapping
    public UserInfo update(@AuthenticationPrincipal TokenUser principal,@RequestBody UserInfo userInfo){
        return service.update(userInfo,principal.getUsername());
    }
    @Operation(security = {@SecurityRequirement(name = "token")})
    @PutMapping(value ="/upd_password")
    public ChangePasswordResponse updatePassword(@RequestBody ChangePasswordRequest request){
        return service.updatePassword(request);
    }
    @Operation(security = {@SecurityRequirement(name = "token")})
    @PostMapping(value="/recover_account")
    public RecoverUserAccResponse recoverUserAcc(@RequestBody RecoverUserAccRequest request){
        return service.recoverUserAcc(request);
    }
    @Operation(security = {@SecurityRequirement(name = "token")})
    @GetMapping
    public String test(){
        return "test";
    }
}
