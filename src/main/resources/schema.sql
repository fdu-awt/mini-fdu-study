SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `quiz`
-- ----------------------------

DROP TABLE IF EXISTS `quiz`;
CREATE TABLE `quiz`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `topic`    VARCHAR(255) NOT NULL COMMENT '知识的类型，目前有club或history',
    `data_id`  INT          NOT NULL COMMENT '关联的知识对应的前端数据集中id',
    `question` VARCHAR(255) NOT NULL COMMENT '测试问题',
    `options`  VARCHAR(255) NOT NULL COMMENT '每个问题有若干个可选答案（默认为4个），答案以"|"分割，如 "1902年|1905年|1911年|1908年"',
    `answer`   VARCHAR(255) NOT NULL COMMENT '正确答案，如"1902年"',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for `quiz_record`
-- ----------------------------

DROP TABLE IF EXISTS `quiz_record`;
CREATE TABLE `quiz_record`
(
    `id`               INT          NOT NULL AUTO_INCREMENT,
    `user_id`          INT          NOT NULL,
    `quiz_id`          INT          NOT NULL,
    `answer`           VARCHAR(255) NOT NULL COMMENT '用户回答',
    `is_correct`       BOOLEAN      NOT NULL COMMENT '该次回答是否正确',
    `create_timestamp` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '答题时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`quiz_id`) REFERENCES quiz (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Table structure for `history`
-- ----------------------------

DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`
(
    `id`    INT          NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `intro` TEXT         NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;