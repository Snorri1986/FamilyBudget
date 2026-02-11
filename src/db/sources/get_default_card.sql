CREATE OR REPLACE FUNCTION public.get_default_card()
	RETURNS integer
	LANGUAGE plpgsql
AS $function$
	DECLARE
           card_default integer;
	BEGIN
           SELECT users.card_default::integer
           INTO card_default FROM public.users
           WHERE username = public.get_last_login();
           RETURN card_default;
    END;
$function$
;