create schema chatroom_base;
set search_path to chatroom_base;

create table user_accounts
(
    id         uuid primary key default gen_random_uuid(),
    active      boolean,
    email       varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    nickname    varchar(255),
    password    varchar(255),
    authorities varchar(255)[],
    deleted_at  timestamp with time zone
);


create table verification_tokens
(
    token   varchar(32) primary key,
    user_id uuid not null,
    constraint fk_verification_tokens_user_id foreign key (user_id) references user_accounts (id)
);

create table deactivated_tokens
(
    c_keep_until timestamp(6) with time zone,
    id           uuid not null
        primary key
);
create table chat_messages
(
    timestamp          timestamp(6),
    id                 uuid not null
        primary key,
    content            varchar(255),
    recipient_nickname varchar(255),
    sender_nickname    varchar(255)
);

