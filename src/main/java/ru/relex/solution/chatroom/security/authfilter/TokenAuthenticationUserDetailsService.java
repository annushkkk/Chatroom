package ru.relex.solution.chatroom.security.authfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import ru.relex.solution.chatroom.security.csrf.GetCsrfTokenFilter;
import ru.relex.solution.chatroom.service.logic.UserAccountService;
import ru.relex.solution.chatroom.service.model.Role;
import ru.relex.solution.chatroom.service.model.Token;

import java.util.List;

public class TokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private UserAccountService userAccountService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationUserDetailsService.class);

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authenticationToken) throws UsernameNotFoundException {
        LOGGER.info("Useeeeeeeeeeeeerdetaaaaaaail");
        if (authenticationToken.getPrincipal() instanceof Token token) {
//                  return new User(token.subject(),"pass12345",
//                          token.authorities().stream()
//                                  .map(SimpleGrantedAuthority::new)
//                                  .toList());
            LOGGER.info("maaaaaaaaaaaaaaaaaaaatchUSeeeeeeeeeeerdetaaaaaaail");
            return new User("user123","pass123",List.of(Role.USER) );
        }
        return null;
    }
}
