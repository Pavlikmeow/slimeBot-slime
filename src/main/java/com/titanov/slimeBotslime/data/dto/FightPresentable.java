package com.titanov.slimeBotslime.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FightPresentable {
    private int fightLvl;
    private int bossHealth;
    private SlimePresentable slime;
    private boolean over;
}
