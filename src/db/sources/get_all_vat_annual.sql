-- DROP FUNCTION public.get_all_expenses_annual();

/**
 * FUNCTION: get_all_vat_annual(p_currency_id integer DEFAULT 3)
 * PURPOSE: Calculates total annual VAT across all categories for the current user
 * RETURNS: double precision - Total annual VAT sum
 *
 * NOTES:
 * - Filters by specified currency ID (default = 3)
 * - Targets previous year data
 * - Sums vat values from: entertainment, groceries, health, housing_rent, telecom, travel
 */
CREATE OR REPLACE FUNCTION public.get_all_vat_annual(p_currency_id integer DEFAULT 3)
    RETURNS DOUBLE PRECISION
    LANGUAGE plpgsql
AS $function$
DECLARE
    v_user_session              text;
    v_year_start            timestamp;
    v_year_end              timestamp;
    v_total_vat             double precision := 0.0;
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

    -- Aggregate all vat from the 6 expense categories
WITH all_vat_annual AS (
    SELECT vat FROM public.entertainment
    WHERE user_last_session = v_user_session AND currency = p_currency_id
      AND "date" >= v_year_start AND "date" < v_year_end
    UNION ALL
    SELECT vat FROM public.groceries
    WHERE user_last_session = v_user_session AND currency = p_currency_id
      AND "date" >= v_year_start AND "date" < v_year_end
    UNION ALL
    SELECT vat FROM public.health
    WHERE user_last_session = v_user_session AND currency = p_currency_id
      AND "date" >= v_year_start AND "date" < v_year_end
    UNION ALL
    SELECT vat FROM public.housing_rent
    WHERE user_last_session = v_user_session AND currency = p_currency_id
      AND "date" >= v_year_start AND "date" < v_year_end
    UNION ALL
    SELECT vat FROM public.telecom
    WHERE user_last_session = v_user_session AND currency = p_currency_id
      AND "date" >= v_year_start AND "date" < v_year_end
    UNION ALL
    SELECT vat FROM public.travel
    WHERE user_last_session = v_user_session AND currency = 3
      AND "date" >= v_year_start AND "date" < v_year_end
)
SELECT COALESCE(SUM(vat), 0) INTO v_total_vat FROM all_vat_annual;

v_total_vat := ROUND(v_total_vat::NUMERIC, 2)::DOUBLE PRECISION;

RETURN v_total_vat;
END;
$function$;