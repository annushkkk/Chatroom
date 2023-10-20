package ru.relex.solution.chatroom.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.relex.solution.chatroom.security.authfilter.TokenCookieAuthenticationConfigurer;
import ru.relex.solution.chatroom.service.model.Token;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class TokenCookieFactoryImpl implements TokenCookieFactory{

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieFactoryImpl.class);
    @Value("${jwt.token-days-to-live}")
    private String tokenDaysToLive;
    @Override
    public Token create(Authentication authentication) {
        var now= Instant.now();
        return new Token(UUID.randomUUID(), authentication.getName(),
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList(),now,now.plus(Duration.ofDays(Long.parseLong(this.tokenDaysToLive))));
    }
}
