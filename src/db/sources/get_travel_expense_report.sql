CREATE OR REPLACE FUNCTION public.get_travel_expense_report(t_destination character varying)
 RETURNS TABLE(tr_type_id integer, amount integer, currency integer, date timestamp with time zone, source_card integer, comments character varying)
 LANGUAGE plpgsql
AS $function$
   DECLARE
         null_source_card int4;
   BEGIN
         null_source_card := 0; -- for cash operations
         RETURN QUERY
         SELECT t.tr_type_id::integer,
                t.amount::integer,
                t.currency::integer,
                t.date,
                t.source_card::integer,
                t.comments
         FROM public.travel t
         JOIN public.cash_operations_log col ON t.tr_type_id = col.ex_type_id
         WHERE t.user_last_session = get_last_login()
         AND t.destination = t_destination
         UNION
         SELECT col.ex_type_id::integer,
                col.amount::integer,
                col.currency::integer,
                col.date,
                null_source_card as source_card,
                col.comments
         FROM public.travel t
         JOIN public.cash_operations_log col ON t.tr_type_id = col.ex_type_id
         WHERE t.user_last_session = get_last_login()
         AND t.destination = t_destination;
	END;
$function$
;