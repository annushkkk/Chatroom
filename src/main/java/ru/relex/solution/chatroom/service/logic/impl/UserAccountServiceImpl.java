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

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public RegisterResponse register(UserAccountDto userAccountDto) {
        UserAccount userAccount=userAccountMapper.toEntity(userAccountDto);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setActive(false);
        userAccount.setAuthorities(List.of(Role.USER));
        userAccountRepository.save(userAccount);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userAccount));
        return new RegisterResponse("You need to confirm your email");
    }

    @Override
    public DeleteResponse deleteAcc() {
        return null;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        UserAccount userAccount=userAccountRepository.getById(userInfo.getId());
        String userAccountEmail=userAccount.getEmail();
        UserAccount user= userAccountMapper.toUpdEntity(userInfo,userAccount);

        if (userInfo.getEmail()!=userAccountEmail){
            user.setActive(false);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));
        }

        return userAccountMapper.fromEntity(userAccountRepository.save(user));
    }
}
