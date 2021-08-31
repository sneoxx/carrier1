create table CARRIER_DELIVERY_TRUCK_LINK (
    DELIVERY_ID varchar(36) not null,
    TRUCK_ID varchar(36) not null,
    primary key (DELIVERY_ID, TRUCK_ID)
);
