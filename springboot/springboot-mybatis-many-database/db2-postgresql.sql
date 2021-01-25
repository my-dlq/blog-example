CREATE TABLE "public"."t_account" (
  "id" int8 NOT NULL,
  "username" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "update_time" timestamp(6) NOT NULL
)
;

COMMENT ON COLUMN "public"."t_account"."id" IS '主键';
COMMENT ON COLUMN "public"."t_account"."username" IS '用户名';
COMMENT ON COLUMN "public"."t_account"."password" IS '密码';
COMMENT ON COLUMN "public"."t_account"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_account"."update_time" IS '更新时间';

INSERT INTO "public"."t_account" VALUES (1, 'mydlq', '123456', '2021-01-01 00:00:00', '2021-01-01 00:00:00');

ALTER TABLE "public"."t_account" ADD CONSTRAINT "account_pkey" PRIMARY KEY ("id");