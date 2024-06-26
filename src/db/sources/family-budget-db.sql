CREATE TABLE IF NOT EXISTS "currency" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"name" varchar(3) NOT NULL UNIQUE,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "income" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"target_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "groceries" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "housing_rent" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "travel" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"destination" varchar(255) NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "entertainment" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "health" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "telecom" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	"amount" bigint NOT NULL,
	"currency" bigint NOT NULL,
	"date" timestamp with time zone NOT NULL,
	"source_card" bigint NOT NULL,
	"comments" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "expenses_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "income_type" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"type" varchar(255) NOT NULL,
	PRIMARY KEY ("id")
);


ALTER TABLE "income" ADD CONSTRAINT "income_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "groceries" ADD CONSTRAINT "groceries_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "groceries" ADD CONSTRAINT "groceries_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "housing_rent" ADD CONSTRAINT "housing_rent_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "housing_rent" ADD CONSTRAINT "housing_rent_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "travel" ADD CONSTRAINT "travel_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "travel" ADD CONSTRAINT "travel_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "entertainment" ADD CONSTRAINT "entertainment_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "entertainment" ADD CONSTRAINT "entertainment_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "health" ADD CONSTRAINT "health_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "health" ADD CONSTRAINT "health_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");
ALTER TABLE "telecom" ADD CONSTRAINT "telecom_fk1" FOREIGN KEY ("type") REFERENCES "expenses_type"("type");

ALTER TABLE "telecom" ADD CONSTRAINT "telecom_fk3" FOREIGN KEY ("currency") REFERENCES "currency"("name");

ALTER TABLE "income_type" ADD CONSTRAINT "income_type_fk1" FOREIGN KEY ("type") REFERENCES "income"("type");