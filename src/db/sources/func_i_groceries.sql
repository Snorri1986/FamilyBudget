create function i_groceries(gtype_value integer, amount_value integer, currency_value integer, oper_date date, oper_type character, src_payment_card integer, comm_value character) returns integer
    language plpgsql
as
$$
DECLARE
    last_id int;

BEGIN

    INSERT INTO groceries(g_type, amount, currency, date, source_card, comments,opertype)
    VALUES (gtype_value, amount_value,currency_value,oper_date,src_payment_card,comm_value,oper_type);

    SELECT max(id) INTO last_id FROM groceries;

    RETURN last_id;

END;
$$;

alter function i_groceries(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;

