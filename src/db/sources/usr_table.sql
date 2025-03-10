create table users
(
    username text,
    password text
);

comment on table users is 'Authorized users';

comment on column users.username is 'login id';

comment on column users.password is 'path phrase';