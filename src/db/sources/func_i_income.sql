create function i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, payment_card integer, comm_value character) returns void
    language plpgsql
as
$$
BEGIN

    INSERT INTO income(i_type, amount, currency, date,target_card, comments)
    VALUES (income_type,amount_value,currency_value,oper_date,payment_card,comm_value);

END;
$$;

alter function i_income(integer, integer, integer, date, integer, char) owner to u4cg7fn2s82n4v;