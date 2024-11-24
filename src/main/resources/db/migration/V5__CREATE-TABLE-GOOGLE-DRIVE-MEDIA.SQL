create table IF NOT EXISTS google_drive_media (
    id serial primary key,
    url varchar(255) not null,
    file_name varchar(255),
    file_type varchar(50),
    description text
);