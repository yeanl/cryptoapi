Pre-insert the DB 
insert into customer (email, name) values ('abc@gmail.com', 'alice tan');
insert into wallet(customer_id, balance) values (1, 50000);
update wallet set balance=50000 where id=1

select * from price_agg;
select * from customer;
select * from wallet;
select * from trade;
select * from cryto_type;


http://localhost:8080/h2-console/login.jsp
