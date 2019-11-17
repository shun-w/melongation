drop table if exists `admin`;
drop table if exists `answer`;
drop table if exists `question`;
drop table if exists `paper`;
drop table if exists `user`;

# 管理员
create table `admin`(
id int primary key auto_increment,
username varchar(10),
`password` varchar(20),
image varchar(100),
email varchar(30)
);

# 用户
create table `user`(
id int primary key auto_increment,
username varchar(10),
`password` varchar(20),
image varchar(100),
email varchar(30),
is_active tinyint
);


# 问卷
create table `paper`(
id int primary key auto_increment,
user_id int,
title varchar(1000),
description varchar(2000),
create_time datetime,
submit_number int,
is_checked tinyint,
foreign key (user_id) references user(id)
);


# 问卷的问题
create table `question`
(
id int primary key auto_increment,
paper_id int,
type int,
content varchar(2000),
must_answer tinyint,
order_number int,
foreign key (paper_id) references paper(id)
);


# 问卷填写的答案
create table  `answer`(
id int primary key auto_increment,
question_id int,
answer varchar(1000),
foreign key (question_id) references question(id)
);
