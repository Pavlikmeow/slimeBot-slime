CREATE TABLE IF NOT EXISTS slimes.slimes
(
    id uuid not null
    constraint slimes_pkey primary key,
    telegram_id varchar,
    level int,
    exp int,
    health int,
    heal_kit int,
    mood int
);