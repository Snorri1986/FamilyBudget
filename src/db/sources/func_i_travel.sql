-- DROP FUNCTION public.i_travel(int4, int4, int4, date, bpchar, int4, bpchar, bpchar);

CREATE OR REPLACE FUNCTION public.i_travel(tr_type_id_val integer, amount_val integer, curr_val integer, oper_date date, oper_type character, src_card_val integer, destin_val character, comm_val character)
 RETURNS void
 LANGUAGE plpgsql
AS $function$

BEGIN

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments)
        VALUES (0,amount_val,oper_date,comm_val);

        PERFORM minus_cash_balance(amount_val);

    ELSE
        INSERT INTO travel(tr_type_id,amount,currency,date,source_card,destination,comments,opertype)
        VALUES (tr_type_id_val,amount_val,curr_val,oper_date,src_card_val,destin_val,comm_val,oper_type);
    END IF;

END;
$function$
;






