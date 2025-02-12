CREATE OR REPLACE FUNCTION public.minus_cash_balance(amount_value integer)
	RETURNS void
	LANGUAGE plpgsql
AS $function$
	BEGIN
         UPDATE cash_balance SET balance = balance - amount_value;
	END;
$function$ ;