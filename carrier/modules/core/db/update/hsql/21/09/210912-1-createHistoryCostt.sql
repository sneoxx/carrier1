create table CARRIER_HISTORY_COSTT (
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
);