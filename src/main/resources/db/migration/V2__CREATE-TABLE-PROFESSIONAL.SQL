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