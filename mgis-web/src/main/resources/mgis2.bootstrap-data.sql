DO $$
	declare priv_id_user int;
	declare priv_id_group int;
BEGIN

	truncate table mgis2_user_mgis2_privilege CASCADE;
	truncate table mgis2_user CASCADE;
	truncate table mgis2_privilege CASCADE;

	insert into mgis2_privilege (id, name) values (nextval('hibernate_sequence'), 'ROLE_USER');
	insert into mgis2_privilege (id, name) values (nextval('hibernate_sequence'), 'ROLE_ADMIN');
	insert into mgis2_user (id, active, "password", username) values (nextval('hibernate_sequence'), true, 'asd', 'asd');

	select id into priv_id_user from mgis2_privilege where "name" = 'ROLE_USER';
	select id into priv_id_group from mgis2_privilege where "name" = 'ROLE_ADMIN';

	raise notice 'priv_id_user: %', priv_id_user;
	raise notice 'priv_id_user: %', priv_id_group;

	insert into mgis2_user_mgis2_privilege (users_id, privileges_id) values (1, priv_id_user);
	insert into mgis2_user_mgis2_privilege (users_id, privileges_id) values (1, priv_id_group);

	commit;

END $$