package ru.relex.solution.chatroom.security.csrf;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.relex.solution.chatroom.security.jwt.TokenCookieJweStringDeserializer;

import java.io.IOException;
@Setter
public class GetCsrfTokenFilter extends OncePerRequestFilter {
    private RequestMatcher requestMatcher = new AntPathRequestMatcher("/csrf");

    private CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCsrfTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (this.requestMatcher.matches(request)) {
            var token = this.csrfTokenRepository.loadToken(request);
            if (token == null) {
                token = this.csrfTokenRepository.generateToken(request);
                this.csrfTokenRepository.saveToken(token, request, response);
            }
           LOGGER.info("scrfmatch");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            this.objectMapper.writeValue(response.getOutputStream(), token);
            return;
        }
        LOGGER.info("scrfffffffffff");

        filterChain.doFilter(request, response);
    }


}
