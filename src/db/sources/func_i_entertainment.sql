CREATE OR REPLACE FUNCTION public.i_entertainment(
    evn_type_id_val int,
    amount_val int,
    cur_value int,
    oper_date date,
    src_card int,
    comm_val character
)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
    last_id int;

    BEGIN

    INSERT INTO entertainment(event_type_id,amount,currency,date,source_card,comments)
    VALUES (evn_type_id_val,amount_val,cur_value,oper_date,src_card,comm_val);

    SELECT max(id) INTO last_id FROM entertainment;

    RETURN last_id;

	END;
$function$
;