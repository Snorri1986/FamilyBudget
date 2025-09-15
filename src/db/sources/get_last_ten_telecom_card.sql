CREATE OR REPLACE FUNCTION public.get_last_ten_telecom_card()
	RETURNS TABLE(t_type_id bigint, amount bigint, currency bigint, date timestamp with time zone, source_card bigint, opertype character varying, comments character varying)
	LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
          SELECT t.t_type_id,
                 t.amount,
                 t.currency,
                 t.date,
                 t.source_card,
                 t.opertype,
                 t.comments
          FROM public.telecom t
          WHERE t.user_last_session = get_last_login()
          AND t.opertype = 'Card'
          ORDER BY t.date DESC
          LIMIT 10;
    END;
$function$
;