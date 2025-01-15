create function i_login(web_login text, web_password text) returns integer
    language plpgsql
as
$$
DECLARE
    validation_result int;
    db_user text;
    db_password text;

BEGIN

    SELECT username, password INTO db_user,db_password FROM users;

    IF web_login == db_user AND web_password == db_password THEN
        validation_result := 1;
    ELSE
        validation_result := 0;
    END IF;

    RETURN validation_result;

END;
$$;