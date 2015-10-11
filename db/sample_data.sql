-- SAMPLE DATA

-- EMPLOYEE
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('SYS_ADMIN', 'SYSTEM ADMINISTRATOR', null, 'kiendang91@gmail.com', null, 'A');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('ADMIN', 'ADMINISTRATOR', '1991-09-12', 'kiendang91@gmail.com', '0936358833', 'A');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('OFFICER', 'APPROVING OFIFCER', null, 'kiendang91@gmail.com', null, 'A');
insert into `leave_application_db`.`MT_EMPLOYEE` (`EMPLOYEE_CODE`, `EMPLOYEE_NAME`, `DATE_OF_BIRTH`, `EMAIL`, `PHONE_NO`, `STATUS`) values ('EMPLOYEE', 'EMPLOYEE', null, 'kiendang91@gmail.com', null, 'A');

-- User
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `STATUS`) VALUES ('U000000000', 'root', 'cac6ca9fa7c0701bed106b841cf5e02f', 'SYS_ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `STATUS`) VALUES ('U000000001', 'admin', '434d711276d2ed2068e7a02499fac15c', 'ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `STATUS`) VALUES ('U000000002', 'officer', '1a3d3cd03406213db5a90ebe91b401fc', 'OFFICER', 'A');
INSERT INTO `leave_application_db`.`MT_USER` (`USER_ID`, `USERNAME`, `PASSWORD`, `EMPLOYEE_CODE`, `STATUS`) VALUES ('U000000003', 'employee', '8ec98f824254352be86a1adb53070504', 'EMPLOYEE', 'A');

-- Role
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('SYS_ADMIN', 'System Administrator', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('ADMIN', 'Administrator', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('OFFICER', 'Approving Ofifcer', 'A');
insert into `leave_application_db`.`MT_ROLE` (`ROLE_CODE`, `ROLE_DESC`, `STATUS`) values ('EMPLOYEE', 'Employee', 'A');

-- User-Role
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000000', 'SYS_ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000001', 'ADMIN', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000002', 'OFFICER', 'A');
INSERT INTO `leave_application_db`.`MT_USER_ROLE` (`USER_ID`, `ROLE_CODE`, `STATUS`) VALUES ('U000000003', 'EMPLOYEE', 'A');

