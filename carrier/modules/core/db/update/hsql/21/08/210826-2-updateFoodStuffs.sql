alter table CARRIER_FOOD_STUFFS alter column COST rename to COST__U98836 ^
alter table CARRIER_FOOD_STUFFS alter column COST__U98836 set null ;
alter table CARRIER_FOOD_STUFFS alter column NAME rename to NAME__U97379 ^
alter table CARRIER_FOOD_STUFFS alter column NAME__U97379 set null ;
alter table CARRIER_FOOD_STUFFS alter column DELETED_BY rename to DELETED_BY__U45523 ^
alter table CARRIER_FOOD_STUFFS alter column DELETE_TS rename to DELETE_TS__U78118 ^
alter table CARRIER_FOOD_STUFFS alter column UPDATED_BY rename to UPDATED_BY__U15574 ^
alter table CARRIER_FOOD_STUFFS alter column UPDATE_TS rename to UPDATE_TS__U44019 ^
alter table CARRIER_FOOD_STUFFS alter column CREATED_BY rename to CREATED_BY__U46201 ^
alter table CARRIER_FOOD_STUFFS alter column CREATE_TS rename to CREATE_TS__U58520 ^
alter table CARRIER_FOOD_STUFFS alter column VERSION rename to VERSION__U85707 ^
alter table CARRIER_FOOD_STUFFS alter column VERSION__U85707 set null ;
alter table CARRIER_FOOD_STUFFS add column DELETED_BY varchar(50) ;
alter table CARRIER_FOOD_STUFFS add column UPDATE_TS timestamp ;
alter table CARRIER_FOOD_STUFFS add column COST decimal(19, 2) ^
update CARRIER_FOOD_STUFFS set COST = 0 where COST is null ;
alter table CARRIER_FOOD_STUFFS alter column COST set not null ;
alter table CARRIER_FOOD_STUFFS add column DELETE_TS timestamp ;
alter table CARRIER_FOOD_STUFFS add column UPDATED_BY varchar(50) ;
alter table CARRIER_FOOD_STUFFS add column NAME varchar(255) ^
update CARRIER_FOOD_STUFFS set NAME = '' where NAME is null ;
alter table CARRIER_FOOD_STUFFS alter column NAME set not null ;
alter table CARRIER_FOOD_STUFFS add column CREATED_BY varchar(50) ;
alter table CARRIER_FOOD_STUFFS add column ID varchar(36) not null ^
update CARRIER_FOOD_STUFFS set ID = '00000000-0000-0000-0000-000000000000' where ID is null ;
alter table CARRIER_FOOD_STUFFS alter column ID set not null ;
alter table CARRIER_FOOD_STUFFS add column CREATE_TS timestamp ;
alter table CARRIER_FOOD_STUFFS add column VERSION integer ^
update CARRIER_FOOD_STUFFS set VERSION = 0 where VERSION is null ;
alter table CARRIER_FOOD_STUFFS alter column VERSION set not null ;
