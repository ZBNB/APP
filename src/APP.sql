/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - sms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sms`;

/*Table structure for table `api_type` */

DROP TABLE IF EXISTS `api_type`;

CREATE TABLE `api_type` (
  `api_type` varchar(20) DEFAULT NULL,
  `describe` varchar(100) DEFAULT NULL,
  `order` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `api_type` */

insert  into `api_type`(`api_type`,`describe`,`order`) values ('alidayu','阿里大于',1),('webChinese','中国网建',2);

/*Table structure for table `join_record` */

DROP TABLE IF EXISTS `join_record`;

CREATE TABLE `join_record` (
  `jr_id` varchar(32) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `api_type` varchar(20) DEFAULT NULL,
  `app_key` varchar(32) DEFAULT NULL,
  `msg_count` int(11) DEFAULT NULL,
  `is_encode` int(11) DEFAULT NULL,
  `msg_head` varchar(20) DEFAULT NULL,
  `apply_reason` varchar(200) DEFAULT NULL,
  `audit_state` int(11) DEFAULT NULL,
  `op_time` varchar(20) DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  PRIMARY KEY (`jr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `join_record` */

insert  into `join_record`(`jr_id`,`username`,`api_type`,`app_key`,`msg_count`,`is_encode`,`msg_head`,`apply_reason`,`audit_state`,`op_time`,`is_delete`) values ('0feb700e04c04be68519d62e7c62f807','管理员','alidayu','20d1d6e56ba24708a9e734648b772717',10,1,'2','1',1,'2017-04-04 02:21:20',1),('7ef3b6cb9b704de9b6e77e310509eb48','管理员','alidayu','aeca7c15ac6c4e78b460d65ad213a845',10,3,'3','3',1,'2017-04-04 10:40:02',1),('da139e600d0742c8a17bb24be264ca1d','管理员','alidayu','8ee7554863a2457f8765cca2754618a4',10,2,'2','2',1,'2017-04-04 10:40:33',0);

/*Table structure for table `sms_user` */

DROP TABLE IF EXISTS `sms_user`;

CREATE TABLE `sms_user` (
  `ip` varchar(50) DEFAULT NULL,
  `sp_code` varchar(100) DEFAULT NULL,
  `access_code` varchar(100) DEFAULT NULL,
  `gw_name` varchar(50) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  `protocol_type` varchar(10) DEFAULT NULL,
  `op_time` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商接入用户';

/*Data for the table `sms_user` */

/*Table structure for table `tb_info` */

DROP TABLE IF EXISTS `tb_info`;

CREATE TABLE `tb_info` (
  `info_id` int(10) NOT NULL AUTO_INCREMENT,
  `info1` varchar(100) DEFAULT NULL,
  `info2` varchar(100) DEFAULT NULL,
  `info3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `tb_info` */

insert  into `tb_info`(`info_id`,`info1`,`info2`,`info3`) values (1,'a1','a2','a3'),(2,'b1','b2','b3'),(3,'c1','c2','c3'),(4,'d1','d2','d3'),(5,'e1','e2','e3'),(6,'f1','f2','f3'),(7,'g1','g2','g3'),(8,'h1','h2','h3'),(9,'i1','i2','i3'),(10,'j1','j2','j3'),(11,'k1','k2','k3'),(12,'l1','l2','l3'),(13,'m1','m2','m3'),(14,'n1','n2','n3'),(15,'o1','o2','o3'),(16,'p1','p2','p3'),(17,'q1','q2','q3'),(18,'r1','r2','r3'),(19,'s1','s2','s3'),(20,'t1','t2','t3'),(21,'u1','u2','u3'),(22,'v1','v2','v3'),(23,'w1','w2','w3'),(24,'x1','x2','x3'),(25,'y1','y2','y3'),(26,'z1','z2','z3');

/*Table structure for table `tb_menu` */

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `menu_id` int(10) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `menu_url` varchar(100) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `tb_menu` */

insert  into `tb_menu`(`menu_id`,`menu_name`,`menu_url`,`parent_id`) values (1,'系统管理','',NULL),(2,'业务管理','',NULL),(3,'XX管理','',NULL),(5,'用户管理','user.html',1),(6,'角色管理','role.html',1),(7,'菜单管理','menu.html',1),(8,'分页','info.html',2),(9,'http接入','httpAPIJoin.html',2),(10,'分页测试','info.html',3),(11,'dfsdf','',3),(12,'e','',3),(15,'ccc','',2),(16,'YY管理','',NULL),(17,'11111','',16),(18,'22222','',16),(19,'33333','',16),(20,'44444','',16);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  `rights` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`role_id`,`role_name`,`rights`) values (1,'系统管理员','35822'),(2,'普通用户','2038792'),(3,'系统用户','230374');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `rights` varchar(100) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`loginname`,`password`,`username`,`rights`,`status`,`role_id`,`last_login`) values (1,'admin','admin','管理员','38894',0,1,'2017-04-04 10:51:27'),(2,'user1','111111','用户A1a','40716',0,2,NULL),(3,'user2','111111','用户B',NULL,0,NULL,'2011-06-13 15:39:08'),(4,'user3','111111','用户3',NULL,0,NULL,'2011-06-13 15:35:42'),(5,'aaa','1111','aaa',NULL,0,NULL,NULL),(6,'bbb','111111','dsfdsf2',NULL,0,2,NULL),(7,'fffff','1','ddds',NULL,0,NULL,NULL),(10,'abc','111111','dsfdsf2x',NULL,0,NULL,NULL),(11,'user4','111111','afdsf',NULL,0,1,NULL),(12,'test1','111111','aaa',NULL,0,NULL,NULL),(13,'test2','222222','222',NULL,0,NULL,NULL),(14,'tes3','333333','333',NULL,0,NULL,NULL),(15,'test4','222222','somebody',NULL,0,NULL,NULL),(16,'test5','111111','dsfdsf',NULL,0,NULL,NULL),(17,'test6','111111','1',NULL,0,NULL,NULL),(18,'test7','111111','dsfdsf',NULL,0,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
