CREATE TABLE IF NOT EXISTS "currency" (
  "id" BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  "name" VARCHAR(3) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "income" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"i_type" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"target_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "groceries" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"g_type" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "housing_rent" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"hr_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

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
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "health" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"h_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "telecom" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"t_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"operType" varchar(255) NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "expenses_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"e_type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "income_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"inc_type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

create table cash_balance
(
    balance integer
);

CREATE TABLE IF NOT EXISTS "cash_operations_log" (
        "id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
        "optype" bigint NOT NULL,
        "amount" bigint NOT NULL,
        "date" timestamp with time zone NOT NULL,
        "comments" varchar(255) NOT NULL,
        PRIMARY KEY ("id")
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
