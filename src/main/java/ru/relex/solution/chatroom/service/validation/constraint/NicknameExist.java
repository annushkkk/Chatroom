package ru.relex.solution.chatroom.service.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.relex.solution.chatroom.service.validation.validator.NicknameExistValidator;
import ru.relex.solution.chatroom.service.validation.validator.NicknameUniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NicknameExistValidator.class)
public @interface NicknameExist {
    String message() default "dont exists";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
