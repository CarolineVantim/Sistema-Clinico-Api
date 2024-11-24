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