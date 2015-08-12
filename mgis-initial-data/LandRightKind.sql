TRUNCATE TABLE nc_land_right_kind;
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.1', '1001001000', 'Собственность', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.2', '1001002000', 'Долевая собственность', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.3', '1001003000', 'Совместная собственность', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.4', '1001004000', 'Хозяйственное ведение', 'Данные виды права не используются для земельных участков');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.5', '1001005000', 'Оперативное управление', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.6', '1001006000', 'Пожизненное наследуемое владение', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.7', '1001007000', 'Постоянное (бессрочное)пользование', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.8', '1001008000', 'Сервитут (право)', '');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.9', '1002003000', 'Аренда (в том числе субаренда, лизинг)', 'Обычные правила формирования классификационного кода нарушены для обеспечения совместимости с классификаторами ФРС');
INSERT INTO nc_land_right_kind (id, number, classification_code, name, comment) VALUES (nextval('nc_land_right_kind_seq'), '1.10', '1002008000', 'Безвозмездное срочное пользование земельным участком', '');


