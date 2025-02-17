CREATE OR REPLACE FUNCTION public.atm_cash_register(oper_type character,amount_val integer,oper_date date,comm_val character)
	RETURNS void
	LANGUAGE plpgsql
AS $function$
	BEGIN
         IF oper_type = 'Income' THEN
             PERFORM  public.add_cash_balance(amount_val);
                     INSERT INTO cash_operations_log(optype, amount, date, comments)
                     VALUES (1,amount_val,oper_date,comm_val);
         ELSIF oper_type = 'Expense' THEN
             PERFORM  public.minus_cash_balance(amount_val);
                     INSERT INTO cash_operations_log(optype, amount, date, comments)
                     VALUES (0,amount_val,oper_date,comm_val);
         END IF;
	END;
$function$
;