CREATE OR REPLACE FUNCTION public.i_groceries(gtype_value integer, amount_value integer, currency_value integer, oper_date date, oper_type character, src_payment_card integer, comm_value character)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments)
        VALUES (0,amount_value,oper_date,comm_value);

    PERFORM minus_cash_balance(amount_value);

    ELSE
        INSERT INTO groceries(g_type, amount, currency, date, source_card, comments,opertype,user_last_session)
        VALUES (gtype_value, amount_value,currency_value,oper_date,src_payment_card,comm_value,oper_type,get_last_login());
    END IF;

END;
$function$
;







