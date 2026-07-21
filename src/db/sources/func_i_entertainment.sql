create or replace function i_entertainment(evn_type_id_val integer, amount_val integer, cur_value integer, oper_date date, oper_type text, src_card integer, comm_val text) returns void
    language plpgsql
as
$$
DECLARE
    username text;
    vat_value double precision;
BEGIN

    IF evn_type_id_val IS NULL THEN
        RAISE EXCEPTION 'Invalid event type ID: %', evn_type_id_val;
    END IF;

    IF amount_val IS NULL OR amount_val <= 0 THEN
        RAISE EXCEPTION 'Invalid amount: %', amount_val;
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
        VALUES (0,amount_val,oper_date,comm_val,username,cur_value,evn_type_id_val,vat_value);

        PERFORM minus_cash_balance(amount_val);

    ELSE
        INSERT INTO entertainment(event_type_id,amount,currency,date,source_card,comments,opertype,user_last_session,vat)
        VALUES (evn_type_id_val,amount_val,cur_value,oper_date,src_card,comm_val,oper_type,username,vat_value);
    END IF;

END;
$$;



