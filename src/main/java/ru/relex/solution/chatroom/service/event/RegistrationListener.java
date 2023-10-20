package ru.relex.solution.chatroom.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.service.logic.VerificationTokenService;

@Component
@RequiredArgsConstructor
public class RegistrationListener  {
    @Value("${server.host}")
    private String address;

    @Value("${server.port}")
    private String port;

    private final VerificationTokenService verificationTokenService;

    private final JavaMailSender mailSender;

    @Async
    @EventListener
    public void onApplicationEvent(@NonNull OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserAccount userAccount = event.getUserAccount();
        String verifyToken = verificationTokenService.generateToken(userAccount);
        String recipientAddress = userAccount.getEmail();

        String subject = "Registration Confirmation";
        String confirmationUrl = "https://%s:%s/api/tokens/verify?token=%s".formatted(address, port, verifyToken);
        String emailMessage = "Link for account activation";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientAddress);
        message.setSubject(subject);
        message.setText(emailMessage + "\r\n" + confirmationUrl);

        mailSender.send(message);
    }
}
