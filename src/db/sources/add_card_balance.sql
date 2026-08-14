CREATE OR REPLACE FUNCTION add_card_balance(amount_value integer) RETURNS void
    LANGUAGE plpgsql
AS
$$
DECLARE
    v_updated_rows bigint;
BEGIN

IF amount_value IS NULL OR amount_value < 0 THEN
    RAISE EXCEPTION 'Income value is NULL or negative';
END IF;

UPDATE card_balance SET balance = balance + amount_value;

GET DIAGNOSTICS v_updated_rows = ROW_COUNT;

IF v_updated_rows <> 1 THEN
    RAISE EXCEPTION
        'Expected exactly one card_balance row, updated %',
        v_updated_rows;
END IF;

END;
$$;

COMMENT ON FUNCTION add_card_balance(integer) is 'increase card balance';