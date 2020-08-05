insert into EVENT_TYPE_TAB (EVENT_TYPE_ID, EVENT_TYPE)
values (101, 'Transactions');
insert into EVENT_TYPE_TAB (EVENT_TYPE_ID, EVENT_TYPE)
values (102, 'Custom');

insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1001, 'Rent Advance', 101, sysdate, 'Hemant');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1002, 'payment car', 102, sysdate, 'Steve');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1003, 'Groceries', 101, sysdate, 'Steve');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1004, 'Boating', 101, sysdate, 'Sharad');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1005, 'complex 123434', 102, sysdate, 'Sharad');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1006, 'bike xxxx', 101, sysdate, 'Hemant');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1007, 'Random stuff 1', 101, sysdate, 'Steve');
insert into EVENT_TAB (EVENT_ID, DESCRIPTION, EVENT_TYPE_ID, EVENT_DATE, CUSTOMER_NAME)
values (1008, 'Random stuff 2', 101, sysdate, 'Sharad');
