-- DROP FUNCTION public.i_housing_rent(int4, int4, int4, date, bpchar, int4, bpchar);

CREATE OR REPLACE FUNCTION public.i_housing_rent(hr_type_id integer, amount_val integer, cur_val integer, oper_date date, oper_type character, src_card integer, comm_value character)
 RETURNS void
 LANGUAGE plpgsql
AS $function$

BEGIN

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments)
        VALUES (0,amount_val,oper_date,comm_value);

    PERFORM minus_cash_balance(amount_val);

    ELSE
        INSERT INTO housing_rent(hr_type_id,amount,currency,date,source_card,comments,opertype)
        VALUES (hr_type_id,amount_val,cur_val,oper_date,src_card,comm_value,oper_type);
    END IF;

END;
$function$
;



