CREATE OR REPLACE FUNCTION public.i_location(i_country text, i_city text, i_vat integer)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
	DECLARE
           v_username text;
	BEGIN

        -- validation --
        IF i_country IS NULL OR trim(i_country) = '' THEN
            RAISE EXCEPTION 'i_country must not be NULL or empty';
        END IF;

        IF i_city IS NULL OR trim(i_city) = '' THEN
            RAISE EXCEPTION 'i_city must not be NULL or empty';
        END IF;

        IF i_vat IS NULL THEN
            RAISE EXCEPTION 'i_vat must not be NULL';
        END IF;

	    -- user's location --
         v_username := get_last_login();

         IF v_username IS NULL THEN
            RAISE EXCEPTION 'get_last_login() returned NULL - cannot update users';
         END IF;

         UPDATE public.users SET country = i_country, city = i_city
         WHERE username = v_username;

        -- update/insert VAT rule --
         INSERT INTO public.vat_rules (country, city, vat_size)
         VALUES (i_country, i_city, i_vat)
         ON CONFLICT (country, city)
         DO UPDATE SET vat_size = EXCLUDED.vat_size;

	END;
$function$
;
