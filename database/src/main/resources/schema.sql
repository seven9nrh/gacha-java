
    drop table if exists gacha_ball;

    drop table if exists gacha_item;

    drop table if exists gacha_player;

    drop table if exists item;

    create table gacha_ball (
       id varchar(255) not null,
        created_at TIMESTAMP,
        created_by varchar(255),
        deleted_at TIMESTAMP,
        deleted_by varchar(255),
        is_deleted bit not null,
        updated_at TIMESTAMP,
        updated_by varchar(255),
        gacha_item_id varchar(255),
        gacha_player_id varchar(255),
        is_ejected bit not null,
        is_opened bit not null,
        primary key (id)
    ) engine=InnoDB;

    create table gacha_item (
       id varchar(255) not null,
        created_at TIMESTAMP,
        created_by varchar(255),
        deleted_at TIMESTAMP,
        deleted_by varchar(255),
        is_deleted bit not null,
        updated_at TIMESTAMP,
        updated_by varchar(255),
        gacha_item_description varchar(255),
        gacha_player_id varchar(255),
        gacha_item_name varchar(255),
        gacha_item_rarity varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table gacha_player (
       id varchar(255) not null,
        created_at TIMESTAMP,
        created_by varchar(255),
        deleted_at TIMESTAMP,
        deleted_by varchar(255),
        is_deleted bit not null,
        updated_at TIMESTAMP,
        updated_by varchar(255),
        gacha_player_description varchar(255),
        gacha_player_name varchar(255),
        gacha_player_wallet integer,
        primary key (id)
    ) engine=InnoDB;

    create table item (
       id varchar(255) not null,
        created_at TIMESTAMP,
        created_by varchar(255),
        deleted_at TIMESTAMP,
        deleted_by varchar(255),
        is_deleted bit not null,
        updated_at TIMESTAMP,
        updated_by varchar(255),
        item_description varchar(255),
        item_name varchar(255),
        item_rarity varchar(255),
        primary key (id)
    ) engine=InnoDB;
