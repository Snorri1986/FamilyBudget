-- DROP FUNCTION public.get_all_expenses_monthly();

/**
 * FUNCTION: get_all_expenses_monthly()
 * PURPOSE: Calculates total monthly expenses across all categories for the current user
 * RETURNS: int4 - Total monthly expenses sum
 *
 * NOTES:
 * - Filters by currency ID = 3
 * - Targets previous calendar month data
 * - Sums expenses from: entertainment, groceries, health, housing_rent, telecom, travel
 */
CREATE OR REPLACE FUNCTION public.get_all_expenses_monthly()
	RETURNS int4
	LANGUAGE plpgsql
AS $function$
DECLARE
	v_user_session          bigint;
	v_month_start           timestamp;
	v_month_end             timestamp;
	v_total_expenses        int4 := 0;
BEGIN

	-- Cache the user session to avoid multiple function calls (performance optimization)
	v_user_session := public.get_last_login();

	-- Define the previous month's date range
	v_month_start := date_trunc('month', current_date - interval '1 month');
	v_month_end := date_trunc('month', current_date);

	-- Aggregate all expenses from the 6 expense categories
	SELECT COALESCE(
		(SELECT sum(amount) FROM public.entertainment
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	+ COALESCE(
		(SELECT sum(amount) FROM public.groceries
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	+ COALESCE(
		(SELECT sum(amount) FROM public.health
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	+ COALESCE(
		(SELECT sum(amount) FROM public.housing_rent
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	+ COALESCE(
		(SELECT sum(amount) FROM public.telecom
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	+ COALESCE(
		(SELECT sum(amount) FROM public.travel
		 WHERE user_last_session = v_user_session AND currency = 3
		   AND date >= v_month_start AND date < v_month_end), 0)
	INTO v_total_expenses;

	RETURN v_total_expenses;
END;
$function$;
