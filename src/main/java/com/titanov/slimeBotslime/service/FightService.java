package com.titanov.slimeBotslime.service;

import com.titanov.slimeBotslime.data.dto.FightPresentable;
import com.titanov.slimeBotslime.data.entity.Fight;
import com.titanov.slimeBotslime.data.entity.Slime;
import com.titanov.slimeBotslime.exception.ErrorCode;
import com.titanov.slimeBotslime.exception.FightNotFoundException;
import com.titanov.slimeBotslime.mapper.FightMapper;
import com.titanov.slimeBotslime.repository.FightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FightService {
    private final FightRepository fightRepository;
    private final SlimeService slimeService;
    private final FightMapper fightMapper;

    public FightPresentable createNewFight(String telegramId) {
        Slime slime = slimeService.getSlimeByTelegramId(telegramId);
        Fight fight = new Fight();
        fight.setSlime(slime);
        fightRepository.save(fight);
        return fightMapper.mapToFightPresentable(fight);
    }

    public Fight getFightBySlimeIdAndOver(UUID id, boolean over) {
        return fightRepository.findBySlimeIdAndOver(id ,over)
                .orElseThrow(() -> new FightNotFoundException(
                        String.format("Fight with slime id: %s and over: %b not found", id, over),
                        ErrorCode.FIGHT_NOT_FOUND
        ));
    }
    public Fight getFightByTelegramId(String telegramId) {
        Slime slime = slimeService.getSlimeByTelegramId(telegramId);
        UUID id = slime.getId();
        return getFightBySlimeIdAndOver(id, false);
    }
    public FightPresentable getFightPresentableByTelegramId(String telegramId) {
        Fight fight = getFightByTelegramId(telegramId);
        return fightMapper.mapToFightPresentable(fight);
    }

    public boolean checkForBattle(String telegramId) {
        UUID id = slimeService.getSlimeIdByTelegramId(telegramId);
        return fightRepository.existsBySlimeIdAndOver(id, false);
    }

    public FightPresentable fightCommand(String telegramId) {
        if (checkForBattle(telegramId)) {
            return getFightPresentableByTelegramId(telegramId);
        }
        Slime slime = slimeService.getSlimeByTelegramId(telegramId);
        if (slime.getHealth() == 0) {
            return null;
        }
        return createNewFight(telegramId);
    }

    @Transactional
    public FightPresentable attackInFight(String telegramId) {
        Fight fight = getFightByTelegramId(telegramId);
        Slime slime = fight.getSlime();
        fight.setBossHealth(fight.getBossHealth() - slime.getLevel()*2);
        if (fight.getBossHealth() <= 0) {
            fight.setFightLvl(fight.getFightLvl() + 1);
            fight.setBossHealth(fight.getFightLvl() * 15);
        } else {
            slime.setHealth(slime.getHealth() - fight.getFightLvl() * 2);
            if (slime.getHealth() <= 0 ) {
                slime.setHealth(0);
                fight.setOver(true);
            }
        }
        return fightMapper.mapToFightPresentable(fight);
    }
}
