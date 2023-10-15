package ru.relex.solution.chatroom.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import ru.relex.solution.chatroom.security.jwt.TokenCookieFactory;
import ru.relex.solution.chatroom.security.jwt.TokenCookieFactoryImpl;
import ru.relex.solution.chatroom.service.model.Token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.function.Function;

@Setter

public class TokenCookieSessionAuthenticationStrategyImpl implements SessionAuthenticationStrategy {
    private TokenCookieFactory tokenCookieFactory = new TokenCookieFactoryImpl();
    private Function<Token, String> tokenStringSerializer = Objects::toString;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieSessionAuthenticationStrategyImpl.class);

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        LOGGER.info("strateeeeeegyyyyyyyyyyyyy");
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            var token = this.tokenCookieFactory.create(authentication);
            var tokenString = this.tokenStringSerializer.apply(token);
            var cookie = new Cookie("__Host-auth-token", tokenString);
            cookie.setPath("/");
            cookie.setDomain(null);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge((int) ChronoUnit.SECONDS.between(Instant.now(), token.expiresAt()));
            LOGGER.info("COOOOOOOOkie made");
            LOGGER.info(authentication.toString());
            LOGGER.info(tokenString);
            response.addCookie(cookie);
        }
    }
}
