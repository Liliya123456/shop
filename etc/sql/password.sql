alter table users add column password VARCHAR(200);
update users set password = '{MD5}'|| MD5(id);
ALTER TABLE users ALTER COLUMN password SET NOT NULL;
