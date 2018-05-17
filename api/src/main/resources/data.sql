/*
    INSERT USER
*/
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1000,'Michal','Krolewski','michal.krolewski@mail.com','password','ADMIN');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1001,'Piotr','Gorczyca','piotr.gorczyca@mail.com','password','ADMIN');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1002,'Marta','Miler','marta.miler@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1003,'Piotr','Gazda','piotr.gazda@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1004,'Olaf','Kris','olaf.kris@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1005,'Mateusz','Krzyzanowski','mateusz.krzyzanowski@mail.com','password','USER');

/*
    INSERT PHOTO
*/
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email) VALUES (1001,'photo1_description','tlo.jpg',0,0,'18-04-21 10:34:09','michal.krolewski@mail.com');
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1002,'photo2_description','photo2','C:\photos\path',1,1,'18-02-27 10:34:09','michal.krolewski@mail.com');
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1003,'photo3_description','photo3','C:\photos\path',1,1,'18-03-21 10:34:09',2);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1004,'photo4_description','photo4','C:\photos\path',1,0,'18-04-21 10:34:09',3);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1005,'photo5_description','photo5','C:\photos\path',0,1,'18-01-12 10:34:09',3);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1006,'photo6_description','photo6','C:\photos\path',1,0,'18-02-23 10:34:09',4);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1007,'photo7_description','photo7','C:\photos\path',1,1,'18-04-16 10:34:09',5);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1008,'photo8_description','photo8','C:\photos\path',1,0,'18-03-05 10:34:09',5);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_email) VALUES (1009,'photo9_description','photo9','C:\photos\path',1,1,'18-04-11 10:34:09',6);

/*
    INSERT CATEGORY
*/
INSERT INTO category (id,name,parent,user_email) VALUES (1001,'holidays',null,'michal.krolewski@mail.com');
INSERT INTO category (id,name,parent,user_email) VALUES (1002,'Egypt','holidays','michal.krolewski@mail.com');
INSERT INTO category (id,name,parent,user_email) VALUES (1003,'sport',null,2);
INSERT INTO category (id,name,parent,user_email) VALUES (1004,'flowers',null,3);
INSERT INTO category (id,name,parent,user_email) VALUES (1005,'anime',null,4);
INSERT INTO category (id,name,parent,user_email) VALUES (1006,'animals',null,5);
INSERT INTO category (id,name,parent,user_email) VALUES (1007,'cars',null,6);

/*
    INSERT PHOTO TO CATEGORY
*/

INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1001,'holidays',1001);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1002,'Egypt',1002);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1003,'sport',1003);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1004,'flowers',1004);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1005,'flowers',1005);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1006,'anime',1006);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1007,'animals',1007);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1008,'animals',1008);
INSERT INTO photo_to_category (id,category_name,photo_id) VALUES (1009,'cars',1009);


/*
    INSERT TAG
*/
INSERT INTO tag (id,name,photo_id) VALUES (1001,'wpolishboy',1001);
INSERT INTO tag (id,name,photo_id) VALUES (1002,'wholidays',1001);
INSERT INTO tag (id,name,photo_id) VALUES (1003,'sunrise',1001);

/*
    INSERT SHARE
*/
INSERT INTO share (id,photo_id,user_email) VALUES (1001,1001,2);
INSERT INTO share (id,photo_id,user_email) VALUES (1003,1005,6);
INSERT INTO share (id,photo_id,user_email) VALUES (1002,1006,'michal.krolewski@mail.com');
INSERT INTO share (id,photo_id,user_email) VALUES (1004,1007,'michal.krolewski@mail.com');
INSERT INTO share (id,photo_id,user_email) VALUES (1005,1008,'michal.krolewski@mail.com');
INSERT INTO share (id,photo_id,user_email) VALUES (1006,1009,'michal.krolewski@mail.com');

/*
    INSERT RATE
*/
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1001,'18-04-21 10:34:09',9,1001,2);
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1002,'18-03-12 10:34:09',4,1001,6);
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1003,'18-03-12 10:34:09',7,1002,6);
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1004,'18-03-12 10:34:09',8,1002,2);
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1005,'18-03-12 10:34:09',4,1004,4);
INSERT INTO rate (id,datetime,rate,photo_id,user_email) VALUES (1006,'18-03-12 10:34:09',2,1006,6);


