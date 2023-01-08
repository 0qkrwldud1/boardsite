DROP TABLE IF EXISTS boardsite CASCADE;

CREATE TABLE board(
	bd_num int PRIMARY key NOT NULL auto_increment ,
	bd_title varchar(45) ,
	bd_content varchar(200) ,
	user_ID varchar(45) NOT NULL ,
	bd_category varchar(45),
	bd_viewcnt int DEFAULT 0  NOT NULL ,
	bd_regdate varchar(45)
);

create table user(
	user_ID varchar(45) PRIMARY key NOT NULL ,
	user_PW varchar(45) ,
	user_Name varchar(45) ,
	user_Gender varchar(45) ,
	user_Email varchar(45) 
);

CREATE TABLE board_images (
       Fnum int not null auto_increment,
       fileName varchar(100) ,
       regDate varchar(50),
	   bd_num int,
       PRIMARY KEY (Fnum)
)default CHARSET=utf8;

ALTER TABLE board_images add CONSTRAINT file_fk FOREIGN KEY(bd_num) 
REFERENCES board (bd_num) ON DELETE CASCADE;