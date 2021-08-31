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
    DISTANCE varchar(255) not null,
    CARRIER_ID varchar(36),
    --
    primary key (ID)
);