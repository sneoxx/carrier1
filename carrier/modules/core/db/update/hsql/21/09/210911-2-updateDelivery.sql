alter table CARRIER_DELIVERY alter column DISTANCE rename to DISTANCE__U03869 ^
alter table CARRIER_DELIVERY alter column DISTANCE__U03869 set null ;
alter table CARRIER_DELIVERY add column DISTANCE integer ^
update CARRIER_DELIVERY set DISTANCE = 0 where DISTANCE is null ;
alter table CARRIER_DELIVERY alter column DISTANCE set not null ;
