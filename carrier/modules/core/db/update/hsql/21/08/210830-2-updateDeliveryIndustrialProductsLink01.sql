alter table CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK add constraint FK_CARRIER_DELIVERY_INDUSTRIAL_PRODUCTS_LINK_ON_INDUSTRIAL_PRODUCTS foreign key (INDUSTRIAL_PRODUCTS_ID) references CARRIER_INDUSTRIAL_PRODUCTS(ID);