create function add_cash_balance(amount_value integer) returns void
    language plpgsql
as
$$
BEGIN
    UPDATE cash_balance SET balance = balance + amount_value;
END;
$$;
COMMENT ON FUNCTION public.add_cash_balance(int4) IS 'increas cash balance';



