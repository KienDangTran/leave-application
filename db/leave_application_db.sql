
use leave_application_db;
/*-------------------------------------------------------------------- CREATE TABLE --------------------------------------------------------------*/

create table if not exists `MT_ROLE` (
    `ROLE_CODE` varchar(50) not null,
    `ROLE_DESC` text,
    `STATUS` char(3),
    
    primary key (`ROLE_CODE`)
    
)  engine=innodb;

create table if not exists `MT_PERMISSION` (
	`PERMISSION_CODE` varchar(50),
    `PERMISSION_DESC` text,
    `STATUS` char(3),
    
    primary key (`PERMISSION_CODE`)
    
) engine = innodb;

create table if not exists `MT_ROLE_PERMISSION_GRANTED` (
    `ROLE_CODE` varchar(50) not null,
    `PERMISSION_CODE` varchar(50)  not null,
    `STATUS` char(3),
    
    primary key (`ROLE_CODE`, `PERMISSION_CODE`),
    
    constraint `FK_ROLE_PERMISSION_GRANTED_ROLE` foreign key (`ROLE_CODE`)
        references `leave_application_db`.`MT_ROLE` (`ROLE_CODE`)
        on delete restrict on update restrict,
	
    constraint `FK_ROLE_PERMISSION_GRANTED_PERMISSION` foreign key (`PERMISSION_CODE`)
        references `leave_application_db`.`MT_PERMISSION` (`PERMISSION_CODE`)
        on delete restrict on update restrict
        
) engine = innodb;

create table if not exists `MT_EMPLOYEE` (
    `EMPLOYEE_CODE` varchar(50) not null,
    `EMPLOYEE_NAME` varchar(50) not null,
    `DATE_OF_BIRTH` date default null,
    `EMAIL` varchar(50) default null,
    `PHONE_NO` varchar(20) default null,
    `STATUS` char(3),
    
    primary key (`EMPLOYEE_CODE`)
    
)  engine=innodb;

create table if not exists `MT_USER` (
	`USER_ID` varchar(50) not null,
	`USERNAME` varchar(50) not null,
	`PASSWORD` varchar(256) not null,
    `EMPLOYEE_CODE` varchar(50) not null,
    `STATUS` char(3),
	
	primary key (`USER_ID`),
    
    constraint `FK_ROLE_EMPLOYEE` foreign key (`EMPLOYEE_CODE`)
        references `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`)
        on delete cascade on update restrict
        
) engine = innodb;

create table if not exists `MT_USER_ROLE` (
	`USER_ID` varchar(50) not null,
	`ROLE_CODE` varchar(50) not null,
    `STATUS` char(3),
    
    primary key (`USER_ID`, `ROLE_CODE`),
    
    constraint `FK_USER_ROLE_USER` foreign key (`USER_ID`)
        references `leave_application_db`.`MT_USER` (`USER_ID`)
        on delete cascade on update restrict,
        
	constraint `FK_USER_ROLE_ROLE` foreign key (`ROLE_CODE`)
        references `leave_application_db`.`MT_ROLE` (`ROLE_CODE`)
        on delete cascade on update restrict
        
);