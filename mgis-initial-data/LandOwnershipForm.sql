TRUNCATE TABLE nc_land_ownership_form;
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '10', 'РОССИЙСКАЯ СОБСТВЕННОСТЬ', '11+14+15+16+17+18+19+61');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '11', 'Государственная собственность', '12+13');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '12', 'Федеральная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '13', 'Собственность субъектов Российской Федерации', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '14', 'Муниципальная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '16', 'Частная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '18', 'Собственность    российских    граждан,    постоянно проживающих за границей', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '19', 'Собственность потребительской кооперации', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '15', 'Собственность общественных и религиозных организаций  (объединений)', '50+51+52+53+54');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '50', 'Собственность благотворительных организаций', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '51', 'Собственность политических общественных объединений', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '52', 'Собственность профессиональных союзов', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '53', 'Собственность общественных объединений', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '54', 'Собственность религиозных объединений', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '17', 'Смешанная российская собственность', '40+49');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '20', 'ИНОСТРАННАЯ СОБСТВЕННОСТЬ', '21+22+23+24+27');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '21', 'Собственность международных организаций', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '22', 'Собственность иностранных государств', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '23', 'Собственность иностранных юридических лиц', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '24', 'Собственность   иностранных   граждан   и   лиц  без гражданства', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '27', 'Смешанная иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '30', 'СОВМЕСТНАЯ РОССИЙСКАЯ И ИНОСТРАННАЯ СОБСТВЕННОСТЬ', '31+32+33+34+35');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '31', 'Совместная федеральная и иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '32', 'Совместная собственность субъектов Российской Федерации и иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '33', 'Совместная муниципальная и иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '34', 'Совместная частная и иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '35', 'Совместная собственность общественных и религиозных организаций (объединений) и иностранная собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '40', 'СМЕШАННАЯ РОССИЙСКАЯ СОБСТВЕННОСТЬ С ДОЛЕЙ ГОСУДАРСТВЕННОЙ СОБСТВЕННОСТИ', '41+42+43');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '41', 'Смешанная российская собственность с долей федеральной собственности', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '42', 'Смешанная российская собственность с долей собственности субъектов Российской Федерации', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '43', 'Смешанная российская собственность с долями федеральной собственности и собственности субъектов Российской Федерации', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '49', 'Иная смешанная российская собственность', '');
INSERT INTO nc_land_ownership_form (id, code, name, gathering_algo) VALUES (nextval('nc_land_ownership_form_seq'), '61', 'Собственность государственных корпораций', '');


UPDATE nc_land_ownership_form SET gathering_parent_id = null;

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '10') f2
WHERE code in ('11', '14', '15', '16', '17', '18', '19', '61');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '11') f2
WHERE code in ('12', '13');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '15') f2
WHERE code in ('50', '51', '52', '53', '54');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '17') f2
WHERE code in ('40', '49');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '20') f2
WHERE code in ('21', '22', '23', '24', '27');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '30') f2
WHERE code in ('31', '32', '33', '34', '35');

UPDATE nc_land_ownership_form
SET gathering_parent_id = f2.id
FROM (SELECT id FROM nc_land_ownership_form WHERE code = '40') f2
WHERE code in ('41', '42', '43');
