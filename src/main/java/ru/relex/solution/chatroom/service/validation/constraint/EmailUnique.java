package ru.relex.solution.chatroom.service.validation.constraint;

import jakarta.validation.Payload;
import jakarta.validation.Constraint;
import ru.relex.solution.chatroom.service.validation.validator.EmailUniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailUniqueValidator.class)
public @interface EmailUnique {

    String message() default "already exists";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
