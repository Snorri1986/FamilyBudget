CREATE OR REPLACE FUNCTION public.i_travel(tr_type_id_val integer, amount_val integer, curr_val integer, oper_date date, oper_type text, src_card_val integer, destin_val text, comm_val text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
DECLARE
    username text;
    vat_value double precision;
BEGIN

    IF tr_type_id_val IS NULL THEN
        RAISE EXCEPTION 'Invalid travel type ID: %', tr_type_id_val;
    END IF;

    IF amount_val IS NULL OR amount_val <= 0 THEN
        RAISE EXCEPTION 'Invalid amount: %', amount_val;
    END IF;

    IF curr_val IS NULL OR curr_val <= 0 THEN
        RAISE EXCEPTION 'Invalid currency: %', curr_val;
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

        PERFORM minus_cash_balance(amount_val);

        INSERT INTO cash_operations_log(optype, amount, date, comments,user_last_session,currency,ex_type_id,vat)
        VALUES (0,amount_val,oper_date,comm_val,username,curr_val,tr_type_id_val,vat_value);

    ELSE

        PERFORM minus_card_balance(amount_val);

        INSERT INTO travel(tr_type_id,amount,currency,date,source_card,destination,comments,opertype,user_last_session,vat)
        VALUES (tr_type_id_val,amount_val,curr_val,oper_date,src_card_val,destin_val,comm_val,oper_type,username,vat_value);

    END IF;
END;
$function$
;






