use campaigneer_db;

insert into address_country (code, name) values ('00', 'Brasil');
insert into address_state (code, name, country_id) values ('00', 'Paraná', (select id from address_country where code = '00'));
insert into address_city (name, state_id) values ('Curitiba', (select id from address_state where code = '00'));
insert into address (address_type, complement, postal_code, street_name, street_number, city_id)
    values(6, 'SEPT', '88008-888', 'Dr. Alcides Vieira Arcoverde', '1225', (select id from address_city where name = 'Curitiba'));