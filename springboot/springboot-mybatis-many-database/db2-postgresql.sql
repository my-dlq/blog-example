CREATE DATABASE db2
    WITH 
    OWNER = postgre
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE public.t_account (
    id bigint NOT NULL,
    username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    create_time timestamp without time zone NOT NULL,
    update_time timestamp without time zone NOT NULL,
    CONSTRAINT account_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.t_account OWNER to postgre;

INSERT INTO public.t_account(id, username, password, create_time, update_time) 
VALUES (1, 'mydlq', '123456', '2021-01-01', '2021-01-01');