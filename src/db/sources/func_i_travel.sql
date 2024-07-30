CREATE OR REPLACE FUNCTION public.i_travel(
    tr_type_id_val int,
    amount_val int,
    curr_val int,
    oper_date date,
    src_card_val int,
    destin_val character,
    comm_val character
)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
    last_id int;

    BEGIN

    INSERT INTO travel(tr_type_id,amount,currency,date,source_card,destination,comments)
    VALUES (tr_type_id_val,amount_val,curr_val,oper_date,src_card_val,destin_val,comm_val);

    SELECT max(id) INTO last_id FROM travel;

    RETURN last_id;

	END;
$function$
;