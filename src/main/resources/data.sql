INSERT INTO PERSON (id,name) VALUES (1,'test');
INSERT INTO PERSON (id,name) VALUES (2,'test 2');
INSERT INTO PERMISSION (id, description) VALUES (1,'ADMIN');
INSERT INTO PERMISSION (id, description) VALUES (2,'USER');
INSERT INTO PERMISSION (id, description) VALUES (3,'COMMON_USER');
INSERT INTO users (id, user_name,full_name,password,account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (1,'Breno', 'Breno Pereira', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', true, true, true, true);
INSERT INTO users (id, user_name,full_name,password,account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (2,'Teste', 'Teste teste', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', true, true, true, true);
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 1);
INSERT INTO user_permission (id_user, id_permission) VALUES (2, 1);
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 2);
 
	
