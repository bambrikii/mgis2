TRUNCATE TABLE lands_land_control_inspection_kind;
INSERT INTO lands_land_control_inspection_kind (id, code, name) VALUES (nextval('lands_land_control_inspection_kind_seq'), '1', 'Плановая');
INSERT INTO lands_land_control_inspection_kind (id, code, name) VALUES (nextval('lands_land_control_inspection_kind_seq'), '2', 'Внеплановая');
INSERT INTO lands_land_control_inspection_kind (id, code, name) VALUES (nextval('lands_land_control_inspection_kind_seq'), '3', 'По жалобе');
INSERT INTO lands_land_control_inspection_kind (id, code, name) VALUES (nextval('lands_land_control_inspection_kind_seq'), '4', 'Тематическая');
