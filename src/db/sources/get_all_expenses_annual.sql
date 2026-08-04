-- DROP FUNCTION public.get_all_expenses_annual();

/**
 * FUNCTION: get_all_expenses_annual()
 * PURPOSE: Calculates total annual expenses across all categories for the current user
 * RETURNS: int4 - Total annual expenses sum
 *
 * NOTES:
 * - Filters by currency ID = 3. Will be flexible in the future to allow user selection of currency
 * - Targets previous year data
 * - Sums expenses from: entertainment, groceries, health, housing_rent, telecom, travel
 */
CREATE OR REPLACE FUNCTION public.get_all_expenses_annual()
    RETURNS int4
    LANGUAGE plpgsql
AS $function$
DECLARE
v_user_session              text;
    v_year_start            timestamp;
    v_year_end              timestamp;
    v_total_expenses        int4 := 0;
BEGIN

    -- Cache the user session to avoid multiple function calls (performance optimization)
    v_user_session := public.get_last_login();

    -- validation
    IF v_user_session IS NULL THEN
        RAISE EXCEPTION 'User session not found';
    END IF;

    -- Calculate date range for previous calendar year (inclusive start, exclusive end)
    v_year_start := date_trunc('year', current_date - interval '1 year');
    v_year_end := date_trunc('year', current_date);

    -- Aggregate all expenses from the 6 expense categories
    WITH all_expenses AS (
        SELECT amount FROM public.entertainment
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
        UNION ALL
        SELECT amount FROM public.groceries
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
        UNION ALL
        SELECT amount FROM public.health
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
        UNION ALL
        SELECT amount FROM public.housing_rent
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
        UNION ALL
        SELECT amount FROM public.telecom
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
        UNION ALL
        SELECT amount FROM public.travel
        WHERE user_last_session = v_user_session AND currency = 3
          AND "date" >= v_year_start AND "date" < v_year_end
    )
    SELECT COALESCE(SUM(amount), 0) INTO v_total_expenses FROM all_expenses;

RETURN v_total_expenses;
END;
$function$;
