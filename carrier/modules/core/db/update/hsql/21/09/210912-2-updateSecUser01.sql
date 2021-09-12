alter table SEC_USER add constraint FK_SEC_USER_ON_CARRIER foreign key (CARRIER_ID) references CARRIER_CARRIER(ID);
create index IDX_SEC_USER_ON_CARRIER on SEC_USER (CARRIER_ID);
