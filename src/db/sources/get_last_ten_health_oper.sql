CREATE OR REPLACE FUNCTION public.get_last_ten_health_oper()
	RETURNS TABLE(h_type_id bigint,amount bigint,currency bigint,date timestamp with time zone,source_card bigint, opertype varchar(255), comments varchar(255))
	LANGUAGE plpgsql
AS $function$
	BEGIN
          RETURN QUERY
          SELECT h.h_type_id,
                 h.amount,
                 h.currency,
                 h.date,
                 h.source_card,
                 h.opertype,
                 h.comments
          FROM public.health h
          WHERE h.user_last_session = get_last_login()
          ORDER BY h.date DESC
          LIMIT 10;
      END;
$function$
;