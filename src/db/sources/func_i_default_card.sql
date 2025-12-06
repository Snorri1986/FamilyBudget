-- DROP FUNCTION public.i_default_card();

CREATE OR REPLACE FUNCTION public.i_default_card(new_card_name varchar)
    RETURNS void
	LANGUAGE plpgsql
AS $function$
    DECLARE
         current_logged_user varchar;
	BEGIN
          -- find current user --
         current_logged_user := public.get_last_login();

         -- insert new default payment card --
         update public.users set card_default = new_card_name
         where username = current_logged_user;

	END;
$function$
;