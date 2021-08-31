create table CARRIER_DELIVERY_GOODS_LINK (
    DELIVERY_ID varchar(36) not null,
    GOODS_ID varchar(36) not null,
    primary key (DELIVERY_ID, GOODS_ID)
);
