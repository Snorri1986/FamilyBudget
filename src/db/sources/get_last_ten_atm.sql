CREATE OR REPLACE FUNCTION public.get_last_ten_atm()
	RETURNS TABLE(v_optype bigint, v_amount bigint, v_date timestamp with time zone, v_currency bigint, v_comments character varying)
	LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
         SELECT
               optype,
               amount,
               date,
               currency,
               comments
         FROM atm_cash_operations_log
         WHERE user_last_session = get_last_login()
         ORDER BY date DESC
         LIMIT 10;
     END;
$function$
;