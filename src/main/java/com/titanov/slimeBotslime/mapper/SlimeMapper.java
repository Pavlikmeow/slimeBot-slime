package com.titanov.slimeBotslime.mapper;

import com.titanov.slimeBotslime.data.dto.SlimePresentable;
import com.titanov.slimeBotslime.data.entity.Slime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlimeMapper {
    SlimePresentable mapToSlimePresentable(Slime slime);
}
