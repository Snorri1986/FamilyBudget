-- DROP FUNCTION public.get_all_expenses_daily();

CREATE OR REPLACE FUNCTION public.get_all_expenses_daily()
	RETURNS int4
	LANGUAGE plpgsql
AS $function$
	DECLARE
          sum_daily_entertainment       int4;
          sum_daily_groceries           int4;
          sum_daily_health              int4;
          sum_daily_housing_rent        int4;
          sum_daily_telecom             int4;
          sum_daily_travel              int4;
          sum_array                     int4[];
          val                           int4;
          sum_final  int4 := 0;
    BEGIN

          -- data gathering --
          SELECT sum(amount) INTO sum_daily_entertainment FROM public.entertainment
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

          SELECT sum(amount) INTO sum_daily_groceries FROM public.groceries
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

         SELECT sum(amount) INTO sum_daily_health FROM public.health
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

          SELECT sum(amount) INTO sum_daily_housing_rent FROM public.housing_rent
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

          SELECT sum(amount) INTO sum_daily_telecom FROM public.telecom
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

          SELECT sum(amount) INTO sum_daily_travel FROM public.travel
          WHERE user_last_session = public.get_last_login()
          AND currency = 3
          AND date::date = current_date;

          -- data collection --
          sum_array := ARRAY[sum_daily_entertainment,sum_daily_groceries,sum_daily_health,
                       sum_daily_housing_rent,sum_daily_telecom,sum_daily_travel];


          -- data validation --
          FOREACH val IN ARRAY sum_array LOOP
          IF val IS NOT NULL THEN
          sum_final := sum_final + val;
          END IF;
          END LOOP;

          RETURN sum_final;
          END;
$function$
;