CREATE OR REPLACE FUNCTION public.i_login(web_login text, web_password text)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
DECLARE
    validation_result int;
    db_user text;
    db_password text;

BEGIN

    SELECT username, password INTO db_user,db_password FROM users;

    IF web_login = db_user AND web_password = db_password THEN
        validation_result := 1;

    INSERT INTO public.user_last_login (login, last_login)
    VALUES(web_login, LOCALTIMESTAMP);

    ELSE
        validation_result := 0;
    END IF;

    RETURN validation_result;

END;
$function$
;