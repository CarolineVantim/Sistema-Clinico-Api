create table IF NOT EXISTS family (
    cpf varchar(11) primary key,
    name varchar(255) not null,
    gender char(1),
    address varchar(255),
    phone varchar(20),
    zip_code varchar(8),
    kinship_degree varchar(50),
    birth_date date
);

create table IF NOT EXISTS professional (
    crm varchar(20) primary key,
    cpf varchar(11) UNIQUE not null,
    name varchar(255) not null,
    gender char(1),
    address varchar(255),
    zip_code varchar(8),
    phone varchar(20),
    position varchar(100),
    birth_date date
);

CREATE TABLE IF NOT EXISTS field_of_works (
    id SERIAL PRIMARY KEY,
    professional_crm VARCHAR(20),
    field_of_work VARCHAR(255),
    FOREIGN KEY (professional_crm) REFERENCES professional(crm)
);

create table IF NOT EXISTS student (
    cpf varchar(11) primary key,
    name varchar(255) not null,
    disability_type varchar(50),
    birth_date date,
    student_image TEXT
);

create table IF NOT EXISTS users (
    id serial primary key,
    cpf varchar(11),
    crm varchar(20),
    username varchar(255) not null UNIQUE,
    password varchar(255) not null,
    type_user varchar(50) not null,
    creation_date DATE DEFAULT CURRENT_DATE,
    foreign key (cpf) references family(cpf) on delete set null,
    foreign key (crm) references professional(crm) on delete set null
);

create table IF NOT EXISTS google_drive_media (
    id serial primary key,
    url varchar(255) not null,
    file_name varchar(255),
    file_type varchar(50),
    description text
);

CREATE TABLE IF NOT EXISTS video_records (
    id SERIAL PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,
    id_google_drive_media INT NOT NULL,
    recording_date DATE NOT NULL DEFAULT CURRENT_DATE,
    description TEXT,
    video_url VARCHAR(255),
    FOREIGN KEY (cpf) REFERENCES student(cpf),
    FOREIGN KEY (id_google_drive_media) REFERENCES google_drive_media(id)
);

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
    media_id int references google_drive_media(id)
);

CREATE TABLE IF NOT EXISTS notes (
    id SERIAL PRIMARY KEY,
    notes TEXT,
    class_records_id INT,
    FOREIGN KEY (class_records_id) REFERENCES class_records(id)
);

CREATE TABLE IF NOT EXISTS professional_student (
    professional_id VARCHAR(20),
    student_id VARCHAR(11),
    FOREIGN KEY (student_id) REFERENCES student(cpf),
    FOREIGN KEY (professional_id) REFERENCES professional(crm)
);

CREATE TABLE student_family (
    id SERIAL PRIMARY KEY,
    student_id VARCHAR(11) NOT NULL,
    family_id VARCHAR(11) NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (cpf),
    FOREIGN KEY (family_id) REFERENCES family (cpf)
);
