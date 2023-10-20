package ru.relex.solution.chatroom.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.relex.solution.chatroom.persistence.entity.UserAccount;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    boolean existsByEmail(String email);
    UserAccount getByNickname(String nickname);
    boolean existsByNickname(String nickname);
    UserAccount getById(UUID uuid);
    UserAccount getByEmail(String email);
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM UserAccount u 
        WHERE   u.deletedAt < :lastRecoveryDay
        """)
    void cleanUpDeletedUsers(@Param("lastRecoveryDay") Instant lastRecoveryDay);
}
