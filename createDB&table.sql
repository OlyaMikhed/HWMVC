create database productmvc;

use productmvc;
create table Product
(
    id     int primary key auto_increment,
    title  nvarchar(50),
    description nvarchar(200)
);