package ru.relex.solution.chatroom.security.authfilter;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.csrf.CsrfFilter;
import ru.relex.solution.chatroom.persistence.entity.DeactivatedToken;
import ru.relex.solution.chatroom.service.logic.DeactivatedTokenService;
import ru.relex.solution.chatroom.service.model.Token;

import java.util.Date;
import java.util.function.Function;

public class TokenCookieAuthenticationConfigurer extends AbstractHttpConfigurer<TokenCookieAuthenticationConfigurer, HttpSecurity> {
    private  Function<String, Token> tokenCookieStringDeserializer;
    private DeactivatedTokenService deactivatedTokenService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenCookieAuthenticationConfigurer.class);

    //private jdbc
    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.logout(logout-> logout.addLogoutHandler(new CookieClearingLogoutHandler("__Host-auth-token"))
                .addLogoutHandler(((request, response, authentication) -> {
                    LOGGER.info("LOOOOOOOOOOOgout");
                    if (authentication!=null &&
                            authentication.getPrincipal() instanceof Token token){
                        deactivatedTokenService.create(new DeactivatedToken(token.id(), Date.from(token.expiresAt())));
                        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    }
                })));
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {;

        var cookieAuthenticationFilter= new AuthenticationFilter(builder.getSharedObject(AuthenticationManager.class),
                new TokenCookieAuthenticationConverter(this.tokenCookieStringDeserializer));
        cookieAuthenticationFilter.setSuccessHandler(((request, response, authentication) -> {}));
        cookieAuthenticationFilter.setFailureHandler(new AuthenticationEntryPointFailureHandler(new Http403ForbiddenEntryPoint()));
        var authenticationProvider=new PreAuthenticatedAuthenticationProvider();
        //jdbc in service
        authenticationProvider.setPreAuthenticatedUserDetailsService(new TokenAuthenticationUserDetailsService());

        builder.addFilterAfter(cookieAuthenticationFilter, CsrfFilter.class)
                .authenticationProvider(authenticationProvider);
        LOGGER.info("CONFIGURATORRRRRRRRRRRRRRR");
    }
    public TokenCookieAuthenticationConfigurer tokenCookieStringDeserializer(
            Function<String, Token> tokenCookieStringDeserializer) {
        this.tokenCookieStringDeserializer = tokenCookieStringDeserializer;
        return this;
    }

//    public JwtAuthenticationConfigurer jdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//        return this;
//    }
}
