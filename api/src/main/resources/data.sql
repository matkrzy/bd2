/*
    INSERT USER
*/
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1,'Michal','Krolewski','michal.krolewski@mail.com','password','ADMIN');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (2,'Piotr','Gorczyca','piotr.gorczyca@mail.com','password','ADMIN');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (3,'Marta','Miler','marta.miler@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (4,'Piotr','Gazda','piotr.gazda@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (5,'Olaf','Kris','olaf.kris@mail.com','password','USER');
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (6,'Mateusz','Krzyzanowski','mateusz.krzyzanowski@mail.com','password','USER');

/*
    INSERT PHOTO
*/
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (1,'photo1_description','photo1','C:\photos\path',0,1,'18-04-21 10:34:09',1);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (2,'photo2_description','photo2','C:\photos\path',1,1,'18-02-27 10:34:09',1);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (3,'photo3_description','photo3','C:\photos\path',1,1,'18-03-21 10:34:09',2);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (4,'photo4_description','photo4','C:\photos\path',1,0,'18-04-21 10:34:09',3);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (5,'photo5_description','photo5','C:\photos\path',0,1,'18-01-12 10:34:09',3);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (6,'photo6_description','photo6','C:\photos\path',1,1,'18-02-23 10:34:09',4);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (7,'photo7_description','photo7','C:\photos\path',1,1,'18-04-16 10:34:09',5);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (8,'photo8_description','photo8','C:\photos\path',1,0,'18-03-05 10:34:09',5);
INSERT INTO photo (id,description,name,path,photo_state,share_state,upload_time,user_id) VALUES (9,'photo9_description','photo9','C:\photos\path',1,1,'18-04-11 10:34:09',6);

/*
    INSERT CATEGORY
*/
INSERT INTO category (id,name,parent_id,user_id) VALUES (1,'holidays',null,1);
INSERT INTO category (id,name,parent_id,user_id) VALUES (2,'Egypt',1,1);
INSERT INTO category (id,name,parent_id,user_id) VALUES (3,'sport',null,2);
INSERT INTO category (id,name,parent_id,user_id) VALUES (4,'flowers',null,3);
INSERT INTO category (id,name,parent_id,user_id) VALUES (5,'anime',null,4);
INSERT INTO category (id,name,parent_id,user_id) VALUES (6,'animals',null,5);
INSERT INTO category (id,name,parent_id,user_id) VALUES (7,'cars',null,6);

/*
    INSERT PHOTO TO CATEGORY
*/

INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1,1,1);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (2,2,2);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (3,3,3);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (4,4,4);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (5,4,5);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (6,5,6);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (7,6,7);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (8,6,8);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (9,7,9);


/*
    INSERT TAG
*/
INSERT INTO tag (id,name,photo_id) VALUES (1,'polishboy',1);
INSERT INTO tag (id,name,photo_id) VALUES (2,'holidays',1);
INSERT INTO tag (id,name,photo_id) VALUES (3,'sunrise',1);

/*
    INSERT SHARE
*/
INSERT INTO share (id,photo_id,user_id) VALUES (1,1,2);
INSERT INTO share (id,photo_id,user_id) VALUES (2,6,1);
INSERT INTO share (id,photo_id,user_id) VALUES (3,5,6);

/*
    INSERT RATE
*/
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (1,'18-04-21 10:34:09',9,1,2);
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (2,'18-03-12 10:34:09',4,1,6);
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (3,'18-03-12 10:34:09',7,2,6);
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (4,'18-03-12 10:34:09',8,2,2);
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (5,'18-03-12 10:34:09',4,4,4);
INSERT INTO rate (id,datetime,rate,photo_id,user_id) VALUES (6,'18-03-12 10:34:09',2,6,6);


