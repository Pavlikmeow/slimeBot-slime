package com.titanov.slimeBotslime.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlimePresentable {
    private int level;
    private int exp;
    private int health;
    private int healKit;
    private int mood;
}
