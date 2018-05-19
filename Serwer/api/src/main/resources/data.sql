/*
    INSERT USER
*/
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1000,'Michal','Krolewski','michal.krolewski@mail.com','password',1);
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1001,'Piotr','Gorczyca','piotr.gorczyca@mail.com','password',0);
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1002,'Marta','Miler','marta.miler@mail.com','password',0);
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1003,'Piotr','Gazda','piotr.gazda@mail.com','password',0);
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1004,'Olaf','Kris','olaf.kris@mail.com','password',0);
INSERT INTO user (id,first_name,last_name,email,password,role) VALUES (1005,'Mateusz','Krzyzanowski','mateusz.krzyzanowski@mail.com','password',0);

/*
    INSERT PHOTO
*/
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1001,'photo1_description','tlo.jpg',1,0,'18-04-21 10:34:09','michal.krolewski@mail.com',1000);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1002,'photo2_description','photo2',1,1,'18-02-27 10:34:09','michal.krolewski@mail.com',1000);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1003,'photo3_description','photo3',1,1,'18-03-21 10:34:09','piotr.gorczyca@mail.com',1001);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1004,'photo4_description','photo4',1,0,'18-04-21 10:34:09','marta.miler@mail.com',1002);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1005,'photo5_description','photo5',0,1,'18-01-12 10:34:09','marta.miler@mail.com',1002);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1006,'photo6_description','photo6',1,0,'18-02-23 10:34:09','piotr.gazda@mail.com',1003);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1007,'photo7_description','photo7',1,1,'18-04-16 10:34:09','olaf.kris@mail.com',1004);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1008,'photo8_description','photo8',1,0,'18-03-05 10:34:09','olaf.kris@mail.com',1004);
INSERT INTO photo (id,description,name,photo_state,share_state,upload_time,user_email,user_id) VALUES (1009,'photo9_description','photo9',1,1,'18-04-11 10:34:09','mateusz.krzyzanowski@mail.com',1005);

/*
    INSERT CATEGORY
*/
INSERT INTO category (id,name,parent_id,user_id) VALUES (1001,'holidays',null,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1002,'Egypt',1001,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1008,'Italy',1001,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1009,'Poland',1001,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1010,'sea',1002,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1011,'school',null,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1012,'exams',1011,1000);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1003,'sport',null,1002);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1004,'flowers',null,1003);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1005,'anime',null,1004);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1006,'animals',null,1005);
INSERT INTO category (id,name,parent_id,user_id) VALUES (1007,'cars',null,1006);

/*
    INSERT PHOTO TO CATEGORY
*/
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1001,1001,1001);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1002,1002,1002);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1003,1003,1003);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1004,1004,1004);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1005,1004,1005);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1006,1005,1006);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1007,1006,1007);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1008,1006,1008);
INSERT INTO photo_to_category (id,category_id,photo_id) VALUES (1009,1007,1009);


/*
    INSERT TAG
*/
INSERT INTO tag (id,name,photo_id,user_id) VALUES (1001,'wpolishboy',1002,1000);
INSERT INTO tag (id,name,photo_id,user_id) VALUES (1002,'wholidays',1002,1000);
INSERT INTO tag (id,name,photo_id,user_id) VALUES (1003,'sunrise',1005,1002);

/*
    INSERT SHARE
*/
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1001,1001,1001,1000);
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1003,1005,1005,1002);
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1002,1006,1000,1003);
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1004,1007,1000,1004);
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1005,1008,1000,1004);
INSERT INTO share (id,photo_id,user_id,owner_id) VALUES (1006,1009,1000,1005);

/*
    INSERT RATE
*/
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1001,'18-04-21 10:34:09',9,1001,1001);
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1002,'18-03-12 10:34:09',4,1001,1005);
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1003,'18-03-12 10:34:09',7,1002,1005);
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1004,'18-03-12 10:34:09',8,1002,1001);
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1005,'18-03-12 10:34:09',4,1004,1003);
INSERT INTO rate (id,date,rate,photo_id,user_id) VALUES (1006,'18-03-12 10:34:09',2,1006,1005);


