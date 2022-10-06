
    drop table if exists gacha_ball;

    drop table if exists gacha_item;

    drop table if exists gacha_machine;

    drop table if exists item_data;

    create table gacha_ball (
       id varchar(255) not null,
        gacha_item_id varchar(255),
        gacha_machine_id varchar(255),
        is_openned bit,
        primary key (id)
    ) engine=InnoDB;

    create table gacha_item (
       id varchar(255) not null,
        gacha_item_category varchar(255),
        gacha_item_description varchar(255),
        gacha_item_name varchar(255),
        gacha_item_rarity varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table gacha_machine (
       id varchar(255) not null,
        gacha_machine_description varchar(255),
        gacha_machine_max_stock integer,
        gacha_machine_name varchar(255),
        gacha_machine_price integer,
        primary key (id)
    ) engine=InnoDB;

    create table item_data (
       id varchar(255) not null,
        item_data_description varchar(255),
        item_data_name varchar(255),
        item_data_rarity varchar(255),
        primary key (id)
    ) engine=InnoDB;
