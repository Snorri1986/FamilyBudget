-- DROP FUNCTION public.get_last_ten_groceries_card();

CREATE OR REPLACE FUNCTION public.get_last_ten_groceries_card()
	RETURNS TABLE(g_type bigint, amount bigint, currency bigint, date timestamp with time zone, source_card bigint, opertype character varying, comments character varying)
	LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
          SELECT g.g_type,
                 g.amount,
                 g.currency,
                 g.date,
                 g.source_card,
                 g.opertype,
                 g.comments
          FROM public.groceries g
          WHERE g.user_last_session = get_last_login()
          AND g.opertype = 'Card'
          ORDER BY g.date DESC
          LIMIT 10;
	END;
$function$
;