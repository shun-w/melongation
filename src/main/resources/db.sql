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


insert into `user` values
(1, '李四','1234567','','321@qq.com',1),
(2, '王五','22345678','','233@qq.com',1);


insert into `admin` values
(1,'张三','12345678','','123@qq.com');


-- 初始化一张问卷
-- 发布人：李四
-- 标题：关于睡眠情况调查
-- 描述：想当今大学生睡眠情况
-- 创建时间：2018-11-22 12:23:32
-- 提交数量:2
-- 是否审核通过：是
-- 问题：
--
-- 1.你现在几年级（单选，必填）
-- A 大一
-- B 大二
-- C 大三
-- D 大四
--
-- 2.睡前喜欢做什么（多选）
-- A 玩手机
-- B 玩电脑
-- C 看书
--
-- 3.你对当今大学生睡眠问题有什么看法（文本，可选）

-- 问卷填写
-- 1. A  AB  没有看法
-- 2. C  AC  应该好好睡觉


-- 插入问卷
insert into `paper`
values (1, 1, '关于睡眠情况调查','想当今大学生睡眠情况','2018-11-22 12:23:32',2,1);

-- 插入问卷内容
insert into question
values (1, 1, 1, '{"你现在几年级":[{"A":"大一"},{"B":"大二"},{"C":"大三"},{"C":"大四"}]}',1,1 );
insert into question
values (2, 1, 2, '{"睡前喜欢做什么":[{"A":"玩手机"},{"B":"玩电脑"},{"C":"学习"}]}',1,2 );
insert into question
values (3, 1, 3, '你对当今大学生睡眠问题有什么看法',0,3 );


-- 插入问卷填写
insert into answer
values (null,1,'A');
insert into answer
values (null,2,'AB');
insert into answer
values (null,3,'没有看法');

insert into answer
values (null,1,'C');
insert into answer
values (null,2,'AC');
insert into answer
values (null,3,'应该好好睡觉');

