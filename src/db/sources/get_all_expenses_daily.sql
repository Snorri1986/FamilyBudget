-- DROP FUNCTION public.get_all_expenses_daily();

/**
 * FUNCTION: get_all_expenses_daily(p_currency_id)
 * PURPOSE: Calculates total daily expenses across all categories for the current user
 * RETURNS: numeric - Total daily expenses sum
 *
 * NOTES:
 * - Filters by specified currency ID (default = 3)
 * - Targets current date only
 * - Sums expenses from: entertainment, groceries, health, housing_rent, telecom, travel
 */
CREATE OR REPLACE FUNCTION public.get_all_expenses_daily(p_currency_id integer DEFAULT 3)
    RETURNS numeric
    LANGUAGE plpgsql
AS $$
DECLARE
    v_user_session  text;
    v_today         date;
    v_day_start     timestamp;
    v_day_end       timestamp;
    v_total_expenses numeric := 0;
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

    -- Aggregate expenses from all 6 expense categories
    WITH all_expenses AS (
        SELECT amount FROM public.entertainment
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
        UNION ALL
        SELECT amount FROM public.groceries
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
        UNION ALL
        SELECT amount FROM public.health
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
        UNION ALL
        SELECT amount FROM public.housing_rent
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
        UNION ALL
        SELECT amount FROM public.telecom
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
        UNION ALL
        SELECT amount FROM public.travel
        WHERE user_last_session = v_user_session
          AND currency = p_currency_id
          AND "date" >= v_day_start AND "date" < v_day_end
    )
    SELECT COALESCE(SUM(amount), 0) INTO v_total_expenses FROM all_expenses;

    RETURN v_total_expenses;
END;
$$;

