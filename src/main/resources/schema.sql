drop table if exists todo;

create table todo (id bigint auto_increment primary key, text varchar, create_at timestamp, update_at timestamp);
