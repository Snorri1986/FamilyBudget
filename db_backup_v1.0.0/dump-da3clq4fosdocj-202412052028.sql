--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.6

-- Started on 2024-12-05 20:28:28

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
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4421 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 243 (class 1255 OID 24671464)
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
-- TOC entry 240 (class 1255 OID 24477260)
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
-- TOC entry 244 (class 1255 OID 24672167)
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
-- TOC entry 241 (class 1255 OID 24669684)
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
-- TOC entry 239 (class 1255 OID 24475142)
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
-- TOC entry 255 (class 1255 OID 24673323)
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
-- TOC entry 242 (class 1255 OID 24670575)
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
-- TOC entry 220 (class 1259 OID 17379988)
-- Name: currency; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.currency (
    id bigint NOT NULL,
    name character varying(3) NOT NULL
);


ALTER TABLE public.currency OWNER TO udh6tl33aq5jhc;

--
-- TOC entry 219 (class 1259 OID 17379987)
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
-- TOC entry 234 (class 1259 OID 19388482)
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
-- TOC entry 233 (class 1259 OID 19388481)
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
-- TOC entry 222 (class 1259 OID 17386485)
-- Name: expenses_type; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.expenses_type (
    id bigint NOT NULL,
    e_type character varying(255) NOT NULL
);


ALTER TABLE public.expenses_type OWNER TO udh6tl33aq5jhc;

--
-- TOC entry 221 (class 1259 OID 17386484)
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
-- TOC entry 228 (class 1259 OID 17965391)
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
-- TOC entry 227 (class 1259 OID 17965390)
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
-- TOC entry 236 (class 1259 OID 19526136)
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
-- TOC entry 235 (class 1259 OID 19526135)
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
-- TOC entry 230 (class 1259 OID 19386367)
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
-- TOC entry 229 (class 1259 OID 19386366)
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
-- TOC entry 226 (class 1259 OID 17964193)
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
-- TOC entry 225 (class 1259 OID 17964192)
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
-- TOC entry 224 (class 1259 OID 17386558)
-- Name: income_type; Type: TABLE; Schema: public; Owner: udh6tl33aq5jhc
--

CREATE TABLE public.income_type (
    id bigint NOT NULL,
    inc_type character varying(255) NOT NULL
);


ALTER TABLE public.income_type OWNER TO udh6tl33aq5jhc;

--
-- TOC entry 223 (class 1259 OID 17386557)
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
-- TOC entry 238 (class 1259 OID 19526990)
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
-- TOC entry 237 (class 1259 OID 19526989)
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
-- TOC entry 232 (class 1259 OID 19387280)
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
-- TOC entry 231 (class 1259 OID 19387279)
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
-- TOC entry 4397 (class 0 OID 17379988)
-- Dependencies: 220
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.currency (id, name) FROM stdin;
1	EUR
2	UAH
3	DKK
4	USD
\.


--
-- TOC entry 4411 (class 0 OID 19388482)
-- Dependencies: 234
-- Data for Name: entertainment; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.entertainment (id, event_type_id, amount, currency, date, source_card, comments) FROM stdin;
\.


--
-- TOC entry 4399 (class 0 OID 17386485)
-- Dependencies: 222
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
\.


--
-- TOC entry 4405 (class 0 OID 17965391)
-- Dependencies: 228
-- Data for Name: groceries; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.groceries (id, g_type, amount, currency, date, source_card, comments) FROM stdin;
\.


--
-- TOC entry 4413 (class 0 OID 19526136)
-- Dependencies: 236
-- Data for Name: health; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.health (id, h_type_id, amount, currency, date, source_card, comments) FROM stdin;
\.


--
-- TOC entry 4407 (class 0 OID 19386367)
-- Dependencies: 230
-- Data for Name: housing_rent; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.housing_rent (id, hr_type_id, amount, currency, date, source_card, comments) FROM stdin;
\.


--
-- TOC entry 4403 (class 0 OID 17964193)
-- Dependencies: 226
-- Data for Name: income; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.income (id, i_type, amount, currency, date, target_card, comments) FROM stdin;
\.


--
-- TOC entry 4401 (class 0 OID 17386558)
-- Dependencies: 224
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
-- TOC entry 4415 (class 0 OID 19526990)
-- Dependencies: 238
-- Data for Name: telecom; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.telecom (id, t_type_id, amount, currency, date, source_card, comments) FROM stdin;
\.


--
-- TOC entry 4409 (class 0 OID 19387280)
-- Dependencies: 232
-- Data for Name: travel; Type: TABLE DATA; Schema: public; Owner: udh6tl33aq5jhc
--

COPY public.travel (id, tr_type_id, amount, currency, date, source_card, destination, comments) FROM stdin;
\.


--
-- TOC entry 4423 (class 0 OID 0)
-- Dependencies: 219
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.currency_id_seq', 33, true);


--
-- TOC entry 4424 (class 0 OID 0)
-- Dependencies: 233
-- Name: entertainment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.entertainment_id_seq', 66, true);


--
-- TOC entry 4425 (class 0 OID 0)
-- Dependencies: 221
-- Name: expenses_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.expenses_type_id_seq', 33, true);


--
-- TOC entry 4426 (class 0 OID 0)
-- Dependencies: 227
-- Name: groceries_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.groceries_id_seq', 33, true);


--
-- TOC entry 4427 (class 0 OID 0)
-- Dependencies: 235
-- Name: health_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.health_id_seq', 33, true);


--
-- TOC entry 4428 (class 0 OID 0)
-- Dependencies: 229
-- Name: housing_rent_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.housing_rent_id_seq', 33, true);


--
-- TOC entry 4429 (class 0 OID 0)
-- Dependencies: 225
-- Name: income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.income_id_seq', 165, true);


--
-- TOC entry 4430 (class 0 OID 0)
-- Dependencies: 223
-- Name: income_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.income_type_id_seq', 33, true);


--
-- TOC entry 4431 (class 0 OID 0)
-- Dependencies: 237
-- Name: telecom_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.telecom_id_seq', 33, true);


--
-- TOC entry 4432 (class 0 OID 0)
-- Dependencies: 231
-- Name: travel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: udh6tl33aq5jhc
--

SELECT pg_catalog.setval('public.travel_id_seq', 33, true);


--
-- TOC entry 4216 (class 2606 OID 17379994)
-- Name: currency currency_name_key; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_name_key UNIQUE (name);


--
-- TOC entry 4218 (class 2606 OID 17379992)
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


--
-- TOC entry 4232 (class 2606 OID 19388486)
-- Name: entertainment entertainment_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_pkey PRIMARY KEY (id);


--
-- TOC entry 4220 (class 2606 OID 17386489)
-- Name: expenses_type expenses_type_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.expenses_type
    ADD CONSTRAINT expenses_type_pkey PRIMARY KEY (id);


--
-- TOC entry 4226 (class 2606 OID 17965395)
-- Name: groceries groceries_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_pkey PRIMARY KEY (id);


--
-- TOC entry 4234 (class 2606 OID 19526140)
-- Name: health health_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_pkey PRIMARY KEY (id);


--
-- TOC entry 4228 (class 2606 OID 19386371)
-- Name: housing_rent housing_rent_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_pkey PRIMARY KEY (id);


--
-- TOC entry 4224 (class 2606 OID 17964197)
-- Name: income income_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_pkey PRIMARY KEY (id);


--
-- TOC entry 4222 (class 2606 OID 17386562)
-- Name: income_type income_type_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income_type
    ADD CONSTRAINT income_type_pkey PRIMARY KEY (id);


--
-- TOC entry 4236 (class 2606 OID 19526994)
-- Name: telecom telecom_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_pkey PRIMARY KEY (id);


--
-- TOC entry 4230 (class 2606 OID 19387286)
-- Name: travel travel_pkey; Type: CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_pkey PRIMARY KEY (id);


--
-- TOC entry 4245 (class 2606 OID 19388918)
-- Name: entertainment entertainment_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_fk1 FOREIGN KEY (event_type_id) REFERENCES public.expenses_type(id);


--
-- TOC entry 4246 (class 2606 OID 19389189)
-- Name: entertainment entertainment_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.entertainment
    ADD CONSTRAINT entertainment_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4239 (class 2606 OID 17965430)
-- Name: groceries groceries_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_fk1 FOREIGN KEY (g_type) REFERENCES public.expenses_type(id);


--
-- TOC entry 4240 (class 2606 OID 19385323)
-- Name: groceries groceries_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.groceries
    ADD CONSTRAINT groceries_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4247 (class 2606 OID 19526312)
-- Name: health health_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_fk1 FOREIGN KEY (h_type_id) REFERENCES public.expenses_type(id);


--
-- TOC entry 4248 (class 2606 OID 19526609)
-- Name: health health_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.health
    ADD CONSTRAINT health_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4241 (class 2606 OID 19386408)
-- Name: housing_rent housing_rent_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_fk1 FOREIGN KEY (hr_type_id) REFERENCES public.expenses_type(id);


--
-- TOC entry 4242 (class 2606 OID 19386849)
-- Name: housing_rent housing_rent_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.housing_rent
    ADD CONSTRAINT housing_rent_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4237 (class 2606 OID 17964321)
-- Name: income income_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_fk1 FOREIGN KEY (i_type) REFERENCES public.income_type(id);


--
-- TOC entry 4238 (class 2606 OID 17964404)
-- Name: income income_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.income
    ADD CONSTRAINT income_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4249 (class 2606 OID 19527127)
-- Name: telecom telecom_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_fk1 FOREIGN KEY (t_type_id) REFERENCES public.expenses_type(id);


--
-- TOC entry 4250 (class 2606 OID 19527397)
-- Name: telecom telecom_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.telecom
    ADD CONSTRAINT telecom_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4243 (class 2606 OID 19387452)
-- Name: travel travel_fk1; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_fk1 FOREIGN KEY (tr_type_id) REFERENCES public.expenses_type(id);


--
-- TOC entry 4244 (class 2606 OID 19387741)
-- Name: travel travel_fk3; Type: FK CONSTRAINT; Schema: public; Owner: udh6tl33aq5jhc
--

ALTER TABLE ONLY public.travel
    ADD CONSTRAINT travel_fk3 FOREIGN KEY (currency) REFERENCES public.currency(id);


--
-- TOC entry 4422 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO udh6tl33aq5jhc;


-- Completed on 2024-12-05 20:28:34

--
-- PostgreSQL database dump complete
--
