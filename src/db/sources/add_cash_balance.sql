create function add_cash_balance(amount_value integer) returns void
    language plpgsql
as
$$
BEGIN
      UPDATE cash_balance SET balance = balance + amount_value FROM cash_balance;
END;
$$;

alter function add_cash_balance(integer) owner to u4cg7fn2s82n4v;

