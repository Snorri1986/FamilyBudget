--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4
-- Dumped by pg_dump version 16.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: _heroku; Type: SCHEMA; Schema: -; Owner: heroku_admin
--

CREATE SCHEMA _heroku;


ALTER SCHEMA _heroku OWNER TO heroku_admin;

--
-- Name: pg_stat_statements; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pg_stat_statements WITH SCHEMA public;


--
-- Name: EXTENSION pg_stat_statements; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pg_stat_statements IS 'track planning and execution statistics of all SQL statements executed';


--
-- Name: create_ext(); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.create_ext() RETURNS event_trigger
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$

DECLARE

  schemaname TEXT;
  databaseowner TEXT;

  r RECORD;

BEGIN

  IF tg_tag = 'CREATE EXTENSION' and current_user != 'rds_superuser' THEN
    FOR r IN SELECT * FROM pg_event_trigger_ddl_commands()
    LOOP
        CONTINUE WHEN r.command_tag != 'CREATE EXTENSION' OR r.object_type != 'extension';

        schemaname = (
            SELECT n.nspname
            FROM pg_catalog.pg_extension AS e
            INNER JOIN pg_catalog.pg_namespace AS n
            ON e.extnamespace = n.oid
            WHERE e.oid = r.objid
        );

        databaseowner = (
            SELECT pg_catalog.pg_get_userbyid(d.datdba)
            FROM pg_catalog.pg_database d
            WHERE d.datname = current_database()
        );
        --RAISE NOTICE 'Record for event trigger %, objid: %,tag: %, current_user: %, schema: %, database_owenr: %', r.object_identity, r.objid, tg_tag, current_user, schemaname, databaseowner;
        IF r.object_identity = 'address_standardizer_data_us' THEN
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'us_gaz');
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'us_lex');
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'us_rules');
        ELSIF r.object_identity = 'amcheck' THEN
            EXECUTE format('GRANT EXECUTE ON FUNCTION %I.bt_index_check TO %I;', schemaname, databaseowner);
            EXECUTE format('GRANT EXECUTE ON FUNCTION %I.bt_index_parent_check TO %I;', schemaname, databaseowner);
        ELSIF r.object_identity = 'dict_int' THEN
            EXECUTE format('ALTER TEXT SEARCH DICTIONARY %I.intdict OWNER TO %I;', schemaname, databaseowner);
        ELSIF r.object_identity = 'pg_partman' THEN
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'part_config');
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'part_config_sub');
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT, UPDATE, INSERT, DELETE', databaseowner, 'custom_time_partitions');
        ELSIF r.object_identity = 'pg_stat_statements' THEN
            EXECUTE format('GRANT EXECUTE ON FUNCTION %I.pg_stat_statements_reset TO %I;', schemaname, databaseowner);
        ELSIF r.object_identity = 'postgis' THEN
            PERFORM _heroku.postgis_after_create();
        ELSIF r.object_identity = 'postgis_raster' THEN
            PERFORM _heroku.postgis_after_create();
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT', databaseowner, 'raster_columns');
            PERFORM _heroku.grant_table_if_exists(schemaname, 'SELECT', databaseowner, 'raster_overviews');
        ELSIF r.object_identity = 'postgis_topology' THEN
            PERFORM _heroku.postgis_after_create();
            EXECUTE format('GRANT USAGE ON SCHEMA topology TO %I;', databaseowner);
            EXECUTE format('GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA topology TO %I;', databaseowner);
            PERFORM _heroku.grant_table_if_exists('topology', 'SELECT, UPDATE, INSERT, DELETE', databaseowner);
            EXECUTE format('GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA topology TO %I;', databaseowner);
        ELSIF r.object_identity = 'postgis_tiger_geocoder' THEN
            PERFORM _heroku.postgis_after_create();
            EXECUTE format('GRANT USAGE ON SCHEMA tiger TO %I;', databaseowner);
            EXECUTE format('GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA tiger TO %I;', databaseowner);
            PERFORM _heroku.grant_table_if_exists('tiger', 'SELECT, UPDATE, INSERT, DELETE', databaseowner);

            EXECUTE format('GRANT USAGE ON SCHEMA tiger_data TO %I;', databaseowner);
            EXECUTE format('GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA tiger_data TO %I;', databaseowner);
            PERFORM _heroku.grant_table_if_exists('tiger_data', 'SELECT, UPDATE, INSERT, DELETE', databaseowner);
        END IF;
    END LOOP;
  END IF;
END;
$$;


ALTER FUNCTION _heroku.create_ext() OWNER TO heroku_admin;

--
-- Name: drop_ext(); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.drop_ext() RETURNS event_trigger
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$

DECLARE

  schemaname TEXT;
  databaseowner TEXT;

  r RECORD;

BEGIN

  IF tg_tag = 'DROP EXTENSION' and current_user != 'rds_superuser' THEN
    FOR r IN SELECT * FROM pg_event_trigger_dropped_objects()
    LOOP
      CONTINUE WHEN r.object_type != 'extension';

      databaseowner = (
            SELECT pg_catalog.pg_get_userbyid(d.datdba)
            FROM pg_catalog.pg_database d
            WHERE d.datname = current_database()
      );

      --RAISE NOTICE 'Record for event trigger %, objid: %,tag: %, current_user: %, database_owner: %, schemaname: %', r.object_identity, r.objid, tg_tag, current_user, databaseowner, r.schema_name;

      IF r.object_identity = 'postgis_topology' THEN
          EXECUTE format('DROP SCHEMA IF EXISTS topology');
      END IF;
    END LOOP;

  END IF;
END;
$$;


ALTER FUNCTION _heroku.drop_ext() OWNER TO heroku_admin;

--
-- Name: extension_before_drop(); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.extension_before_drop() RETURNS event_trigger
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$

DECLARE

  query TEXT;

BEGIN
  query = (SELECT current_query());

  -- RAISE NOTICE 'executing extension_before_drop: tg_event: %, tg_tag: %, current_user: %, session_user: %, query: %', tg_event, tg_tag, current_user, session_user, query;
  IF tg_tag = 'DROP EXTENSION' and not pg_has_role(session_user, 'rds_superuser', 'MEMBER') THEN
    -- DROP EXTENSION [ IF EXISTS ] name [, ...] [ CASCADE | RESTRICT ]
    IF (regexp_match(query, 'DROP\s+EXTENSION\s+(IF\s+EXISTS)?.*(plpgsql)', 'i') IS NOT NULL) THEN
      RAISE EXCEPTION 'The plpgsql extension is required for database management and cannot be dropped.';
    END IF;
  END IF;
END;
$$;


ALTER FUNCTION _heroku.extension_before_drop() OWNER TO heroku_admin;

--
-- Name: grant_table_if_exists(text, text, text, text); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.grant_table_if_exists(alias_schemaname text, grants text, databaseowner text, alias_tablename text DEFAULT NULL::text) RETURNS void
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$

BEGIN

  IF alias_tablename IS NULL THEN
    EXECUTE format('GRANT %s ON ALL TABLES IN SCHEMA %I TO %I;', grants, alias_schemaname, databaseowner);
  ELSE
    IF EXISTS (SELECT 1 FROM pg_tables WHERE pg_tables.schemaname = alias_schemaname AND pg_tables.tablename = alias_tablename) THEN
      EXECUTE format('GRANT %s ON TABLE %I.%I TO %I;', grants, alias_schemaname, alias_tablename, databaseowner);
    END IF;
  END IF;
END;
$$;


ALTER FUNCTION _heroku.grant_table_if_exists(alias_schemaname text, grants text, databaseowner text, alias_tablename text) OWNER TO heroku_admin;

--
-- Name: postgis_after_create(); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.postgis_after_create() RETURNS void
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$
DECLARE
    schemaname TEXT;
    databaseowner TEXT;
BEGIN
    schemaname = (
        SELECT n.nspname
        FROM pg_catalog.pg_extension AS e
        INNER JOIN pg_catalog.pg_namespace AS n ON e.extnamespace = n.oid
        WHERE e.extname = 'postgis'
    );
    databaseowner = (
        SELECT pg_catalog.pg_get_userbyid(d.datdba)
        FROM pg_catalog.pg_database d
        WHERE d.datname = current_database()
    );

    EXECUTE format('GRANT EXECUTE ON FUNCTION %I.st_tileenvelope TO %I;', schemaname, databaseowner);
    EXECUTE format('GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE %I.spatial_ref_sys TO %I;', schemaname, databaseowner);
END;
$$;


ALTER FUNCTION _heroku.postgis_after_create() OWNER TO heroku_admin;

--
-- Name: validate_extension(); Type: FUNCTION; Schema: _heroku; Owner: heroku_admin
--

CREATE FUNCTION _heroku.validate_extension() RETURNS event_trigger
    LANGUAGE plpgsql SECURITY DEFINER
    AS $$

DECLARE

  schemaname TEXT;
  r RECORD;

BEGIN

  IF tg_tag = 'CREATE EXTENSION' and current_user != 'rds_superuser' THEN
    FOR r IN SELECT * FROM pg_event_trigger_ddl_commands()
    LOOP
      CONTINUE WHEN r.command_tag != 'CREATE EXTENSION' OR r.object_type != 'extension';

      schemaname = (
        SELECT n.nspname
        FROM pg_catalog.pg_extension AS e
        INNER JOIN pg_catalog.pg_namespace AS n
        ON e.extnamespace = n.oid
        WHERE e.oid = r.objid
      );

      IF schemaname = '_heroku' THEN
        RAISE EXCEPTION 'Creating extensions in the _heroku schema is not allowed';
      END IF;
    END LOOP;
  END IF;
END;
$$;


ALTER FUNCTION _heroku.validate_extension() OWNER TO heroku_admin;

--
-- Name: i_entertainment(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_entertainment(evn_type_id_val integer, amount_val integer, cur_value integer, oper_date date, src_card integer, comm_val character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO entertainment(event_type_id,amount,currency,date,source_card,comments)
    VALUES (evn_type_id_val,amount_val,cur_value,oper_date,src_card,comm_val);

    SELECT max(id) INTO last_id FROM entertainment;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_entertainment(evn_type_id_val integer, amount_val integer, cur_value integer, oper_date date, src_card integer, comm_val character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_groceries(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_groceries(gtype_value integer, amount_value integer, currency_value integer, oper_date date, src_payment_card integer, comm_value character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO groceries(g_type, amount, currency, date, source_card, comments)
    VALUES (gtype_value, amount_value,currency_value,oper_date,src_payment_card,comm_value);

    SELECT max(id) INTO last_id FROM groceries;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_groceries(gtype_value integer, amount_value integer, currency_value integer, oper_date date, src_payment_card integer, comm_value character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_health(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_health(h_type_id_val integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_val character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO health(h_type_id,amount,currency,date,source_card,comments)
    VALUES (h_type_id_val,amount_val,cur_val,oper_date,src_card,comm_val);

    SELECT max(id) INTO last_id FROM health;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_health(h_type_id_val integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_val character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_housing_rent(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_housing_rent(hr_type_id integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_value character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO housing_rent(hr_type_id,amount,currency,date,source_card,comments)
    VALUES (hr_type_id,amount_val,cur_val,oper_date,src_card,comm_value);

    SELECT max(id) INTO last_id FROM housing_rent;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_housing_rent(hr_type_id integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_value character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_income(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, payment_card integer, comm_value character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO income(i_type, amount, currency, date,target_card, comments)
    VALUES (income_type,amount_value,currency_value,oper_date,payment_card,comm_value);

    SELECT max(id) INTO last_id FROM income;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_income(income_type integer, amount_value integer, currency_value integer, oper_date date, payment_card integer, comm_value character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_telecom(integer, integer, integer, date, integer, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_telecom(t_type_id_val integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_val character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO telecom(t_type_id,amount,currency,date,source_card,comments)
    VALUES (t_type_id_val,amount_val,cur_val,oper_date,src_card,comm_val);

    SELECT max(id) INTO last_id FROM telecom;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_telecom(t_type_id_val integer, amount_val integer, cur_val integer, oper_date date, src_card integer, comm_val character) OWNER TO udh6tl33aq5jhc;

--
-- Name: i_travel(integer, integer, integer, date, integer, character, character); Type: FUNCTION; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE FUNCTION public.i_travel(tr_type_id_val integer, amount_val integer, curr_val integer, oper_date date, src_card_val integer, destin_val character, comm_val character) RETURNS integer
    LANGUAGE plpgsql
    AS $$
    DECLARE
    last_id int;
	
    BEGIN
    
    INSERT INTO travel(tr_type_id,amount,currency,date,source_card,destination,comments)
    VALUES (tr_type_id_val,amount_val,curr_val,oper_date,src_card_val,destin_val,comm_val);

    SELECT max(id) INTO last_id FROM travel;

    RETURN last_id;

	END;
$$;


ALTER FUNCTION public.i_travel(tr_type_id_val integer, amount_val integer, curr_val integer, oper_date date, src_card_val integer, destin_val character, comm_val character) OWNER TO udh6tl33aq5jhc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: currency; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.currency (
    id bigint NOT NULL,
    name character varying(3) NOT NULL
);


ALTER TABLE public.currency OWNER TO udh6tl33aq5jhc;

--
-- Name: currency_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.currency ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.currency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: entertainment; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.entertainment (
    id bigint NOT NULL,
    event_type_id bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.entertainment OWNER TO udh6tl33aq5jhc;

--
-- Name: entertainment_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.entertainment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.entertainment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: expenses_type; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.expenses_type (
    id bigint NOT NULL,
    e_type character varying(255) NOT NULL
);


ALTER TABLE public.expenses_type OWNER TO udh6tl33aq5jhc;

--
-- Name: expenses_type_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.expenses_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.expenses_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: groceries; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.groceries (
    id bigint NOT NULL,
    g_type bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.groceries OWNER TO udh6tl33aq5jhc;

--
-- Name: groceries_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.groceries ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.groceries_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: health; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.health (
    id bigint NOT NULL,
    h_type_id bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.health OWNER TO udh6tl33aq5jhc;

--
-- Name: health_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.health ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.health_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: housing_rent; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.housing_rent (
    id bigint NOT NULL,
    hr_type_id bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.housing_rent OWNER TO udh6tl33aq5jhc;

--
-- Name: housing_rent_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.housing_rent ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.housing_rent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: income; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.income (
    id bigint NOT NULL,
    i_type bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    target_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.income OWNER TO udh6tl33aq5jhc;

--
-- Name: income_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.income ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.income_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: income_type; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.income_type (
    id bigint NOT NULL,
    inc_type character varying(255) NOT NULL
);


ALTER TABLE public.income_type OWNER TO udh6tl33aq5jhc;

--
-- Name: income_type_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.income_type ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.income_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: telecom; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.telecom (
    id bigint NOT NULL,
    t_type_id bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.telecom OWNER TO udh6tl33aq5jhc;

--
-- Name: telecom_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.telecom ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.telecom_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: travel; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.travel (
    id bigint NOT NULL,
    tr_type_id bigint NOT NULL,
    amount bigint NOT NULL,
    currency bigint NOT NULL,
    date timestamp with time zone NOT NULL,
    source_card bigint NOT NULL,
    destination character varying(255) NOT NULL,
    comments character varying(255) NOT NULL
);


ALTER TABLE public.travel OWNER TO udh6tl33aq5jhc;

--
-- Name: travel_id_seq; Type: SEQUENCE; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE public.travel ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.travel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.currency (id, name) FROM stdin;
1	EUR
2	UAH
3	DKK
4	USD
\.


--
-- Data for Name: entertainment; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.entertainment (id, event_type_id, amount, currency, date, source_card, comments) FROM stdin;
67	17	177	4	2024-12-06 00:00:00+00	6285	McDonalds - Hvidovre
100	17	30	4	2024-12-08 00:00:00+00	6285	Julefest Brønshøj Torv
199	17	147	4	2024-12-30 00:00:00+00	6285	IKEA
265	38	79	3	2025-01-04 00:00:00+00	6285	Sushi
298	17	999	2	2025-01-06 00:00:00+00	3464	Костю
331	17	666	2	2025-01-08 00:00:00+00	3464	Heroku monthly payment
364	17	343	2	2025-01-09 00:00:00+00	3464	Netflix
\.


--
-- Data for Name: expenses_type; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.expenses_type (id, e_type) FROM stdin;
1	groceries
2	rent
3	housing
4	kindergarden
5	mobile
6	internet
7	transport
8	travel
9	sport
10	internet_shoping
11	money_transfers
12	others
13	cinema
14	vacation
15	relax
16	homefest
17	other
18	daily
19	weekly
20	weekend
21	fest
22	other
23	dentist
24	regular medical check
25	special doctor
26	swimming pool
27	spa
28	nails
31	renovation
29	electricity
30	houseequipments
32	roaming bundles
33	tickets
34	hotel
35	foodintrip
36	travelentertainment
37	mortage
38	restaurant
39	a-kasse
40	mt sender
41	mt receiver
42	haircut
43	clothes
\.


--
-- Data for Name: groceries; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.groceries (id, g_type, amount, currency, date, source_card, comments) FROM stdin;
34	22	747	4	2024-12-06 00:00:00+00	6285	Rema1000 - Hvidovre
67	22	21	4	2024-12-07 00:00:00+00	6285	Netto Bronshoj
68	22	19	4	2024-12-08 00:00:00+00	6285	dba.dk
69	22	24	4	2024-12-09 00:00:00+00	6285	Netto Astrupvej
70	22	37	4	2024-12-09 00:00:00+00	6285	Rema1000 Kobævenner
100	22	22	4	2024-12-10 00:00:00+00	6285	7Eleven  Friheden
133	22	186	4	2024-12-11 00:00:00+00	6286	Netto Friheden
134	22	139	4	2024-12-11 00:00:00+00	6286	Rema1000 Bronshoj
135	22	16	4	2024-12-12 00:00:00+00	6285	Netto - Astrupvej
166	22	50	4	2024-12-16 00:00:00+00	6285	Netto Astrupvej
199	22	183	4	2024-12-17 00:00:00+00	6285	SuperBrugsen - Bronshoj
202	22	32	4	2024-12-18 00:00:00+00	6285	Lunch at work
232	22	140	4	2024-12-18 00:00:00+00	6285	SuperBrugsen Bronshoj
233	22	50	4	2024-12-19 00:00:00+00	6285	Fotex Husum
265	22	171	4	2024-12-23 00:00:00+00	6285	Rema Husum
266	22	192	4	2024-12-24 00:00:00+00	6285	Netto Brønshøj
267	22	636	4	2024-12-26 00:00:00+00	6285	Rema Bronshoj
268	22	146	4	2024-12-27 00:00:00+00	6285	Normal
269	22	35	4	2024-12-29 00:00:00+00	6285	Netto Bronshoj
270	22	149	4	2024-12-31 00:00:00+00	6285	Rema1000
271	22	352	4	2025-01-01 00:00:00+00	6532	Netto Bronshoj
273	18	95	3	2025-01-02 00:00:00+00	6285	Sweets
274	19	780	3	2025-01-03 00:00:00+00	6285	Rema 1000 Bronshoj
275	18	79	3	2025-01-04 00:00:00+00	6286	Too good to go
276	18	43	3	2025-01-05 00:00:00+00	6285	Netto
277	18	67	3	2025-01-05 00:00:00+00	6825	Rema1000
278	20	143	3	2025-01-05 00:00:00+00	6285	Rema1000
279	18	72	3	2025-01-06 00:00:00+00	6285	Rema 1000
280	18	32	3	2025-01-07 00:00:00+00	6285	Daily lunch
281	18	15	3	2025-01-07 00:00:00+00	6285	Daily snack
282	18	32	3	2025-01-08 00:00:00+00	6285	Lunch at work
283	18	15	3	2025-01-08 00:00:00+00	6285	Cannelsnel
298	18	32	3	2025-01-09 00:00:00+00	6285	Lunch at work
331	18	70	3	2025-01-10 00:00:00+00	6285	Netto
332	19	989	3	2025-01-10 00:00:00+00	6285	Rema1000 - Bronshoj
\.


--
-- Data for Name: health; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.health (id, h_type_id, amount, currency, date, source_card, comments) FROM stdin;
67	22	1750	4	2024-12-19 00:00:00+00	6285	Dentist in Bronshoj
100	22	200	4	2024-12-24 00:00:00+00	6425	Aliexpres
133	22	219	4	2025-01-02 00:00:00+00	6285	Puregym
134	22	200	4	2025-01-02 00:00:00+00	6285	Nails
166	22	300	3	2025-01-04 00:00:00+00	6286	Haircut
\.


--
-- Data for Name: housing_rent; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.housing_rent (id, hr_type_id, amount, currency, date, source_card, comments) FROM stdin;
34	22	215	4	2024-12-07 00:00:00+00	6285	Silvan Norrebro - Brustap
35	22	350	4	2024-12-07 00:00:00+00	6285	Norrebro Elgiganten - Mobile charger
69	22	2500	4	2024-12-17 00:00:00+00	3464	Housing Paladina
166	22	1136	4	2024-12-24 00:00:00+00	6285	IKEA
199	22	16150	4	2025-01-02 00:00:00+00	6285	Rent Jan 2025
200	22	743	4	2025-01-02 00:00:00+00	6285	Electricity jan 2025
232	40	128	3	2025-01-06 00:00:00+00	6285	Send to Hege
\.


--
-- Data for Name: income; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.income (id, i_type, amount, currency, date, target_card, comments) FROM stdin;
166	17	11037	4	2024-12-17 00:00:00+00	3464	Rent Paladina
232	17	2000	4	2024-12-17 23:00:00+00	6285	MobilePay from wife
233	17	1000	4	2024-12-17 23:00:00+00	6285	MobilePay from wife
265	17	31832	4	2024-12-23 00:00:00+00	6285	Salary December
298	17	43	4	2025-01-02 00:00:00+00	6285	Interest
\.


--
-- Data for Name: income_type; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.income_type (id, inc_type) FROM stdin;
13	bonus
14	salary
15	travel_refund
16	shop_refund
17	other
\.


--
-- Data for Name: telecom; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.telecom (id, t_type_id, amount, currency, date, source_card, comments) FROM stdin;
34	12	400	4	2024-12-06 00:00:00+00	3464	Life to wife
35	12	400	4	2024-12-06 00:00:00+00	3464	Life to me
67	12	795	4	2024-12-10 00:00:00+00	3464	Heroku Cloud
100	12	125	4	2024-12-13 00:00:00+00	6285	Protection screen
166	5	1	4	2025-01-01 23:00:00+00	6285	Test7
\.


--
-- Data for Name: travel; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.travel (id, tr_type_id, amount, currency, date, source_card, destination, comments) FROM stdin;
34	12	300	4	2024-12-08 00:00:00+00	6285	Kopenhagen	Rejsekort
100	12	424	4	2024-12-14 00:00:00+00	6285	Skodsborg	SPA
101	12	160	4	2024-12-14 00:00:00+00	6285	Skodsborg	Netto
67	12	1940	4	2024-12-12 00:00:00+00	6285	Skodsborg	SPA
166	12	730	4	2024-12-17 00:00:00+00	6285	Copenhagen	Pendlekort charging
199	12	3912	4	2024-12-18 00:00:00+00	6982	Nice	Nice - Hotel
232	12	50	4	2024-12-20 00:00:00+00	0	Hamburg	Groceries Hamburg
265	12	33	4	2024-12-21 00:00:00+00	0	Hamburg	Tickets in Hamborg
266	12	7	4	2024-12-21 00:00:00+00	6285	Hamburg	Cafe
267	12	8	4	2024-12-22 00:00:00+00	0	Hamburg	Hamburg Metro
268	12	10	4	2024-12-22 00:00:00+00	0	Hamburg	Hamburg transport
269	12	100	4	2024-12-26 00:00:00+00	6285	Copenhagen	Transport card
270	12	21	4	2024-12-31 00:00:00+00	6285	Copenhagen	Rejsekort App
298	34	3418	3	2025-01-06 00:00:00+00	6285	Istambul	Hotel in Instambul
\.


--
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.currency_id_seq', 33, true);


--
-- Name: entertainment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.entertainment_id_seq', 396, true);


--
-- Name: expenses_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.expenses_type_id_seq', 33, true);


--
-- Name: groceries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.groceries_id_seq', 332, true);


--
-- Name: health_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.health_id_seq', 198, true);


--
-- Name: housing_rent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.housing_rent_id_seq', 264, true);


--
-- Name: income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.income_id_seq', 330, true);


--
-- Name: income_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.income_type_id_seq', 33, true);


--
-- Name: telecom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.telecom_id_seq', 198, true);


--
-- Name: travel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.travel_id_seq', 330, true);


--
-- Name: currency currency_name_key; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_name_key UNIQUE (name);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


--
-- Name: entertainment entertainment_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_pkey PRIMARY KEY (id);


--
-- Name: expenses_type expenses_type_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.expenses_type
    ADD CONSTRAINT expenses_type_pkey PRIMARY KEY (id);


--
-- Name: groceries groceries_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_pkey PRIMARY KEY (id);


--
-- Name: health health_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_pkey PRIMARY KEY (id);


--
-- Name: housing_rent housing_rent_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_pkey PRIMARY KEY (id);


--
-- Name: income income_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (id);


--
-- Name: income_type income_type_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income_type
    ADD CONSTRAINT income_type_pkey PRIMARY KEY (id);


--
-- Name: telecom telecom_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_pkey PRIMARY KEY (id);


--
-- Name: travel travel_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_pkey PRIMARY KEY (id);


--
-- Name: entertainment entertainment_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_fk1 FOREIGN KEY (event_type_id) REFERENCES public.expenses_type(id);


--
-- Name: entertainment entertainment_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: groceries groceries_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_fk1 FOREIGN KEY (g_type) REFERENCES public.expenses_type(id);


--
-- Name: groceries groceries_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: health health_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_fk1 FOREIGN KEY (h_type_id) REFERENCES public.expenses_type(id);


--
-- Name: health health_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: housing_rent housing_rent_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_fk1 FOREIGN KEY (hr_type_id) REFERENCES public.expenses_type(id);


--
-- Name: housing_rent housing_rent_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: income income_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_fk1 FOREIGN KEY (i_type) REFERENCES public.income_type(id);


--
-- Name: income income_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: telecom telecom_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_fk1 FOREIGN KEY (t_type_id) REFERENCES public.expenses_type(id);


--
-- Name: telecom telecom_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: travel travel_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_fk1 FOREIGN KEY (tr_type_id) REFERENCES public.expenses_type(id);


--
-- Name: travel travel_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO udh6tl33aq5jhc;


--
-- Name: FUNCTION pg_stat_statements_reset(userid oid, dbid oid, queryid bigint); Type: ACL; Schema: public; Owner: rdsadmin
--

GRANT ALL ON FUNCTION public.pg_stat_statements_reset(userid oid, dbid oid, queryid bigint) TO udh6tl33aq5jhc;


--
-- Name: extension_before_drop; Type: EVENT TRIGGER; Schema: -; Owner: heroku_admin
--

CREATE EVENT TRIGGER extension_before_drop ON ddl_command_start
   EXECUTE FUNCTION _heroku.extension_before_drop();


ALTER EVENT TRIGGER extension_before_drop OWNER TO heroku_admin;

--
-- Name: log_create_ext; Type: EVENT TRIGGER; Schema: -; Owner: heroku_admin
--

CREATE EVENT TRIGGER log_create_ext ON ddl_command_end
   EXECUTE FUNCTION _heroku.create_ext();


ALTER EVENT TRIGGER log_create_ext OWNER TO heroku_admin;

--
-- Name: log_drop_ext; Type: EVENT TRIGGER; Schema: -; Owner: heroku_admin
--

CREATE EVENT TRIGGER log_drop_ext ON sql_drop
   EXECUTE FUNCTION _heroku.drop_ext();


ALTER EVENT TRIGGER log_drop_ext OWNER TO heroku_admin;

--
-- Name: validate_extension; Type: EVENT TRIGGER; Schema: -; Owner: heroku_admin
--

CREATE EVENT TRIGGER validate_extension ON ddl_command_end
   EXECUTE FUNCTION _heroku.validate_extension();


ALTER EVENT TRIGGER validate_extension OWNER TO heroku_admin;

--
-- PostgreSQL database dump complete
--

