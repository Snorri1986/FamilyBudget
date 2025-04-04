CREATE OR REPLACE FUNCTION public.get_last_ten_travel_operations()
	RETURNS TABLE(tr_type_id bigint, amount bigint, currency bigint, date timestamp with time zone, source_card bigint, destination varchar(255), opertype varchar(255), comments varchar(255))
	LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT tr.tr_type_id,
                 tr.amount,
                 tr.currency,
                 tr.date,
                 tr.source_card,
                 tr.destination,
                 tr.opertype,
                 tr.comments
          FROM public.travel tr
          WHERE tr.user_last_session = get_last_login()
          ORDER BY tr.date DESC
          LIMIT 10;
    END;
$function$
;;