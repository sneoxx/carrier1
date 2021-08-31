alter table CARRIER_INDUSTRIAL_PRODUCTS alter column COST rename to COST__U49539 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column COST__U49539 set null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column NAME rename to NAME__U99206 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column NAME__U99206 set null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column DELETED_BY rename to DELETED_BY__U64780 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column DELETE_TS rename to DELETE_TS__U94484 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column UPDATED_BY rename to UPDATED_BY__U08748 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column UPDATE_TS rename to UPDATE_TS__U74141 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column CREATED_BY rename to CREATED_BY__U02178 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column CREATE_TS rename to CREATE_TS__U17138 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column VERSION rename to VERSION__U78641 ^
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column VERSION__U78641 set null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column DELETED_BY varchar(50) ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column UPDATE_TS timestamp ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column COST decimal(19, 2) ^
update CARRIER_INDUSTRIAL_PRODUCTS set COST = 0 where COST is null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column COST set not null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column DELETE_TS timestamp ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column UPDATED_BY varchar(50) ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column NAME varchar(255) ^
update CARRIER_INDUSTRIAL_PRODUCTS set NAME = '' where NAME is null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column NAME set not null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column CREATED_BY varchar(50) ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column ID varchar(36) not null ^
update CARRIER_INDUSTRIAL_PRODUCTS set ID = '00000000-0000-0000-0000-000000000000' where ID is null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column ID set not null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column CREATE_TS timestamp ;
alter table CARRIER_INDUSTRIAL_PRODUCTS add column VERSION integer ^
update CARRIER_INDUSTRIAL_PRODUCTS set VERSION = 0 where VERSION is null ;
alter table CARRIER_INDUSTRIAL_PRODUCTS alter column VERSION set not null ;
