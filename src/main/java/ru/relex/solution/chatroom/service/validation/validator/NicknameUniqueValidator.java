package ru.relex.solution.chatroom.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameUnique;

@RequiredArgsConstructor
public class NicknameUniqueValidator implements ConstraintValidator<NicknameUnique, String> {

    private final UserAccountRepository repository;

    @Override
    public boolean isValid(String nickname, ConstraintValidatorContext context) {
        if (nickname == null) {
            return true;
        }

        return !repository.existsByNickname(nickname);
    }
}