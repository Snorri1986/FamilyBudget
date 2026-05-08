-- DROP FUNCTION public.get_all_expenses_daily();

create function get_all_expenses_daily(p_currency_id integer DEFAULT 3) returns numeric
    language plpgsql
as
$$
DECLARE
    v_user_session TEXT;
    v_today DATE;
    v_total_expenses NUMERIC := 0;
BEGIN
    -- Get the user's last login session
    v_user_session := public.get_last_login();
    v_today := CURRENT_DATE;

    -- Aggregate expenses from all categories
    SELECT COALESCE((
                        SELECT SUM(amount) FROM public.entertainment
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0) +
           COALESCE((
                        SELECT SUM(amount) FROM public.groceries
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0) +
           COALESCE((
                        SELECT SUM(amount) FROM public.health
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0) +
           COALESCE((
                        SELECT SUM(amount) FROM public.housing_rent
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0) +
           COALESCE((
                        SELECT SUM(amount) FROM public.telecom
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0) +
           COALESCE((
                        SELECT SUM(amount) FROM public.travel
                        WHERE user_last_session = v_user_session
                          AND currency = p_currency_id
                          AND DATE(date) = v_today), 0)
    INTO v_total_expenses;
    RETURN v_total_expenses;
END;
$$;

alter function get_all_expenses_daily(integer) owner to u4cg7fn2s82n4v;
