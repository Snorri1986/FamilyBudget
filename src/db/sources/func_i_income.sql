CREATE OR REPLACE FUNCTION public.i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, oper_type character, payment_card integer, comm_value character)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN

    IF oper_type = 'Cash' THEN

        PERFORM add_cash_balance(amount_value);

        INSERT INTO cash_operations_log(optype, amount, date, comments,user_last_session,currency,ex_type_id)
        VALUES (1,amount_value,oper_date,comm_value,get_last_login(),currency_value,income_type);

    ELSE

        PERFORM add_card_balance(amount_value);

        INSERT INTO income(i_type, amount, currency, date,target_card, comments,opertype,user_last_session)
        VALUES (income_type,amount_value,currency_value,oper_date,payment_card,comm_value,oper_type,get_last_login());

    END IF;

END;
$function$
;