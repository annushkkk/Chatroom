package ru.relex.solution.chatroom.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.solution.chatroom.persistence.entity.DeactivatedToken;

import java.util.UUID;

public interface DeactivatedTokenRepository extends JpaRepository<DeactivatedToken, UUID> {

}
