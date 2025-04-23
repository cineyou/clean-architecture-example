create table if not exists public.type_compte
(
    id integer not null primary key,
    libelle varchar(255)
);

create table if not exists public.compte

(
    id varchar(255) not null primary key,
    solde numeric(38,2),
    date_creation timestamp(6),
    type_compte_id integer,
    constraint fk_type_compte_id  foreign key (type_compte_id) references type_compte(id)
);

create sequence if not exists type_compte_seq increment by 1;