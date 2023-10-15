package ru.relex.solution.chatroom.config;

import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import ru.relex.solution.chatroom.security.*;
import ru.relex.solution.chatroom.security.authfilter.TokenCookieAuthenticationConfigurer;
import ru.relex.solution.chatroom.security.csrf.GetCsrfTokenFilter;
import ru.relex.solution.chatroom.security.jwt.TokenCookieJweStringDeserializer;
import ru.relex.solution.chatroom.security.jwt.TokenCookieJweStringSerializer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer(
            @Value("${jwt.cookie-token-key}") String cookieTokenKey
    ) throws Exception {
        return new TokenCookieAuthenticationConfigurer()
                .tokenCookieStringDeserializer(new TokenCookieJweStringDeserializer(
                        new DirectDecrypter(OctetSequenceKey.parse(cookieTokenKey))));
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer,
            TokenCookieJweStringSerializer tokenCookieJweStringSerializer
    ) throws Exception {
        var tokenCookieSessionAuthenticationStrategy =new TokenCookieSessionAuthenticationStrategyImpl();
        tokenCookieSessionAuthenticationStrategy.setTokenStringSerializer(tokenCookieJweStringSerializer);
        http.apply(tokenCookieAuthenticationConfigurer);
//htmmmmmmmmlllllllllllllllllllllllllllllllllllllll
        return http
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .sessionAuthenticationStrategy(tokenCookieSessionAuthenticationStrategy))
                .addFilterAfter(new GetCsrfTokenFilter(), ExceptionTranslationFilter.class)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/manager.html").hasRole("MANAGER")
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated())
                .csrf(csrf->csrf.csrfTokenRepository((new CookieCsrfTokenRepository()))
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .sessionAuthenticationStrategy(((authentication, request, response) -> {})))
                .build();
    }
    @Bean
    public TokenCookieJweStringSerializer tokenCookieJweStringSerializer(@Value("${jwt.cookie-token-key}") String cookieTokenKey) throws Exception {
        return new TokenCookieJweStringSerializer(new DirectEncrypter(OctetSequenceKey.parse(cookieTokenKey)));

    }
}
