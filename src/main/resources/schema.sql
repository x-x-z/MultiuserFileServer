CREATE SEQUENCE public.users_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE public.shared_files_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE IF NOT EXISTS public.users
(
  user_id bigint NOT NULL,
  login character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  first_name character varying(255) NOT NULL,
  middle_name character varying(255) NOT NULL,
  last_name character varying(255) NOT NULL,
  role character varying(255) NOT NULL,
  login_count integer NOT NULL,
  traffic_limit integer NOT NULL,

  CONSTRAINT users_pkey PRIMARY KEY (user_id),
  CONSTRAINT users_unique UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS public.shared_files
(
  file_id bigint NOT NULL,
  user_id bigint NOT NULL,
  path character varying(255) NOT NULL,
  size bigint NOT NULL,

  CONSTRAINT shared_files_pkey PRIMARY KEY (file_id),
  CONSTRAINT shared_files_fkey FOREIGN KEY (user_id) REFERENCES public.users (user_id),
  CONSTRAINT shared_files_unique UNIQUE (path, user_id)
);

CREATE TABLE public.shared_access
(
  user_id bigint,
  file_id bigint NOT NULL,
  CONSTRAINT shared_access_pkey PRIMARY KEY (file_id),
  CONSTRAINT shared_access_file_fkey FOREIGN KEY (file_id) REFERENCES public.shared_files (file_id),
  CONSTRAINT shared_access_user_fkey FOREIGN KEY (user_id) REFERENCES public.users (user_id)
);