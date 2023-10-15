package ru.relex.solution.chatroom.service.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final UserAccount userAccount;

    public OnRegistrationCompleteEvent(UserAccount userAccount) {
        super(userAccount);
        this.userAccount = userAccount;
    }
}