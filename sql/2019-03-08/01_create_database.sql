/*==============================================================*/
/* Database: hx                                           */
/*==============================================================*/
create database hx DEFAULT CHARACTER SET utf8mb4;
create user hx IDENTIFIED BY 'hx';
GRANT ALL PRIVILEGES  ON hx.* TO 'hx'@'%';
grant all privileges on hx.* to hx@'localhost' identified by 'hx';
flush privileges;