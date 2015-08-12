TRUNCATE TABLE lands_land_control_inspection_type;
INSERT INTO lands_land_control_inspection_type (id, code, name) VALUES (nextval('lands_land_control_inspection_type_seq'), '1', 'Выездная');
INSERT INTO lands_land_control_inspection_type (id, code, name) VALUES (nextval('lands_land_control_inspection_type_seq'), '2', 'Камеральная');
