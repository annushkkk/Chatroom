package ru.relex.solution.chatroom.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.security.SecurityService;
import ru.relex.solution.chatroom.service.validation.constraint.EmailUnique;

@RequiredArgsConstructor
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    private final UserAccountRepository repository;
    private final SecurityService securityService;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }

        return !repository.existsByEmail(email);
    }
}