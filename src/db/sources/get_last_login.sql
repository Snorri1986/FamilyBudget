CREATE OR REPLACE FUNCTION public.get_last_login()
	RETURNS text
	LANGUAGE plpgsql
AS $function$
	DECLARE
         last_login_name varchar;
    BEGIN

    SELECT login INTO last_login_name FROM user_last_login
    WHERE last_login = (SELECT max(last_login) from user_last_login);

    RETURN last_login_name;

	END;
$function$
;