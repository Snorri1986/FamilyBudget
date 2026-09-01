create or replace function get_last_ten_telecom_cash()
    returns TABLE(ex_type_id integer, amount bigint, currency bigint, date timestamp with time zone, opertype text, comments character varying)
    language sql
as
$$
SELECT
    c_log.ex_type_id,
    c_log.amount,
    c_log.currency,
    c_log.date,
    'Cash'::text AS opertype,
    c_log.comments
FROM public.cash_operations_log AS c_log
         JOIN public.cash_expense_id AS c_exp ON c_log.ex_type_id = c_exp.exp_cash_id
WHERE c_log.user_last_session = public.get_last_login()
  AND c_log.user_last_session IS NOT NULL
  AND c_exp.exp_area = 'Telecom'
ORDER BY c_log.date DESC
LIMIT 10;
$$;