package com.titanov.slimeBotslime.mapper;

import com.titanov.slimeBotslime.data.dto.FightPresentable;
import com.titanov.slimeBotslime.data.entity.Fight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FightMapper {
    FightPresentable mapToFightPresentable(Fight fight);
}
