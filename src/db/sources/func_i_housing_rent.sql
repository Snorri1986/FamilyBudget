CREATE OR REPLACE FUNCTION public.i_housing_rent(
      hr_type_id int,
      amount_val int,
      cur_val int,
      oper_date date,
      src_card int,
      comm_value character
)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
    last_id int;

    BEGIN

    INSERT INTO housing_rent(hr_type_id,amount,currency,date,source_card,comments)
    VALUES (hr_type_id,amount_val,cur_val,oper_date,src_card,comm_value);

    SELECT max(id) INTO last_id FROM housing_rent;

    RETURN last_id;

	END;
$function$
;