package com.titanov.slimeBotslime.service;

import com.titanov.slimeBotslime.data.dto.FightPresentable;
import com.titanov.slimeBotslime.data.entity.Fight;
import com.titanov.slimeBotslime.data.entity.Slime;
import com.titanov.slimeBotslime.mapper.FightMapper;
import com.titanov.slimeBotslime.repository.FightRepository;
import com.titanov.slimeBotslime.repository.SlimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FightService {
    private final FightRepository fightRepository;
    private final SlimeRepository slimeRepository;
    private final FightMapper fightMapper;

    public FightPresentable fightCommand(String telegramId) {
        Slime slime = slimeRepository.findByTelegramId(telegramId).orElseThrow();
        UUID slimeId = slime.getId();
        if (slime.getHealth() == 0) {
            return null;
        } else if (checkForBattle(telegramId)) {
            Fight fight = fightRepository.findBySlimeIdAndOver(slimeId, false).orElseThrow();
            return fightMapper.mapToFightPresentable(fight);
        } else {
            return createNewFight(telegramId);
        }
    }

    public FightPresentable createNewFight(String telegramId) {
        Slime slime = slimeRepository.findByTelegramId(telegramId).orElseThrow();
        Fight fight = new Fight();
        fight.setSlime(slime);
        fightRepository.save(fight);
        return fightMapper.mapToFightPresentable(fight);
    }

    public boolean checkForBattle(String telegramId) {
        Slime slime = slimeRepository.findByTelegramId(telegramId).orElseThrow();
        UUID id = slime.getId();
        return fightRepository.existsBySlimeIdAndOver(id, false);
    }

    public FightPresentable getFightByTelegramId(String telegramId) {
        Slime slime = slimeRepository.findByTelegramId(telegramId).orElseThrow();
        UUID id = slime.getId();
        Fight fight = fightRepository.findBySlimeIdAndOver(id, false).orElseThrow();
        return fightMapper.mapToFightPresentable(fight);
    }

    @Transactional
    public FightPresentable attackInFight(String telegramId) {
        Slime slime = slimeRepository.findByTelegramId(telegramId).orElseThrow();
        UUID id = slime.getId();
        Fight fight = fightRepository.findBySlimeIdAndOver(id, false).orElseThrow();
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
