-- begin CARRIER_TRUCK_TYPE
create table CARRIER_TRUCK_TYPE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CAPACITY integer not null,
    --
    primary key (ID)
)^
-- end CARRIER_TRUCK_TYPE
-- begin CARRIER_TRUCK
create table CARRIER_TRUCK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255) not null,
    CARRIER_ID varchar(36),
    TRUCK_TYPE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CARRIER_TRUCK
-- begin CARRIER_CARRIER
create table CARRIER_CARRIER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    COST decimal(19, 2) not null,
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end CARRIER_CARRIER
-- begin CARRIER_INDUSTRIAL_PRODUCTS
create table CARRIER_INDUSTRIAL_PRODUCTS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME varchar(255) not null,
    COST decimal(19, 2) not null,
    --
    WEIGHT double precision,
    --
    primary key (ID)
)^
-- end CARRIER_INDUSTRIAL_PRODUCTS
-- begin CARRIER_FOOD_STUFFS
create table CARRIER_FOOD_STUFFS (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME varchar(255) not null,
    COST decimal(19, 2) not null,
    --
    EXPIRATION_DATE date,
    --
    primary key (ID)
)^
-- end CARRIER_FOOD_STUFFS
