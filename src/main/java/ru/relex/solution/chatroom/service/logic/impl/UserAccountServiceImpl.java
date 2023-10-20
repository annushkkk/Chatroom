package ru.relex.solution.chatroom.service.logic.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.persistence.repository.UserAccountRepository;
import ru.relex.solution.chatroom.service.event.OnRegistrationCompleteEvent;
import ru.relex.solution.chatroom.service.logic.UserAccountService;
import ru.relex.solution.chatroom.service.mapper.UserAccountMapper;
import ru.relex.solution.chatroom.service.model.*;
import ru.relex.solution.chatroom.service.model.useraccount.DeleteResponse;
import ru.relex.solution.chatroom.service.model.useraccount.RegisterResponse;
import ru.relex.solution.chatroom.service.model.useraccount.UserAccountDto;
import ru.relex.solution.chatroom.service.model.useraccount.UserInfo;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordRequest;
import ru.relex.solution.chatroom.service.model.useraccount.changepassword.ChangePasswordResponse;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccRequest;
import ru.relex.solution.chatroom.service.model.useraccount.recover.RecoverUserAccResponse;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public RegisterResponse register(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setActive(false);
        userAccount.setAuthorities(List.of(Role.USER));
        userAccountRepository.save(userAccount);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userAccount));
        return new RegisterResponse("You need to confirm your email");
    }

    @Override
    public DeleteResponse deleteAcc(String nickname) {
        UserAccount userAccount = userAccountRepository.getByNickname(nickname);
        userAccount.setActive(false);
        userAccount.setDeletedAt(Instant.now());
        userAccountRepository.save(userAccount);
        return new DeleteResponse("Account successfully deleted");
    }

    @Override
    public UserInfo update(UserInfo userInfo, String nickname) {
        UserAccount userAccount = userAccountRepository.getByNickname(nickname);
        String userAccountEmail = userAccount.getEmail();
        UserAccount user = userAccountMapper.toUpdEntity(userInfo, userAccount);
        if (!Objects.equals(userInfo.getNickname(), nickname) &&
                userAccountRepository.existsByNickname(userInfo.getNickname())
        ) {
            return userInfo;
        }
        if (!Objects.equals(userInfo.getEmail(), userAccountEmail)) {
            user.setActive(false);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        }

        return userAccountMapper.fromEntity(userAccountRepository.save(user));
    }

    @Override
    public ChangePasswordResponse updatePassword(ChangePasswordRequest request) {
        UserAccount user = userAccountRepository.getByEmail(request.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userAccountRepository.save(user);
                return new ChangePasswordResponse("Password successfully updated");
            }
        }
        return new ChangePasswordResponse("Invalid credentials");
    }

    @Override
    public RecoverUserAccResponse recoverUserAcc(RecoverUserAccRequest request) {
        UserAccount user = userAccountRepository.getByNickname(request.getNickname());
        if (user != null) {
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                user.setActive(true);
                userAccountRepository.save(user);
                return new RecoverUserAccResponse("Account successfully recovered");
            }
            return new RecoverUserAccResponse("Invalid credentials");

        }
        return new RecoverUserAccResponse("Recovery time has expired");
    }
}
