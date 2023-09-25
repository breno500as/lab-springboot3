INSERT INTO PERSON (id,name) VALUES (1,'test');
INSERT INTO PERSON (id,name) VALUES (2,'test 2');
INSERT INTO PERMISSION (id, description) VALUES (1,'ADMIN');
INSERT INTO PERMISSION (id, description) VALUES (2,'USER');
INSERT INTO PERMISSION (id, description) VALUES (3,'COMMON_USER');
INSERT INTO users (id, user_name,full_name,password,account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (1,'Breno', 'Breno Pereira', '87126b1c928969f3d8bebeb98a90986b30fa3bc5cb3fcf96ee20e9dba093bcc67d4b0a62bebe3580', true, true, true, true);
INSERT INTO users (id, user_name,full_name,password,account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES (2,'Teste', 'Teste teste', '87126b1c928969f3d8bebeb98a90986b30fa3bc5cb3fcf96ee20e9dba093bcc67d4b0a62bebe3580', true, true, true, true);
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 1);
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 2);
INSERT INTO user_permission (id_user, id_permission) VALUES (1, 3);
INSERT INTO user_permission (id_user, id_permission) VALUES (2, 3);
 
	
