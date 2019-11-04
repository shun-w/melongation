drop table if exists `admin`;
drop table if exists `answer`;
drop table if exists `question`;
drop table if exists `paper`;
drop table if exists `user`;


create table `admin`(
id int primary key auto_increment,
username varchar(10),
`password` varchar(20),
image varchar(100),
email varchar(30)
);


create table `user`(
id int primary key auto_increment,
username varchar(10),
`password` varchar(20),
image varchar(100),
email varchar(30),
is_active tinyint
);


create table `paper`(
id int primary key auto_increment,
user_id int,
title varchar(1000),
description varchar(2000),
create_time datetime,
submit_number int,
foreign key (user_id) references user(id)
);

create table `question`
(
id int primary key auto_increment,
paper_id int,
type int,
must_answer tinyint,
order_number int,
foreign key (paper_id) references paper(id)
);

create table  `answer`(
id int primary key auto_increment,
question_id int,
answer varchar(1000),
foreign key (question_id) references question(id)
);
