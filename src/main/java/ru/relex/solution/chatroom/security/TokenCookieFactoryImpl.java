package ru.relex.solution.chatroom.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.relex.solution.chatroom.service.model.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class TokenCookieFactoryImpl implements TokenCookieFactory{
    //conf
    private Duration tokenTtl=Duration.ofDays(1);
    @Override
    public Token create(Authentication authentication) {
        var now= Instant.now();
        return new Token(UUID.randomUUID(), authentication.getName(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList(),now,now.plus(this.tokenTtl));
    }
}
