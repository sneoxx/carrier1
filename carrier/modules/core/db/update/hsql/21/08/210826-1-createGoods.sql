create table CARRIER_GOODS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255) not null,
    COST decimal(19, 2) not null,
    --
    -- from carrier_IndustrialProducts
    WEIGHT double precision,
    --
    -- from carrier_FoodStuffs
    EXPIRATION_DATE date,
    --
    primary key (ID)
);