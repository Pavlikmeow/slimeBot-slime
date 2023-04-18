package com.titanov.slimeBotslime.service;

import com.titanov.slimeBotslime.data.dto.SlimePresentable;
import com.titanov.slimeBotslime.data.entity.Slime;
import com.titanov.slimeBotslime.exception.ErrorCode;
import com.titanov.slimeBotslime.exception.SlimeNotFoundException;
import com.titanov.slimeBotslime.mapper.SlimeMapper;
import com.titanov.slimeBotslime.repository.SlimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static java.sql.Date.valueOf;

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

    public Slime getSlimeByTelegramId(String id) {
        return slimeRepository.findByTelegramId(id)
                .orElseThrow(() -> new SlimeNotFoundException(
                        String.format("Slime with telegram id: %s not found", id),
                        ErrorCode.SLIME_NOT_FOUND
                ));
    }

    public SlimePresentable getSlimePresentableByTelegramId(String id) {
        Slime slime = getSlimeByTelegramId(id);
        return slimeMapper.mapToSlimePresentable(slime);
    }

    public UUID getSlimeIdByTelegramId(String id) {
        return getSlimeByTelegramId(id).getId();
    }

    @Transactional
    public int healSlimeByTelegramId(String id) {
        Slime slime = getSlimeByTelegramId(id);
        slime.setHealth(slime.getHealth() + 5);
        slime.setHealKit(slime.getHealKit() - 1);
        return slime.getHealth();
    }

    @Transactional
    public String petSlimeByTelegramId(String id) {
        Slime slime = getSlimeByTelegramId(id);
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
