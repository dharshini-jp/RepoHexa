create database OrderManagementsystem;
use OrderManagementSystem;

create table product (
    productid int primary key,
    productname varchar(255),
    description text,
    price double,
    quantityinstock int,
    type enum('electronics', 'clothing')
);

create table electronics (
    productid int primary key,
    brand varchar(255),
    warrantyperiod int,
    foreign key (productid) references product(productid)
);

create table clothing (
    productid int primary key,
    size varchar(20),
    color varchar(50),
    foreign key (productid) references product(productid)
);

create table user (
    userid int primary key,
    username varchar(255),
    password varchar(255),
    role enum('admin', 'user')
);

