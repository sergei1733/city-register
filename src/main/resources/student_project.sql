DROP TABLE IF EXISTS ts_apartment_resident;
DROP TABLE IF EXISTS ts_apartment;
DROP TABLE IF EXISTS ts_house;
DROP TABLE IF EXISTS ts_pets;
DROP TABLE IF EXISTS ts_social_status;

CREATE TABLE ts_house
(
    house_id SERIAL;
    house_code integer not null,
    address_name varchar(300),
    PRIMARY KEY (house_id)
);

CREATE TABLE ts_apartment
(
    ap_apartment_id SERIAL,
    ap_house_id integer not null,
    ap_address_name varchar(300),
    ap_apartment_owner varchar(100),
    ap_apartment_number varchar(10),
    ap_entrance_number varchar(3),
    ap_house_code integer not null,
    PRIMARY KEY (ap_apartment_id),
    FOREIGN KEY (ap_house_id) REFERENCES ts_house(house_id) ON DELETE RESTRICT
);

CREATE TABLE ts_pets
(
    ps_pet_id SERIAL,
    apartment_resident_id integer not null,
    ps_area_name varchar(200),
    ps_pet_name varchar(50),
    ps_pet_address varchar (100),
    PRIMARY KEY (ps_pet_id)
    FOREIGN KEY (apartment_resident_id) REFERENCES ts_apartment_resident(ar_apartment_resident_id) ON DELETE RESTRICT
);

CREATE TABLE ts_social_status
(
    ss_social_status_id SERIAL,
    ss_social_status_name char(50) not null,
    PRIMARY KEY (ss_social_status_id),
);

CREATE TABLE ts_apartment_resident
(
    ar_apartment_resident_id SERIAL,
    apartment_id integer not null,
    social_status_id integer not null,
    ar_sur_name varchar(100) not null,
    ar_given_name varchar(100) not null,
    ar_patronymic varchar(100) not null,
    ar_address varchar(300),
    ar_social_status_id integer not null,
    ar_pet_id integer not null;
    ar_apartment_id integer not null,

    PRIMARY KEY (ar_apartment_resident_id),
    FOREIGN KEY (apartment_id) REFERENCES ts_apartment(ap_apartment_id) ON DELETE RESTRICT,
    FOREIGN KEY (social_status_id) REFERENCES ts_social_status(ss_social_status_id) ON DELETE RESTRICT
);

CREATE INDEX idx_ar_apartment_resident_id ON ts_apartment_resident(ar_apartment_resident_id);
