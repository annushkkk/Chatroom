package ru.relex.solution.chatroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.relex.solution.chatroom.service.logic.UserAccountService;
import ru.relex.solution.chatroom.service.model.DeleteResponse;
import ru.relex.solution.chatroom.service.model.RegisterResponse;
import ru.relex.solution.chatroom.service.model.UserAccountDto;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService service;


    @PostMapping
    public RegisterResponse register(@RequestBody UserAccountDto userAccountDto) {
        return service.register(userAccountDto);
    }
    @DeleteMapping
    public DeleteResponse deleteAcc(){
        return service.deleteAcc();
    }
    @GetMapping
    public String test(){

        return "eeeeeeeeeeeeeeeeeeeeeeeeeeeee";
    }
}
