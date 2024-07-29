CREATE OR REPLACE FUNCTION public.i_groceries(
gtype_value integer,
amount_value integer,
currency_value integer,
oper_date date,
src_payment_card integer,
comm_value character)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
    last_id int;

    BEGIN

    INSERT INTO groceries(g_type, amount, currency, date, source_card, comments)
    VALUES (gtype_value, amount_value,currency_value,oper_date,src_payment_card,comm_value);

    SELECT max(id) INTO last_id FROM groceries;

    RETURN last_id;

	END;
$function$
;