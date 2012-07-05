create database if not exists controlibmarket;

use controlibmarket;

drop table if exists application;
drop table if exists user;

create table user ( id INT not null auto_increment, login VARCHAR(20), password VARCHAR(32), admin boolean, primary key (id) ) TYPE=innodb;
create table application ( id INT not null auto_increment, name VARCHAR(100), description VARCHAR(100), link VARCHAR(255), user_id int, index par_ind (user_id), primary key (id), foreign key (user_id) references user(id)) TYPE=innodb;
