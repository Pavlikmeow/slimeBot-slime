package com.titanov.slimeBotslime.service;

import com.titanov.slimeBotslime.data.dto.SlimePresentable;
import com.titanov.slimeBotslime.data.entity.Slime;
import com.titanov.slimeBotslime.mapper.SlimeMapper;
import com.titanov.slimeBotslime.repository.SlimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static java.sql.Date.valueOf;
import static java.util.Date.*;

@Service
@RequiredArgsConstructor
public class SlimeService {

    private final SlimeRepository slimeRepository;
    private final SlimeMapper slimeMapper;

    public void createNewSlime(String id) {
        Slime slime = new Slime();
        slime.setTelegramId(id);
        slimeRepository.save(slime);
    }

    public SlimePresentable getSlimeByTelegramId(String id) {
        Slime slime = slimeRepository.findByTelegramId(id).orElseThrow();
        return slimeMapper.mapToSlimePresentable(slime);
    }

    @Transactional
    public int healSlimeByTelegramId(String id) {
        Slime slime = slimeRepository.findByTelegramId(id).orElseThrow();
        slime.setHealth(slime.getHealth() + 5);
        slime.setHealKit(slime.getHealKit() - 1);
        return slime.getHealth();
    }

    @Transactional
    public String petSlimeByTelegramId(String id) {
        Slime slime = slimeRepository.findByTelegramId(id).orElseThrow();
        Date today = valueOf(LocalDate.now());
        if (slime.getLastPetDate().compareTo(today) != 0 && slime.getMood() != 100) {
            slime.setMood(slime.getMood() + 5);
            if (slime.getMood() > 100) {
                slime.setMood(100);
            }
            slime.setLastPetDate(new Date());
            return String.format("Вы погладили слайма, теперь его настроение: %d/100", slime.getMood());
        } else if (slime.getLastPetDate().compareTo(today) == 0) {
            return "Вы сегодня уже кормили слайма";
        } else return "Слайм не голодный";
    }
}
