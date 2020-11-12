create schema if not exists schema_b;
create table schema_b.test_table1(ID INT PRIMARY KEY,NAME VARCHAR(500));
insert into schema_b.test_table1 values (1,'A');