package ru.relex.solution.chatroom.service.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;
import ru.relex.solution.chatroom.service.model.Role;
import ru.relex.solution.chatroom.service.model.UserAccountDto;
import ru.relex.solution.chatroom.service.model.UserInfo;

import java.util.List;

@Component
public class UserAccountMapper {

        public UserAccount toEntity(UserAccountDto dto) {
            if (dto == null) {
                return null;
            }

            return UserAccount.builder()
                    .nickname(dto.getNickname())
                    .email(dto.getEmail())
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .password(dto.getPassword())
                    .authorities(List.of(Role.USER))
                    .build();
        }
    public UserAccount toUpdEntity(UserInfo dto,UserAccount userAccount) {
        if (dto == null) {
            return null;
        }

         userAccount.setNickname(dto.getNickname());
         userAccount.setEmail(dto.getEmail());
         userAccount.setFirstName(dto.getFirstName());
         userAccount.setLastName(dto.getLastName());
         userAccount.setId(dto.getId());
        return userAccount;
    } public UserInfo fromEntity(UserAccount userAccount) {
        if (userAccount == null) {
            return null;
        }

        return UserInfo.builder()
                .nickname(userAccount.getNickname())
                .email(userAccount.getEmail())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .id(userAccount.getId())
                .build();
    }

}
