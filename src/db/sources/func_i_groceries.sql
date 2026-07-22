CREATE OR REPLACE FUNCTION public.i_groceries(gtype_value integer, amount_value integer, currency_value integer, oper_date date, oper_type text, src_payment_card integer, comm_value text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
DECLARE
    username text;
    vat_value double precision;
BEGIN

    IF gtype_value IS NULL THEN
        RAISE EXCEPTION 'Invalid grocery type ID: %', gtype_value;
    END IF;

    IF amount_value IS NULL OR amount_value <= 0 THEN
        RAISE EXCEPTION 'Invalid amount: %', amount_value;
    END IF;

    IF currency_value IS NULL OR currency_value <= 0 THEN
        RAISE EXCEPTION 'Invalid currency: %', currency_value;
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

    vat_value := calculate_vat(amount_value);

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments,user_last_session,currency,ex_type_id,vat)
        VALUES (0,amount_value,oper_date,comm_value,username,currency_value,gtype_value,vat_value);

    PERFORM minus_cash_balance(amount_value);

    ELSE
        INSERT INTO groceries(g_type, amount, currency, date, source_card, comments,opertype,user_last_session,vat)
        VALUES (gtype_value, amount_value,currency_value,oper_date,src_payment_card,comm_value,oper_type,username,vat_value);
    END IF;

END;
$function$
;







