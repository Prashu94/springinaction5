-- create database taco_db;
use taco_db;
-- drop table Ingredient;
create table Ingredient(
	id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);
-- drop table Taco;
create table Taco(
	id bigint(20) not null auto_increment,
    name varchar(50) not null,
    createdAt timestamp not null,
    primary key (id)
);

-- drop table Taco_Ingredients;
create table Taco_Ingredients(
	taco bigint(20) not null,
    ingredient varchar(4) not null
);

alter table Taco_ingredients 
add constraint taco_ingredients_ibfk1 foreign key (taco) references Taco(id);
alter table Taco_Ingredients 
add constraint taco_ingredients_ibfk2 foreign key (ingredient) references Ingredient(id);

-- drop table Taco_Order;
create table Taco_order(
	id bigint(20) not null auto_increment,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(50) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null,
    primary key (id)
);
-- drop table Taco_Order_Tacos;
create table Taco_Order_Tacos(
	tacoOrder bigint(20) not null,
    taco bigint(20) not null
);

alter table Taco_Order_Tacos
add constraint taco_order_tacos_ibfk1 foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
add constraint taco_order_tacos_ibfk2 foreign key (taco) references Taco(id);

create table User(
	id bigint(20) not null auto_increment,
    username varchar(255),
    password varchar(255),
    fullname varchar(255),
    street varchar(255),
    city varchar(255),
    state varchar(255),
    zip varchar(255),
    phone_number varchar(255),
    primary key (id)
);
