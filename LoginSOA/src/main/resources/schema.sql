create table users(
	id number(38) primary key,
	username varchar2(50),
	password varchar2(500));
create table role(
	id number(38) primary key,
	name varchar2(20));
create table users_role(
	users_id number(38),
	role_id number(38));
create sequence users_seq;
create sequence role_seq;
create table yemek(
	id number(38) primary key,
	adi varchar2(50),
	fiyat number(6,2),
	kota number(5)
);
create sequence yemek_seq;
create table users_yemek(
	id number(38) primary key,
	tarih date,
	users_id number(38),
	yemek_id number(38));
create sequence users_yemek_seq;

