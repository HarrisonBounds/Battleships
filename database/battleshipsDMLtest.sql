insert into users
	values('jsmith@uca.edu', aes_encrypt('hello123', 'key'), 1, 2);
insert into users
	values('msmith@uca.edu', aes_encrypt('pass123', 'key'), 5, 0);
insert into users
	values('tjones@yahoo.com', aes_encrypt('123456', 'key'), 3, 0);
insert into users
	values('jjones@yahoo.com', aes_encrypt('hello1234', 'key'), 2, 1);
