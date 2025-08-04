CREATE OR REPLACE FUNCTION public.get_last_ten_incomes_card()
 RETURNS TABLE(i_type bigint, amount bigint, currency bigint, date timestamp with time zone, target_card bigint, comments character varying)
 LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT i.i_type,
                 i.amount,
                 i.currency,
                 i.date,
                 i.target_card,
                 i.comments
          FROM public.income i
          WHERE i.user_last_session = get_last_login()
          AND i.opertype = 'Card'
          ORDER BY i.date DESC
          LIMIT 10;
	END;
$function$
;