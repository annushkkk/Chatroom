package ru.relex.solution.chatroom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.solution.chatroom.service.logic.VerificationTokenService;
import ru.relex.solution.chatroom.service.model.VerificationResponse;

@RestController
@RequestMapping(path = "/api/tokens")
@RequiredArgsConstructor
public class VerificationController {

  private final VerificationTokenService service;

  @GetMapping("/verify")
  public VerificationResponse verifyEmail(@RequestParam("token") String token) {
    return service.verifyToken(token);
  }
}