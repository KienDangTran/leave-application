
use leave_application_db;

alter table `MT_ROLE_PERMISSION_GRANTED` drop foreign key `FK_ROLE_PERMISSION_GRANTED_ROLE`;
alter table `MT_ROLE_PERMISSION_GRANTED` drop foreign key `FK_ROLE_PERMISSION_GRANTED_PERMISSION`;
drop table if exists `MT_ROLE_PERMISSION_GRANTED`;

drop table if exists `MT_PERMISSION`;

alter table `MT_USER_ROLE` drop foreign key `FK_USER_ROLE_USER`;
alter table `MT_USER_ROLE` drop foreign key `FK_USER_ROLE_ROLE`;
drop table if exists `MT_USER_ROLE`;

drop table if exists `MT_ROLE`;

alter table `MT_PERSISTENT_LOGIN` drop foreign key `FK_PERSISTENT_LOGIN_USER`;
drop table if exists `MT_PERSISTENT_LOGIN`;

alter table `MT_USER` drop foreign key `FK_ROLE_EMPLOYEE`;
drop table if exists `MT_USER`;

drop table if exists `MT_EMPLOYEE`;
