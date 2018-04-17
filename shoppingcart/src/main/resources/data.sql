insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S001', 'Core Java', 100, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S002', 'Spring for Beginners', 50, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S003', 'Swift for Beginners', 120, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S004', 'Oracle XML Parser', 120, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S005', 'CSharp Tutorial for Beginers', 110, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S006', 'Java Black Book', 200, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S007', 'Java9 for Programmers', 250, SYSTIMESTAMP() );
insert into products (CODE, NAME, PRICE, CREATE_DATE) values ('S008', 'HeadFirst Java', 50, SYSTIMESTAMP() );

insert into users (ID, USERNAME, PASSWORD, authority) values (1, 'user', 'password', 'ROLE_USER');
insert into users (ID, USERNAME, PASSWORD, authority) values (2, 'admin', 'password', 'ROLE_ADMIN');
insert into users (ID, USERNAME, PASSWORD, authority) values (3, 'su', 'password', 'ROLE_SUPERUSER');