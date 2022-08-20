drop database if exists customer_db;

create database customer_db;

use customer_db;

create table customer (
    customer_id int not null,
    emailid varchar(20),
    name varchar(20),
    date_of_birth date,
    constraint pk_customer primary key (customer_id)
);
create table loan (
    loan_id int not null auto_increment,
    amount double precision,
    issue_date date,
    cust_id int,
    status varchar(10),
    constraint pk_loan primary key (loan_id),
    constraint fk_cust_loan foreign key (cust_id) references customer(customer_id)
);



insert into customer values (1001,'steven@barclays.com', 'Steven Martin', sysdate()-interval 7476 day);
insert into customer values (1002,'kevin@barclays.com', 'Kevin Nelson', sysdate()-interval 11374 day);
insert into customer values(1003,'john@barclays.com', 'John Matric', sysdate()-interval 12344 day);
insert into customer values (1004,'chan@barclays.com', 'Chan Mathew', sysdate()-interval 10344 day);
insert into customer values(1005,'jill@barclays.com', 'Jill Mathew', sysdate()-interval 11374 day);

insert into loan values (2001,612345,sysdate()-interval 1000 day,1001,'Open');
insert into loan values (2002,2289073,sysdate()-interval 500 day,1001,'Closed');
insert into loan values (2003,109376289,sysdate()-interval 800 day,1001,'Open');
insert into loan values (2005,99027309,sysdate()-interval 2345 day,1004,'Open');

commit;

select * from loan;
select * from customer;

