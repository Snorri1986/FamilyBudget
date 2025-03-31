CREATE OR REPLACE FUNCTION public.get_last_ten_housing_oper()
	RETURNS TABLE(hr_type_id bigint,amount bigint,currency bigint,date timestamp with time zone,source_card bigint,opertype varchar(255),comments varchar(255))
	LANGUAGE plpgsql
AS $function$
	BEGIN
         RETURN QUERY
          SELECT r.hr_type_id,
                 r.amount,
                 r.currency,
                 r.date,
                 r.source_card,
                 r.opertype,
                 r.comments
          FROM public.housing_rent r
          WHERE r.user_last_session = get_last_login()
          ORDER BY r.date DESC
          LIMIT 10;
    END;
$function$
;