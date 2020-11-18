
--
create table robinsonjdbc.users(
 userId serial primary key not null,
 firstname varchar(30),
 lastname varchar(30),
 username varchar(30) unique,
 password varchar(30),
 admin boolean not null default false
 
 );


-- add accounts table
create table accounts (
 accountId serial primary key not null,
 acct_opened date,
 balance numeric,
 active boolean not null default true,
 );




--relationship table
create table user_accounts(
userId integer,
accountId integer,
foreign key(userId) references users(userId),
foreign key(accountId) references accounts(accountId) 
on delete cascade
);

alter table if exists users
alter column "userId" set not null;

alter table if exists users
add unique(username);
 
 
select * from accounts;
select * from user_accounts;
select * from robinsonjdbc.users;