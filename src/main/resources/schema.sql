CREATE SEQUENCE IF NOT EXISTS public.users_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE IF NOT EXISTS public.shared_files_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE IF NOT EXISTS public.users
(
  id bigint NOT NULL,
  login character varying(40) NOT NULL,
  password character varying(40) NOT NULL,
  first_name character varying(40) NOT NULL,
  middle_name character varying(40) NOT NULL,
  last_name character varying(40) NOT NULL,
  role character varying(15) NOT NULL,
  login_count integer NOT NULL DEFAULT 0,
  traffic_limit integer NOT NULL DEFAULT 0,

  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_unique UNIQUE (login)
);

CREATE TABLE IF NOT EXISTS public.shared_files
(
  id bigint NOT NULL,
  user_owner_id bigint NOT NULL,
  path character varying(255) NOT NULL,

  CONSTRAINT shared_files_pkey PRIMARY KEY (id),
  CONSTRAINT shared_files_fkey FOREIGN KEY (user_owner_id) REFERENCES public.users (id),
  CONSTRAINT shared_files_unique UNIQUE (path, user_owner_id)
);

CREATE TABLE IF NOT EXISTS public.shared_access_rights
(
  id bigint NOT NULL,
  file_right character varying(15) NOT NULL,
  CONSTRAINT shared_access_rights_pkey PRIMARY KEY (id),
  CONSTRAINT shared_access_rights_unique UNIQUE (file_right)
);

CREATE TABLE IF NOT EXISTS public.shared_access
(
  user_id bigint NOT NULL,
  file_id bigint NOT NULL,
  right_id int NOT NULL,
  CONSTRAINT shared_access_pkey PRIMARY KEY (file_id, user_id),
  CONSTRAINT shared_access_file_fkey FOREIGN KEY (file_id) REFERENCES public.shared_files (id),
  CONSTRAINT shared_access_user_fkey FOREIGN KEY (user_id) REFERENCES public.users (id),
  CONSTRAINT shared_access_rights_fkey FOREIGN KEY (right_id) REFERENCES public.shared_access_rights(id)
);