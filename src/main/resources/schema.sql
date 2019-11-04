drop table if exists `role_user`;
drop table if exists `answer`;
drop table if exists `question`;
drop table if exists `paper`;
drop table if exists `role`;
drop table if exists `user`;

create table `role`(
id int primary key auto_increment,
name varchar(10)
);

create table `user`(
id int primary key auto_increment,
username varchar(10),
`password` varchar(20),
image varchar(100),
email varchar(30)
);

create table `role_user`(
role_id int not null,
user_id int not null,
foreign key (role_id) references role(id),
foreign key (user_id) references user(id)
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

insert into `role` values
(1,'user'),
(2, 'admin');