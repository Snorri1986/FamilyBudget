CREATE OR REPLACE FUNCTION get_all_vat_monthly(p_currency_id integer DEFAULT 3) RETURNS DOUBLE PRECISION
    LANGUAGE plpgsql
AS
$$
DECLARE
v_user_session          text;
    v_month_start           timestamp;
    v_month_end             timestamp;
    v_total_monthly_vat     double precision := 0.0;
BEGIN

    -- Cache the user session to avoid multiple function calls (performance optimization)
    v_user_session := public.get_last_login();

    -- validation
    IF v_user_session IS NULL THEN
        RAISE EXCEPTION 'User session is null. Cannot calculate expenses without a valid user session.';
END IF;

    -- Define the previous month's date range
    v_month_start := date_trunc('month', current_date - interval '1 month');
    v_month_end := date_trunc('month', current_date);

    -- Aggregate all expenses from the 6 expense categories
WITH all_monthly_vat AS (
    SELECT vat FROM public.entertainment
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
    UNION ALL
    SELECT vat FROM public.groceries
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
    UNION ALL
    SELECT vat FROM public.health
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
    UNION ALL
    SELECT vat FROM public.housing_rent
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
    UNION ALL
    SELECT vat FROM public.telecom
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
    UNION ALL
    SELECT vat FROM public.travel
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_month_start AND "date" < v_month_end
)
SELECT COALESCE(SUM(vat),0) INTO v_total_monthly_vat FROM all_monthly_vat;

v_total_monthly_vat := ROUND(v_total_monthly_vat::NUMERIC, 2)::DOUBLE PRECISION;

RETURN v_total_monthly_vat;
END;
$$;



