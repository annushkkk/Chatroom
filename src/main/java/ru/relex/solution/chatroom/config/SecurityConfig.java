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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.repository.DeactivatedTokenRepository;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.security.*;
import ru.relex.solution.chatroom.security.authfilter.TokenCookieAuthenticationConfigurer;
import ru.relex.solution.chatroom.security.jwt.TokenCookieJweStringDeserializer;
import ru.relex.solution.chatroom.security.jwt.TokenCookieJweStringSerializer;
import ru.relex.solution.chatroom.service.model.Role;

import java.util.List;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer(
            @Value("${jwt.cookie-token-key}") String cookieTokenKey,
            DeactivatedTokenRepository deactivatedTokenRepository
            ) throws Exception {
        return new TokenCookieAuthenticationConfigurer()
                .deactivatedTokenRepository(deactivatedTokenRepository)
                .tokenCookieStringDeserializer(new TokenCookieJweStringDeserializer(
                        new DirectDecrypter(OctetSequenceKey.parse(cookieTokenKey))));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            TokenCookieAuthenticationConfigurer tokenCookieAuthenticationConfigurer,
            TokenCookieJweStringSerializer tokenCookieJweStringSerializer
    ) throws Exception {
        var tokenCookieSessionAuthenticationStrategy = new TokenCookieSessionAuthenticationStrategyImpl();
        tokenCookieSessionAuthenticationStrategy.setTokenStringSerializer(tokenCookieJweStringSerializer);
        http.apply(tokenCookieAuthenticationConfigurer);
        return http
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .sessionAuthenticationStrategy(tokenCookieSessionAuthenticationStrategy))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/manager.html").hasRole("MANAGER")
                                .requestMatchers("/error").permitAll()
                                .requestMatchers(POST, "/register", "/login", "/logout").permitAll()
                                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public TokenCookieJweStringSerializer tokenCookieJweStringSerializer(@Value("${jwt.cookie-token-key}") String cookieTokenKey) throws Exception {
        return new TokenCookieJweStringSerializer(new DirectEncrypter(OctetSequenceKey.parse(cookieTokenKey)));

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
           return userAccountRepository::getByNickname;
        //return  username-> new User("user123","$2a$12$m7Uo5GIopglaqsj1WYDKcuNkyVP.Rip.GC1MIsvPxTVRTmzTCOfk2", List.of(Role.USER));
//        return username -> jdbcTemplate.query("select * from t_user where c_username = ?",
//                (rs, i) -> User.builder()
//                        .username(rs.getString("c_username"))
//                        .password(rs.getString("c_password"))
//                        .authorities(
//                                jdbcTemplate.query("select c_authority from t_user_authority where id_user = ?",
//                                        (rs1, i1) ->
//                                                new SimpleGrantedAuthority(rs1.getString("c_authority")),
//                                        rs.getInt("id")))
//                        .build(), username).stream().findFirst().orElse(null);
    }
}
