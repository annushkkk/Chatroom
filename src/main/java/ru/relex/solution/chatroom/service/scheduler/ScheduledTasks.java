package ru.relex.solution.chatroom.service.scheduler;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;

import java.time.Duration;
import java.time.Instant;
@RequiredArgsConstructor
@Component
public class ScheduledTasks {

    private final UserAccountRepository userAccountRepository;
    @Value("${user-account.recovery-days}")
    private String recoveryDays;

    @Scheduled(cron = "0 0 0 1 1,7 *")
    public void deletingUserAccounts() {
        Duration recoveryTime = Duration.ofDays(Integer.parseInt(recoveryDays));
        Instant now=Instant.now();
        Instant lastRecoveryDay=now.minus(recoveryTime);
        userAccountRepository.cleanUpDeletedUsers(lastRecoveryDay);

    }
}
