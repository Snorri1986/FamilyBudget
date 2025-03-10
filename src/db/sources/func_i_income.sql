create function i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, oper_type character, payment_card integer, comm_value character) returns void
    language plpgsql
as
$$
BEGIN

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments)
        VALUES (1,amount_value,oper_date,comm_value);

        PERFORM add_cash_balance(amount_value);
    ELSE
        INSERT INTO income(i_type, amount, currency, date,target_card, comments,opertype)
        VALUES (income_type,amount_value,currency_value,oper_date,payment_card,comm_value,oper_type);
    END IF;

END;
$$;

alter function i_income(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;