create table IF NOT EXISTS class_records (
       id serial primary key,
       professional_crm varchar(20) not null references professional(crm),
       student_cpf VARCHAR(11) NOT NULL REFERENCES student(cpf),
       class_date date not null,
       start_time time not null,
       end_time time not null,
       subject varchar(255) not null,
       status varchar(50) not null check (status in ('PLANNED', 'COMPLETED')),
       location varchar(255),
       discipline varchar(50),
       media_id int references google_drive_media(id) on delete set null
);