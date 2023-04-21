insert into user
	values('jsmith@uca.edu', aes_encrypt('hello123', 'key'), 0, 0);
insert into user
	values('msmith@uca.edu', aes_encrypt('pass123', 'key'), 0, 0);
