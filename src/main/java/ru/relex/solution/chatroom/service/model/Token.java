package ru.relex.solution.chatroom.service.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record Token(UUID id, String subject, List<String> authorities, Instant createdAt,
                    Instant expiresAt) {
}
