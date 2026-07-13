CREATE OR REPLACE FUNCTION public.get_location()
	RETURNS TABLE(user_country text, user_city text)
	LANGUAGE plpgsql
AS $function$
    DECLARE
         v_username text;
	BEGIN

        v_username := TRIM(get_last_login());

        IF v_username IS NULL OR v_username = '' THEN
            RAISE EXCEPTION 'get_last_login() returned NULL or empty string - cannot retrieve location for username';
        END IF;

         RETURN QUERY
         SELECT country AS user_country,
                city AS user_city
         FROM public.users
         WHERE username = v_username
         LIMIT 1;
     END;
$function$
;