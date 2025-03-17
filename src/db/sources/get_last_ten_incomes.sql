CREATE OR REPLACE FUNCTION public.get_last_ten_incomes()
	RETURNS table(i_type int8, amount int8, currency int8,date timestamptz,target_card int8,comments varchar(255))
	LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT i.i_type,i.amount,i.currency,i.date,i.target_card,i.comments
          FROM public.income i
          WHERE i.user_last_session = get_last_login()
          ORDER BY i.date DESC
          LIMIT 10;
	END;
$function$
;