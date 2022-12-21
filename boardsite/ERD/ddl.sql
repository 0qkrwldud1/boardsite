DROP TABLE IF EXISTS boardsite CASCADE;

CREATE TABLE board(
	bd_num int PRIMARY key NOT NULL auto_increment ,
	bd_title varchar(45) ,
	bd_content varchar(200) ,
	user_ID varchar(45) NOT NULL ,
	bd_viewcnt int DEFAULT 0 CHECK (bk_viewcnt >= 0) NOT NULL ,
	bd_regdate datetime DEFAULT now()
);

create table user(
	user_ID varchar(45) PRIMARY key NOT NULL ,
	user_PW varchar(45) ,
	user_Name varchar(45) ,
	user_Gender varchar(45) ,
	user_Email varchar(45) 
);

