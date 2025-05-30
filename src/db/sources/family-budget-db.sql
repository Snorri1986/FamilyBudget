CREATE TABLE IF NOT EXISTS "currency" (
  "id" BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "name" VARCHAR(3) NOT NULL UNIQUE
);
COMMENT ON TABLE public.currency IS 'type of currencies on system';
COMMENT ON COLUMN public.currency.id IS 'id of record';
COMMENT ON COLUMN public.currency."name" IS 'name of currency';

CREATE TABLE IF NOT EXISTS "income" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"i_type" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"target_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.income IS 'storage of income operations';
COMMENT ON COLUMN public.income.id IS 'id of record';
COMMENT ON COLUMN public.income.i_type IS 'income type';
COMMENT ON COLUMN public.income.amount IS 'value of money';
COMMENT ON COLUMN public.income.currency IS 'currency of operation';
COMMENT ON COLUMN public.income."date" IS 'date of operation';
COMMENT ON COLUMN public.income.target_card IS 'target card';
COMMENT ON COLUMN public.income."comments" IS 'description of operation';
COMMENT ON COLUMN public.income.opertype IS 'type of operation';
COMMENT ON COLUMN public.income.user_last_session IS 'owner of operation';

CREATE TABLE IF NOT EXISTS "groceries" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"g_type" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.groceries IS 'storage of groceries operations';
COMMENT ON COLUMN public.groceries.id IS 'id of record';
COMMENT ON COLUMN public.groceries.g_type IS 'grocery type';
COMMENT ON COLUMN public.groceries.amount IS 'value of money';
COMMENT ON COLUMN public.groceries.currency IS 'currency of operation';
COMMENT ON COLUMN public.groceries."date" IS 'date of operation';
COMMENT ON COLUMN public.groceries.source_card IS 'payment card number';
COMMENT ON COLUMN public.groceries."comments" IS 'description of operation';
COMMENT ON COLUMN public.groceries.opertype IS 'cash or card operation';
COMMENT ON COLUMN public.groceries.user_last_session IS 'owner of operation';

CREATE TABLE IF NOT EXISTS "housing_rent" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"hr_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.housing_rent IS 'storage of housing operations';
COMMENT ON COLUMN public.housing_rent.id IS 'id of record';
COMMENT ON COLUMN public.housing_rent.hr_type_id IS 'type of operation';
COMMENT ON COLUMN public.housing_rent.amount IS 'value of money';
COMMENT ON COLUMN public.housing_rent.currency IS 'currency of operation';
COMMENT ON COLUMN public.housing_rent."date" IS 'date of operation';
COMMENT ON COLUMN public.housing_rent.source_card IS 'payment card';
COMMENT ON COLUMN public.housing_rent."comments" IS 'description of operation';
COMMENT ON COLUMN public.housing_rent.opertype IS 'cash or card operation';
COMMENT ON COLUMN public.housing_rent.user_last_session IS 'owner of operation';

CREATE TABLE IF NOT EXISTS "travel" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"tr_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"destination" varchar(255) NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "entertainment" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"event_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.entertainment IS 'storage for entertainment operations';
COMMENT ON COLUMN public.entertainment.id IS 'id of record';
COMMENT ON COLUMN public.entertainment.event_type_id IS 'type of operation';
COMMENT ON COLUMN public.entertainment.amount IS 'value of money';
COMMENT ON COLUMN public.entertainment.currency IS 'currency of operations';
COMMENT ON COLUMN public.entertainment."date" IS 'time of operations';
COMMENT ON COLUMN public.entertainment.source_card IS 'payment card number';
COMMENT ON COLUMN public.entertainment."comments" IS 'description of operation';
COMMENT ON COLUMN public.entertainment.opertype IS 'cash or card';
COMMENT ON COLUMN public.entertainment.user_last_session IS 'owner of operations';

CREATE TABLE IF NOT EXISTS "health" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"h_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.health IS 'storage for health operations';
COMMENT ON COLUMN public.health.id IS 'id of record';
COMMENT ON COLUMN public.health.h_type_id IS 'health operation type';
COMMENT ON COLUMN public.health.amount IS 'value of money';
COMMENT ON COLUMN public.health.currency IS 'currency of operation';
COMMENT ON COLUMN public.health."date" IS 'date of operation';
COMMENT ON COLUMN public.health.source_card IS 'payment card number';
COMMENT ON COLUMN public.health."comments" IS 'description of operation';
COMMENT ON COLUMN public.health.opertype IS 'cash or card operation';
COMMENT ON COLUMN public.health.user_last_session IS 'owner of operation';

CREATE TABLE IF NOT EXISTS "telecom" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"t_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	"user_last_session" text NOT NULL
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.telecom IS 'storage of telecom operations';
COMMENT ON COLUMN public.telecom.id IS 'id of record';
COMMENT ON COLUMN public.telecom.t_type_id IS 'expenses type';
COMMENT ON COLUMN public.telecom.amount IS 'amount';
COMMENT ON COLUMN public.telecom.currency IS 'currency';
COMMENT ON COLUMN public.telecom."date" IS 'date of operations';
COMMENT ON COLUMN public.telecom.source_card IS 'payment card';
COMMENT ON COLUMN public.telecom."comments" IS 'description of operation';
COMMENT ON COLUMN public.telecom.opertype IS 'card or cash';
COMMENT ON COLUMN public.telecom.user_last_session IS 'owner of operation';


CREATE TABLE IF NOT EXISTS "expenses_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"e_type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.expenses_type IS 'list of expenses with codes';
COMMENT ON COLUMN public.expenses_type.id IS 'id of record';
COMMENT ON COLUMN public.expenses_type.e_type IS 'code';

CREATE TABLE IF NOT EXISTS "income_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"inc_type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);
COMMENT ON TABLE public.income_type IS 'type of income';
COMMENT ON COLUMN public.income_type.id IS 'id of record';
COMMENT ON COLUMN public.income_type.inc_type IS 'income code number';

create table cash_balance
(
    balance integer
);
comment on column cash_balance.balance is 'current cash balance';

CREATE TABLE IF NOT EXISTS "cash_operations_log" (
        "id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
        "optype" bigint NOT NULL,
        "amount" bigint NOT NULL,
        "date" timestamp with time zone NOT NULL,
        "comments" varchar(255) NOT NULL,
        "user_last_session" text NOT NULL,
        currency int8 NULL,
        PRIMARY KEY ("id")
);
COMMENT ON TABLE cash_operations_log  is 'storage of cash operations';
COMMENT ON COLUMN public.cash_operations_log.id IS 'id of record';
COMMENT ON COLUMN public.cash_operations_log.optype IS '0 - expense, 1-income';
COMMENT ON COLUMN public.cash_operations_log.amount IS 'value of money';
COMMENT ON COLUMN public.cash_operations_log."date" IS 'operation date';
COMMENT ON COLUMN public.cash_operations_log."comments" IS 'description of operation';
COMMENT ON COLUMN public.cash_operations_log.user_last_session IS 'owner of record';

CREATE TABLE public.user_last_login (
	login varchar NULL,
	last_login timestamp NULL
);

ALTER TABLE "income" ADD CONSTRAINT "income_fk1" FOREIGN KEY ("i_type") REFERENCES "income_type"("id");
ALTER TABLE "income" ADD CONSTRAINT "income_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "groceries" ADD CONSTRAINT "groceries_fk1" FOREIGN KEY ("g_type") REFERENCES "expenses_type"("id");
ALTER TABLE "groceries" ADD CONSTRAINT "groceries_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "housing_rent" ADD CONSTRAINT "housing_rent_fk1" FOREIGN KEY ("hr_type_id") REFERENCES "expenses_type"("id");
ALTER TABLE "housing_rent" ADD CONSTRAINT "housing_rent_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "travel" ADD CONSTRAINT "travel_fk1" FOREIGN KEY ("tr_type_id") REFERENCES "expenses_type"("id");
ALTER TABLE "travel" ADD CONSTRAINT "travel_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "entertainment" ADD CONSTRAINT "entertainment_fk1" FOREIGN KEY ("event_type_id") REFERENCES "expenses_type"("id");
ALTER TABLE "entertainment" ADD CONSTRAINT "entertainment_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "health" ADD CONSTRAINT "health_fk1" FOREIGN KEY ("h_type_id") REFERENCES "expenses_type"("id");
ALTER TABLE "health" ADD CONSTRAINT "health_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
ALTER TABLE "telecom" ADD CONSTRAINT "telecom_fk1" FOREIGN KEY ("t_type_id") REFERENCES "expenses_type"("id");
ALTER TABLE "telecom" ADD CONSTRAINT "telecom_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("id");
