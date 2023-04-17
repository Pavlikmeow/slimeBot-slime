package com.titanov.slimeBotslime.repository;

import com.titanov.slimeBotslime.data.entity.Fight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FightRepository extends JpaRepository<Fight, UUID> {

    boolean existsBySlimeIdAndOver(UUID id, boolean isOver);

    Optional<Fight> findBySlimeIdAndOver(UUID id, boolean isOver);

    Optional<Fight> findBySlimeId(UUID id);
}
