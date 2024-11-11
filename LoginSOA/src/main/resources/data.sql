insert into users values (users_seq.nextval,'ali','ali');
insert into users values (users_seq.nextval,'veli','veli');
insert into users values (users_seq.nextval,'zeynep','zeynep');

insert into role values (role_seq.nextval,'kullanici');
insert into role values (role_seq.nextval,'yonetici');

insert into users_role values (
	(select id from users where username='ali'),
	(select id from role where name='kullanici')
);

insert into users_role values (
	(select id from users where username='veli'),
	(select id from role where name='kullanici')
);

insert into users_role values (
	(select id from users where username='zeynep'),
	(select id from role where name='yonetici')
);

insert into yemek values (yemek_seq.nextval,'Kuru Fasulye', 23.5, 5);
insert into yemek values (yemek_seq.nextval,'Ayşe Kadın Fasulye', 45.9, 12);
insert into yemek values (yemek_seq.nextval,'Kadun Budu Köfte', 68.3, 3);
