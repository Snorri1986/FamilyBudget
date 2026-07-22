CREATE OR REPLACE FUNCTION public.i_telecom(t_type_id_val integer, amount_val integer, cur_val integer, oper_date date, oper_type text, src_card integer, comm_val text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
DECLARE
    username text;
    vat_value double precision;
BEGIN

    IF t_type_id_val IS NULL THEN
        RAISE EXCEPTION 'Invalid telecom type ID: %', t_type_id_val;
    END IF;

    IF amount_val IS NULL OR amount_val <= 0 THEN
        RAISE EXCEPTION 'Invalid amount: %', amount_val;
    END IF;

    IF cur_val IS NULL OR cur_val <= 0 THEN
        RAISE EXCEPTION 'Invalid currency: %', cur_val;
    END IF;

    IF oper_date IS NULL THEN
        RAISE EXCEPTION 'Operation date cannot be NULL';
    END IF;

    IF oper_type IS NULL THEN
        RAISE EXCEPTION 'Operation type cannot be NULL';
    END IF;

    IF oper_type NOT IN ('Cash','Card') THEN
        RAISE EXCEPTION 'Unsupported operation type: %', oper_type;
    END IF;

    username := get_last_login();

    IF username IS NULL THEN
        RAISE EXCEPTION 'User not logged in';
    END IF;

    vat_value := calculate_vat(amount_val);



    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments,user_last_session,currency,ex_type_id,vat)
        VALUES (0,amount_val,oper_date,comm_val,username,cur_val,t_type_id_val,vat_value);

        PERFORM minus_cash_balance(amount_val);

    ELSE
        INSERT INTO telecom(t_type_id,amount,currency,date,source_card,comments,opertype,user_last_session,vat)
        VALUES (t_type_id_val,amount_val,cur_val,oper_date,src_card,comm_val,oper_type,username,vat_value);
    END IF;

END;
$function$
;



