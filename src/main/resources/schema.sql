create table users(
                      username varchar(50) not null primary key,
                      password varchar(50) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table customer(
    id uuid primary key ,
    email varchar(50) not null,
    pwd varchar(50) not null,
    role varchar(50) not null
)

insert into customer values ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5','test@test.com','12345','admin');
