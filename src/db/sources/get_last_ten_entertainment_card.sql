CREATE OR REPLACE FUNCTION public.get_last_ten_entertainment_card()
 RETURNS TABLE(event_type_id bigint, amount bigint, currency bigint, date timestamp with time zone, source_card bigint, opertype text, comments character varying)
 LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
          SELECT e.event_type_id,
                 e.amount,
                 e.currency,
                 e.date,
                 e.source_card,
                 e.opertype::text,
                 e.comments
          FROM public.entertainment e
          WHERE e.user_last_session = get_last_login()
          AND e.opertype = 'Card'
          ORDER BY e.date DESC
          LIMIT 10;
     END;
$function$
;