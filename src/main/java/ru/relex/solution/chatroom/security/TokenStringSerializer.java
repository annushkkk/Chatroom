package ru.relex.solution.chatroom.security;

import ru.relex.solution.chatroom.service.model.Token;

public interface TokenStringSerializer {
    String serialize(Token token);
}
