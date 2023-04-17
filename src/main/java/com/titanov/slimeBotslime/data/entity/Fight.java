package com.titanov.slimeBotslime.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(schema = "fights", name = "fights")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fight {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "fight_lvl")
    private int fightLvl = 1;
    @Column(name = "boss_health")
    private int bossHealth = 20;
    @Column(name = "over")
    private boolean over = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slime_id", nullable = false)
    private Slime slime;
}
