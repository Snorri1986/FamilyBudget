CREATE OR REPLACE FUNCTION public.get_cash_balance()
	RETURNS int4
	LANGUAGE plpgsql
AS $function$
    DECLARE
       cash_balance int4;
	BEGIN
        SELECT balance INTO cash_balance from cash_balance;
        RETURN cash_balance;
	END;
$function$
;
