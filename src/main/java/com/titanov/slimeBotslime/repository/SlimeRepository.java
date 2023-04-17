package com.titanov.slimeBotslime.repository;

import com.titanov.slimeBotslime.data.entity.Slime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SlimeRepository extends JpaRepository<Slime, UUID> {

    Optional<Slime> findByTelegramId(String telegramId);

    boolean existsByTelegramId(String telegramId);
}
