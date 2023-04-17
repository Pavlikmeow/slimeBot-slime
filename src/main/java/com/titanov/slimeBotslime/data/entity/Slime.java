package com.titanov.slimeBotslime.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(schema = "slimes", name = "slimes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Slime {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "telegram_id")
    private String telegramId;
    @Column(name = "level")
    private int level = 1;
    @Column(name = "exp")
    private int exp = 0;
    @Column(name = "health")
    private int health = 20;
    @Column(name = "heal_kit")
    private int healKit = 3;
    @Column(name = "mood")
    private int mood = 0;
    @Column(name = "last_pet_date")
    private Date lastPetDate = new Date();

}
