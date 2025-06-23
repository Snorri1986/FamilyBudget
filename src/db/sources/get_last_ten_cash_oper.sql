CREATE OR REPLACE FUNCTION public.get_last_ten_cash_oper()
 RETURNS TABLE(optype bigint, amount bigint, date timestamp with time zone, comments character varying)
 LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT c.optype,c.amount,c.date,c.comments
          FROM public.cash_operations_log c
          WHERE c.user_last_session = get_last_login()
          ORDER BY c.date DESC
          LIMIT 10;
	END;
$function$
;
;;
COMMENT ON FUNCTION public.get_last_ten_cash_oper() IS 'get last ten cash operations';