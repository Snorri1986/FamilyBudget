CREATE OR REPLACE FUNCTION public.get_last_ten_incomes_cash()
 RETURNS TABLE(ex_type_id integer, amount bigint, currency bigint, date timestamp with time zone, opertype text, comments character varying)
 LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
          SELECT
                   c_log.ex_type_id,
                   c_log.amount,
                   c_log.currency,
                   c_log.date,
                   'Cash' AS opertype,
                   c_log.comments
          FROM public.cash_operations_log c_log
          WHERE c_log.user_last_session = get_last_login()
          AND c_log.ex_type_id IN (14,13,15,16,41,17,18)
          ORDER BY c_log.date DESC
          LIMIT 10;
	END;
$function$
;