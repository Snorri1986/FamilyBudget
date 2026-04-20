CREATE OR REPLACE FUNCTION public.calculate_vat(i_amount integer)
	RETURNS float8
    LANGUAGE plpgsql
AS $function$
    DECLARE
          active_user      text;
          location_city    text;
          location_country text;
          vat              integer;
          vat_calculated   float8;
	BEGIN
          active_user := get_last_login();

          -- get user's location --
          SELECT city INTO location_city FROM public.users
          WHERE username = active_user;

          SELECT country INTO location_country FROM public.users
          WHERE username = active_user;

          -- get VAT value --
          SELECT vat_size INTO vat FROM public.vat_rules
          WHERE country = location_country
          AND city = location_city;

          -- calculate VAT value and return it --
          vat_calculated := (vat/100.0) * i_amount;

          RETURN vat_calculated;
     END;
$function$