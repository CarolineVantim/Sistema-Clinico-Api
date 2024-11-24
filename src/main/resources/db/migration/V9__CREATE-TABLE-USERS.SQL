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