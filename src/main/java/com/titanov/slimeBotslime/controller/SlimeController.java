package com.titanov.slimeBotslime.controller;

import com.titanov.slimeBotslime.data.dto.SlimePresentable;
import com.titanov.slimeBotslime.service.SlimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slime")
@RequiredArgsConstructor
public class SlimeController {

    private final SlimeService slimeService;

    @PostMapping("/{telegramId}")
    public void createNewSlime(@PathVariable String telegramId) {
        slimeService.createNewSlime(telegramId);
    }

    @GetMapping("/{telegramId}")
    public SlimePresentable getSlimeByTelegramId(@PathVariable String telegramId) {
        return slimeService.getSlimeByTelegramId(telegramId);
    }

    @PostMapping("/heal/{telegramId}")
    public int healSlimeByTelegramId(@PathVariable String telegramId) {
        return slimeService.healSlimeByTelegramId(telegramId);
    }

    @PostMapping("/pet/{telegramId}")
    public String petSlimeByTelegramId(@PathVariable String telegramId) {
        return slimeService.petSlimeByTelegramId(telegramId);
    }

}
