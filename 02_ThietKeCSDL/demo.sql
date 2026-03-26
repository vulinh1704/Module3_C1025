create database shop_c10;
use shop_c10;
-- lệnh liên quan đến cấu trúc db: create, alter, drop, show 
create table product(
	id int primary key auto_increment,  -- primary key = unique + not null + khóa chính
    name varchar(120) not null unique,
    description text,
    price double default(0)
);
-- lệnh liên quan đến dữ liệu (data): insert, update, delete, select
insert into product(name) values (
"Tôm hùm"
);
select product.*, category.name as categoryName 
from product join category on product.idCategory = category.id;
create table category(
	id int primary key auto_increment,
    name varchar(100) not null
);
insert into category(name) values ("Tôm");
select * from category;
alter table product add column idCategory int;
alter table product add foreign key (idCategory) REFERENCES category(id); -- khóa phụ