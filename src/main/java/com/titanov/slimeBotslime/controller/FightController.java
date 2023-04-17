package com.titanov.slimeBotslime.controller;

import com.titanov.slimeBotslime.data.dto.FightPresentable;
import com.titanov.slimeBotslime.service.FightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slime/fight")
@RequiredArgsConstructor
public class FightController {
    private final FightService fightService;

    @PostMapping("/{telegramId}")
    public FightPresentable fightCommand(@PathVariable String telegramId) {
        return fightService.fightCommand(telegramId);
    }

    @GetMapping("/{telegramId}")
    public FightPresentable getFightByTelegramId(@PathVariable String telegramId) {
        return fightService.getFightByTelegramId(telegramId);
    }

    @PostMapping("/attack/{telegramId}")
    public FightPresentable attackInFight(@PathVariable String telegramId) {
        return fightService.attackInFight(telegramId);
    }
}
