package ru.relex.solution.chatroom.security.authfilter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.relex.solution.chatroom.service.model.Token;

import java.util.function.Function;
import java.util.stream.Stream;

@AllArgsConstructor
public class TokenCookieAuthenticationConverter implements AuthenticationConverter {
    private final Function<String, Token> tokenCookieStringDeserializer;
    @Override
    public Authentication convert(HttpServletRequest request) {
        if (request.getCookies()!= null){
            return Stream.of(request.getCookies())
                    .filter(cookie->cookie.getName().equals("__Host-auth-token"))
                    .findFirst()
                    .map(cookie -> {
                        var token = this.tokenCookieStringDeserializer.apply(cookie.getValue());
                        return new PreAuthenticatedAuthenticationToken(token,cookie.getValue());
                            }).orElse(null);
        }
        return null;
    }
}
