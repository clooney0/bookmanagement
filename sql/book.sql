/*
Navicat MySQL Data Transfer

Source Server         : servers
Source Server Version : 50623
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2017-10-06 16:25:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `book_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL,
  `book_stock` bigint(255) DEFAULT NULL,
  `book_category` varchar(255) DEFAULT NULL,
  `book_detail` varchar(255) DEFAULT NULL,
  `book_location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `book_stock` (`book_stock`),
  KEY `book_name` (`book_name`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', '时间简史', '20', '科普类', '史蒂芬·霍金', '101');
INSERT INTO `t_book` VALUES ('2', '西游记', '19', '名著类', '吴承恩', '201');
INSERT INTO `t_book` VALUES ('3', '牧羊少年奇幻之旅', '20', '散文类', '柯艾略', null);
INSERT INTO `t_book` VALUES ('4', '我们生活在巨大的差距里', '20', '小说类', ' 余华', null);
INSERT INTO `t_book` VALUES ('5', '沉默的大多数', '20', '散文类', '王小波\r\n\r\n王小波\r\n\r\n王小波', null);
INSERT INTO `t_book` VALUES ('6', '爱你就像爱生命', '20', '小说类', '王小波', null);
INSERT INTO `t_book` VALUES ('7', '我们仨', '20', '小说类', '杨绛', null);
INSERT INTO `t_book` VALUES ('8', '黄帝内经', '20', '散文类', ' 徐文兵 梁冬', null);
INSERT INTO `t_book` VALUES ('9', '摆渡人', '20', '散文类', '克莱儿麦克福尔', null);
INSERT INTO `t_book` VALUES ('10', '黄帝内经', '20', '小说类', ' 徐文兵 梁冬', null);
INSERT INTO `t_book` VALUES ('11', '我与地坛', '20', '散文类', ' 史铁生', null);
INSERT INTO `t_book` VALUES ('12', '野火', '22', '科幻类', ' 龙应台', null);
INSERT INTO `t_book` VALUES ('13', '目送', '20', '小说类', ' 龙应台', null);
INSERT INTO `t_book` VALUES ('14', '沉睡的森林', '20', '科幻类', '东野圭吾', null);
INSERT INTO `t_book` VALUES ('15', '我的晃荡的青春', '20', '科幻类', '东野圭吾', null);
INSERT INTO `t_book` VALUES ('16', '虚无的十字架', '20', '文学类', ' 东野圭吾', null);
INSERT INTO `t_book` VALUES ('17', '至味在人间', '20', '文学类', ' 陈晓卿', null);
INSERT INTO `t_book` VALUES ('18', '按自己的意愿过一生', '20', '科幻类', '王潇', null);
INSERT INTO `t_book` VALUES ('19', '在难搞的日子笑出声来', '20', '科幻类', ' 大鹏', null);
INSERT INTO `t_book` VALUES ('20', '从你的全世界路过', '20', '文学类', '张嘉佳', null);
INSERT INTO `t_book` VALUES ('21', '白说', '20', '科幻类', '白岩松', null);
INSERT INTO `t_book` VALUES ('22', '夜空的星照亮你前行', '20', '文学类', '刘华剑', null);
INSERT INTO `t_book` VALUES ('23', '我喜欢这个功利的世界', '20', '科幻类', ' 咪蒙', null);
INSERT INTO `t_book` VALUES ('24', '美女与野兽', '20', '文学类', '林建', null);
INSERT INTO `t_book` VALUES ('25', '向死而生', '20', '文学类', '李开复', null);
INSERT INTO `t_book` VALUES ('26', '雪国', '20', '名著类', '川端康成', null);
INSERT INTO `t_book` VALUES ('27', '挪威的森林', '20', '艺术类', '村上春树', null);
INSERT INTO `t_book` VALUES ('28', '麦田里的守望者', '20', '艺术类', 'J．D．塞林格', null);
INSERT INTO `t_book` VALUES ('29', '骆驼祥子', '20', '艺术类', '老舍', null);
INSERT INTO `t_book` VALUES ('30', '简·爱', '20', '文学类', '夏洛蒂·勃朗特', null);
INSERT INTO `t_book` VALUES ('31', '鲁滨逊漂流记', '20', '艺术类', '丹尼尔·笛福', null);
INSERT INTO `t_book` VALUES ('32', '雾都孤儿', '20', '文学类', '狄更斯', null);
INSERT INTO `t_book` VALUES ('33', '莎士比亚全集', '20', '艺术类', '莎士比亚', null);
INSERT INTO `t_book` VALUES ('34', '傲慢与偏见', '20', '文学类', '简·奥斯汀', null);
INSERT INTO `t_book` VALUES ('35', '论人生', '20', '历史类', '培根', null);
INSERT INTO `t_book` VALUES ('36', '三个火枪手', '20', '历史类', '大仲马', null);
INSERT INTO `t_book` VALUES ('37', '失乐园', '20', '历史类', '弥尔顿', null);
INSERT INTO `t_book` VALUES ('38', '基督山伯爵', '20', '历史类', '大仲马', null);
INSERT INTO `t_book` VALUES ('39', '茶花女', '20', '文学类', '小仲马', null);
INSERT INTO `t_book` VALUES ('40', '月亮与六便士', '20', '文学类', '毛姆', null);
INSERT INTO `t_book` VALUES ('41', '航天育种简史', '20', '科普类', '郭锐 李军', null);
INSERT INTO `t_book` VALUES ('42', '万物简史', '20', '历史类', '比尔·布莱森', null);
INSERT INTO `t_book` VALUES ('43', '寂静的春天', '20', '科学类', '蕾切尔·卡逊', null);
INSERT INTO `t_book` VALUES ('44', '细胞生命的礼赞', '20', '科学类', '刘易斯·托马斯', null);
INSERT INTO `t_book` VALUES ('45', '心血运动论', '20', '历史类', '威廉·哈维', null);
INSERT INTO `t_book` VALUES ('46', '物种起源', '20', '历史类', '查里·达尔文', null);
INSERT INTO `t_book` VALUES ('47', '自然哲学的数学原理', '20', '历史类', '伊萨克·牛顿', null);
INSERT INTO `t_book` VALUES ('48', '昆虫记', '20', '科学类', '让·亨利·卡西米尔·法布尔', null);
INSERT INTO `t_book` VALUES ('49', '数字化生存', '20', '历史类', '尼葛洛庞帝', null);
INSERT INTO `t_book` VALUES ('50', '天体运行论', '20', '历史类', '哥白尼', null);
INSERT INTO `t_book` VALUES ('65', 'JSP实用教程', '30', '计算机类', '耿祥义 张跃平 著\n清华大学出版社', '3F-3d4c');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `log_username` varchar(255) DEFAULT NULL,
  `log_bkname` varchar(255) DEFAULT NULL,
  `log_bkstock` bigint(255) DEFAULT NULL,
  `log_brotime` varchar(255) DEFAULT NULL,
  `log_rtntime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `log_bkstock` (`log_bkstock`),
  KEY `t_log_book_name` (`log_bkname`),
  KEY `t_log_user_name` (`log_username`),
  CONSTRAINT `t_log_book_name` FOREIGN KEY (`log_bkname`) REFERENCES `t_book` (`book_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_log_user_name` FOREIGN KEY (`log_username`) REFERENCES `t_user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', 'eddie', '时间简史', '20', '2017-9-8', '2017-9-10');
INSERT INTO `t_log` VALUES ('13', 'amy', '时间简史', '20', '2017-09-17 13:01', '');
INSERT INTO `t_log` VALUES ('77', 'uu', '西游记', '19', '2017-09-19 22:21', '2017-09-19 22:21');

-- ----------------------------
-- Table structure for t_root
-- ----------------------------
DROP TABLE IF EXISTS `t_root`;
CREATE TABLE `t_root` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `root_name` varchar(255) DEFAULT NULL,
  `root_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_root
-- ----------------------------
INSERT INTO `t_root` VALUES ('1', 'root', 'root');
INSERT INTO `t_root` VALUES ('2', 'admin', '123');
INSERT INTO `t_root` VALUES ('3', 'tom', 'tom');
INSERT INTO `t_root` VALUES ('4', 'rose', 'rose');
INSERT INTO `t_root` VALUES ('5', 'uuu', 'uuu');
INSERT INTO `t_root` VALUES ('7', 'rain', '123');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_gender` varchar(5) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone` (`user_phone`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'eddie', '男', '13387676383');
INSERT INTO `t_user` VALUES ('2', 'amy', '女', '13400973678');
INSERT INTO `t_user` VALUES ('4', 'uu', '男', '13522442265');
INSERT INTO `t_user` VALUES ('8', 'tom', '女', '13111333524');
INSERT INTO `t_user` VALUES ('19', 'judy', '男', '13888598562');
INSERT INTO `t_user` VALUES ('20', 'sany', '女', '13888598222');
INSERT INTO `t_user` VALUES ('25', 'alyson', '女', '15365245895');
