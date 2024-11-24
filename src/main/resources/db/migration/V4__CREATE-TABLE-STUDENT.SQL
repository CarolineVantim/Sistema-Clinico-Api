create table IF NOT EXISTS student (
     cpf varchar(11) primary key,
     name varchar(255) not null,
     disability_type varchar(50),
     birth_date date,
     student_image TEXT
);