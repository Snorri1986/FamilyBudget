CREATE OR REPLACE FUNCTION public.get_location()
	RETURNS TABLE(v_country text, v_city text)
	LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
         SELECT country,
                city
         FROM public.users
         WHERE username = get_last_login();
     END;
$function$
;;