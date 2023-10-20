package ru.relex.solution.chatroom.service.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.service.validation.constraint.NicknameExist;
@RequiredArgsConstructor
public class NicknameExistValidator implements ConstraintValidator<NicknameExist, String> {
    private final UserAccountRepository repository;

    @Override
    public boolean isValid(String nickname, ConstraintValidatorContext context) {
        if (nickname == null) {
            return false;
        }

        return repository.existsByNickname(nickname);
    }
}
