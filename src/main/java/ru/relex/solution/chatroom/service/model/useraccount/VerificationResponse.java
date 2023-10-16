package ru.relex.solution.chatroom.service.model.useraccount;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerificationResponse {
    private final static String DEFAULT_MESSAGE ="Account has been successfully created";

    @NotNull
    private String message;

    public VerificationResponse() {
        this.message = DEFAULT_MESSAGE;
    }
}
