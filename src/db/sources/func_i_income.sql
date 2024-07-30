CREATE OR REPLACE FUNCTION public.i_income(income_type integer,
amount_value integer,
currency_value integer,
oper_date date,
payment_card integer,
comm_value character)
 RETURNS integer
 LANGUAGE plpgsql
AS $function$
    DECLARE
    last_id int;

    BEGIN

    INSERT INTO income(i_type, amount, currency, date,target_card, comments)
    VALUES (income_type,amount_value,currency_value,oper_date,payment_card,comm_value);

    SELECT max(id) INTO last_id FROM income;

    RETURN last_id;

	END;
$function$
;