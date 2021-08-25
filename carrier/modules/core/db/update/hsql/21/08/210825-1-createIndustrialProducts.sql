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
);