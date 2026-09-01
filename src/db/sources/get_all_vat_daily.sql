create function get_all_vat_daily(p_currency_id integer DEFAULT 3) returns double precision
    language plpgsql
as
$$
DECLARE
v_user_session  text;
    v_today         date;
    v_day_start     timestamp;
    v_day_end       timestamp;
    v_total_daily_vat double precision := 0.0;
BEGIN
    -- Cache the user session to avoid multiple function calls (performance optimization)
    v_user_session := public.get_last_login();
    v_today := CURRENT_DATE;

    -- Validation
    IF v_user_session IS NULL THEN
        RAISE EXCEPTION 'User session not found';
END IF;

    -- Calculate date range for current day (inclusive start, exclusive end)
    v_day_start := v_today::timestamp;
    v_day_end := (v_today + interval '1 day')::timestamp;

    -- Aggregate VAT from all 6 expense categories
WITH all_daily_vat AS (
    SELECT vat FROM public.entertainment
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
    UNION ALL
    SELECT vat FROM public.groceries
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
    UNION ALL
    SELECT vat FROM public.health
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
    UNION ALL
    SELECT vat FROM public.housing_rent
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
    UNION ALL
    SELECT vat FROM public.telecom
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
    UNION ALL
    SELECT vat FROM public.travel
    WHERE user_last_session = v_user_session
      AND currency = p_currency_id
      AND "date" >= v_day_start AND "date" < v_day_end
)
SELECT COALESCE(SUM(vat), 0) INTO v_total_daily_vat FROM all_daily_vat;

RETURN v_total_daily_vat;
END;
$$;



