CREATE OR REPLACE FUNCTION public.get_last_ten_cash_oper()
	RETURNS table(id int8, optype int8,amount int8,date timestamptz,comments varchar(255))
	LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT c.id,c.optype,c.amount,c.date,c.comments
          FROM public.cash_operations_log c
          WHERE c.user_last_session = get_last_login()
          ORDER BY c.date DESC
          LIMIT 10;
	END;
$function$
;