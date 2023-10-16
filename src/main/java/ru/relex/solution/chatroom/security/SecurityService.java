package ru.relex.solution.chatroom.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.service.model.TokenUser;


@Service
public class SecurityService {
    public String getNickname() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        Object principal = securityContext.getAuthentication().getPrincipal();

        if (!(principal instanceof String) ) {
            TokenUser c=(TokenUser) principal;
            return c.getUsername();
        } else {
            return null;
        }
        }
    }
