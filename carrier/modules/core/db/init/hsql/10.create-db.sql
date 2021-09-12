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
-- end CARRIER_INDUSTRIAL_PRODUCTS
-- begin CARRIER_FOOD_STUFFS
-- end CARRIER_FOOD_STUFFS
-- begin CARRIER_GOODS
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
)^
-- end CARRIER_GOODS
-- begin CARRIER_DELIVERY
create table CARRIER_DELIVERY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_CITY varchar(255) not null,
    ADDRESS_STREET varchar(255) not null,
    ADDRESS_HOUSE varchar(255) not null,
    --
    NUMBER_ varchar(255) not null,
    STATUS integer not null,
    DATE_ date not null,
    DISTANCE integer not null,
    CARRIER_ID varchar(36),
    --
    primary key (ID)
)^
-- end CARRIER_DELIVERY
-- begin CARRIER_DELIVERY_TRUCK_LINK
create table CARRIER_DELIVERY_TRUCK_LINK (
    DELIVERY_ID varchar(36) not null,
    TRUCK_ID varchar(36) not null,
    primary key (DELIVERY_ID, TRUCK_ID)
)^
-- end CARRIER_DELIVERY_TRUCK_LINK
-- begin CARRIER_DELIVERY_GOODS_LINK
create table CARRIER_DELIVERY_GOODS_LINK (
    GOODS_ID varchar(36) not null,
    DELIVERY_ID varchar(36) not null,
    primary key (GOODS_ID, DELIVERY_ID)
)^
-- end CARRIER_DELIVERY_GOODS_LINK
-- begin CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK
create table CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK (
    DELIVERY_ID varchar(36) not null,
    INDUSTRIAL_PRODUCTS_ID varchar(36) not null,
    primary key (DELIVERY_ID, INDUSTRIAL_PRODUCTS_ID)
)^
-- end CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK

-- begin CARRIER_HISTORY_COST
create table CARRIER_HISTORY_COST (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CHANGE_DATE timestamp not null,
    COST decimal(19, 2) not null,
    CARRIER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CARRIER_HISTORY_COST
-- begin SEC_USER
alter table SEC_USER add column CARRIER_ID varchar(36) ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'carrier_ExtUser' where DTYPE is null ^
-- end SEC_USER
