CREATE OR REPLACE FUNCTION public.minus_cash_balance(amount_value integer)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
    DECLARE
          current_cash_balance int4;
	BEGIN
         SELECT balance INTO current_cash_balance FROM cash_balance;

         IF amount_value <= current_cash_balance THEN
               UPDATE cash_balance SET balance = balance - amount_value;
         END IF;
	END;
$function$
;