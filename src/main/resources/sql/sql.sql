CREATE TABLE mojohao.USER_INFO
(
USER_ID INT NOT NULL AUTO_INCREMENT comment '用户ID',
USER_STATE  ENUM('ON', 'OFF') DEFAULT 'ON' comment '用户状态',
USER_TYPE varchar(12) NOT NULL comment '用户类型',
USER_NAME varchar(20) NOT NULL comment '用户名',
USER_ADDRESS varchar(60) NOT NULL comment '用户地址',
USER_ELEM_CODE varchar(20) NOT NULL comment '电能表编号',
USER_AREA_CODE varchar(20) NOT NULL comment '电能表台区编号',
PRIMARY KEY (USER_ID)
) comment='用户档案' ;
CREATE TABLE mojohao.USER_ELE_DATA
(
DATA_ID INT NOT NULL AUTO_INCREMENT comment '数据ID',
USER_ID INT NOT NULL comment '用户ID',
COLLECT_TIME timestamp NOT NULL comment '抄表日期',
ELEM_ID varchar(20) NOT NULL comment '电能表编号',
ELEM_DATA double NOT NULL comment '电能表数据',
PRIMARY KEY (DATA_ID)
) comment='采集电能表数据信息';
CREATE TABLE mojohao.API_LIST
(
API_ID INT NOT NULL AUTO_INCREMENT comment '接口ID',
API_NAME varchar(20) NOT NULL comment '接口名称',
API_TYPE ENUM('REST', 'SOAP') NOT NULL comment '接口类别',
API_LINK varchar(60) NOT NULL comment '接口地址',
ACCESS_MODE ENUM('GET', 'POST','PUT','DELETE','SOAP') NOT NULL comment '访问方式',
API_INFO varchar(60) NOT NULL comment '接口说明',
PRIMARY KEY (API_ID)
) comment='接口服务信息';
CREATE TABLE mojohao.TEST_CASE
(
CASE_ID INT NOT NULL AUTO_INCREMENT comment '用例ID',
CASE_LIB_ID INT NOT NULL comment '所属用例库ID',
CASE_NAME varchar(20) NOT NULL comment '用例名称',
CASE_PARA_TYPE ENUM('XML', 'JSON') NOT NULL comment '用例参数类别',
PARAMETER TEXT comment '输入',
DESIRED_RESPONSE TEXT  comment '期望输出',
CASE_INFO varchar(60) NOT NULL comment '用例说明',
PRIMARY KEY (CASE_ID)
) comment='测试用例';
CREATE TABLE mojohao.TEST_CASE_LIB
(
LIB_ID INT NOT NULL AUTO_INCREMENT comment '用例库ID',
LIB_NAME varchar(20) NOT NULL comment '用例库名称',
LIB_INFO varchar(60) comment '用例库简介',
APPLY_API_ID INT NOT NULL comment '适用接口ID',
PRIMARY KEY (LIB_ID)
) comment='测试用例库';
CREATE TABLE mojohao.TEST_RESULT
(
RESULT_ID INT NOT NULL auto_increment COMMENT '结果编号',
TEST_ID varchar(36) NOT NULL COMMENT '测试编号',
TEST_PLAN_ID INT NOT NULL COMMENT '测试计划编号',
CASE_ID INT NOT NULL COMMENT '用例编号',
TEST_DATE TIMESTAMP NOT NULL COMMENT '测试时间',
DESIRED_RESPONSE TEXT  comment '期望输出',
ACTUAL_RESPONSE TEXT  comment '实际输出',
ASSERTION ENUM('PASSED', 'UNPASSED') comment '断言',
PRIMARY KEY (RESULT_ID)
) comment='测试结果';
CREATE TABLE mojohao.TEST_PLAN
(
TEST_PLAN_ID INT NOT NULL AUTO_INCREMENT COMMENT '测试计划编号',
API_ID INT NOT NULL comment '接口ID',
LIB_ID INT NOT NULL comment '用例库ID',
CASE_ID TEXT COMMENT '用例编号集',
PLAN_NAME varchar(20) NOT NULL COMMENT '测试计划名称',
PLAN_STATUS int(2) NOT NULL COMMENT '计划执行状态',
PRIMARY KEY (TEST_PLAN_ID)
) comment='测试计划';
