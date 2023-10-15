package ru.relex.solution.chatroom.security;

import org.springframework.security.core.Authentication;
import ru.relex.solution.chatroom.service.model.Token;

public interface TokenCookieFactory {
    Token create(Authentication authentication);
}
