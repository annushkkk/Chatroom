package ru.relex.solution.chatroom.security.authfilter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import ru.relex.solution.chatroom.persistence.entity.DeactivatedToken;
import ru.relex.solution.chatroom.persistence.repository.DeactivatedTokenRepository;
import ru.relex.solution.chatroom.service.model.Token;
import ru.relex.solution.chatroom.service.model.TokenUser;

import java.util.function.Function;

public class TokenCookieAuthenticationConfigurer extends AbstractHttpConfigurer<TokenCookieAuthenticationConfigurer, HttpSecurity> {
    private  Function<String, Token> tokenCookieStringDeserializer;
    private DeactivatedTokenRepository deactivatedTokenRepository;

    @Override
    public void init(HttpSecurity builder) throws Exception {
       builder.logout(logout -> logout
                .addLogoutHandler(new CookieClearingLogoutHandler("__Host-auth-token"))
                .addLogoutHandler((request, response, authentication) -> {
                    if (authentication != null && authentication.getPrincipal() instanceof TokenUser user) {
                        DeactivatedToken deactivatedToken = new DeactivatedToken();
                        deactivatedToken.setId(user.getToken().id());
                        deactivatedToken.setCKeepUntil(user.getToken().expiresAt());
                        deactivatedTokenRepository.save(deactivatedToken);

                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }})
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
        );
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {;
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        var cookieAuthenticationFilter = new AuthenticationFilter(
                authenticationManager,
                new TokenCookieAuthenticationConverter(this.tokenCookieStringDeserializer)
        );

        cookieAuthenticationFilter.setSuccessHandler((request, response, authentication) -> {});
        cookieAuthenticationFilter.setFailureHandler(
                new AuthenticationEntryPointFailureHandler(new Http403ForbiddenEntryPoint())
        );

        var preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(
                new TokenAuthenticationUserDetailsService(this.deactivatedTokenRepository)
        );

        builder
                .addFilterAfter(cookieAuthenticationFilter, CsrfFilter.class)
                .authenticationProvider(preAuthenticatedAuthenticationProvider);
    }
    public TokenCookieAuthenticationConfigurer tokenCookieStringDeserializer(
            Function<String, Token> tokenCookieStringDeserializer) {
        this.tokenCookieStringDeserializer = tokenCookieStringDeserializer;
        return this;
    }

    public TokenCookieAuthenticationConfigurer deactivatedTokenRepository(DeactivatedTokenRepository deactivatedTokenRepository) {
        this.deactivatedTokenRepository = deactivatedTokenRepository;
        return this;
    }
}
