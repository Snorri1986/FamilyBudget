CREATE OR REPLACE FUNCTION public.get_default_card()
	RETURNS text
	LANGUAGE plpgsql
AS $function$
    DECLARE
           card_default varchar;
	BEGIN
           SELECT users.card_default INTO card_default FROM public.users
           WHERE username = public.get_last_login();

           RETURN card_default;
	END;
$function$
;