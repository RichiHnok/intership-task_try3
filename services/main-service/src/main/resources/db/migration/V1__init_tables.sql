CREATE TABLE IF NOT EXISTS "my_user"
(
    user_id SERIAL not NULL primary key,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    user_role varchar(255) NOT NULL
);