create or replace function get_card_balance() returns integer
    language plpgsql
as
$$
DECLARE
v_balance integer;
BEGIN
SELECT balance INTO v_balance
from card_balance
LIMIT 1;

IF NOT FOUND THEN
    RAISE EXCEPTION 'No card balance record found';
END IF;

IF v_balance IS NULL THEN
    RAISE EXCEPTION 'Card balance is NULL';
END IF;

RETURN v_balance;

EXCEPTION
    WHEN OTHERS THEN
        RAISE EXCEPTION 'Error retrieving card balance: %', SQLERRM;
END;
$$;

comment on function get_card_balance() is 'get current card balance';