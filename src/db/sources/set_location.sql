-- DROP PROCEDURE public.set_location(bpchar, bpchar);

CREATE OR REPLACE PROCEDURE public.set_location(IN i_country character, IN i_city character)
 LANGUAGE plpgsql
AS $procedure$
	BEGIN
         UPDATE public.users SET country = i_country, city = i_city
         WHERE username = get_last_login();
	END;
$procedure$
;