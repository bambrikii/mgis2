TRUNCATE TABLE lands_land_area_type;
INSERT INTO lands_land_area_type (id, name) VALUES (nextval('lands_land_area_type_seq'), 'Уточнённая');
INSERT INTO lands_land_area_type (id, name) VALUES (nextval('lands_land_area_type_seq'), 'Декларированная');
INSERT INTO lands_land_area_type (id, name) VALUES (nextval('lands_land_area_type_seq'), 'По карте');
INSERT INTO lands_land_area_type (id, name) VALUES (nextval('lands_land_area_type_seq'), 'Под застройкой');
