CREATE TABLE fights.fights
(
    id uuid not null
    constraint fights_pkey primary key,
    fight_lvl int,
    boss_health int,
    over bool,
    slime_id uuid
)