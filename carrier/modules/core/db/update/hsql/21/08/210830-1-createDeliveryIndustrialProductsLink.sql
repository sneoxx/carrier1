create table CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK (
    DELIVERY_ID varchar(36) not null,
    INDUSTRIAL_PRODUCTS_ID varchar(36) not null,
    primary key (DELIVERY_ID, INDUSTRIAL_PRODUCTS_ID)
);
