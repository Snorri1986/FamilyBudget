CREATE OR REPLACE FUNCTION public.i_location(i_country character, i_city character, i_vat integer)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
	DECLARE
           vat_city text;
	BEGIN
         -- user's location --
         UPDATE public.users SET country = i_country, city = i_city
         WHERE username = get_last_login();

         -- vat rule --
         SELECT city INTO vat_city FROM public.vat_rules
         WHERE city = i_city;

         IF vat_city IS NULL THEN
            INSERT INTO public.vat_rules(country,city,vat_size)
            VALUES(i_country,i_city,i_vat);
         ELSE
            UPDATE public.vat_rules SET country = i_country,
                                       city = i_city,
                                       vat_size = i_vat
            WHERE country = i_country
            AND city = i_city;
         END IF;
	END;
$function$
;
