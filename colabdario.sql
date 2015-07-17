-- Database: colabdario

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE colabdario;

CREATE DATABASE colabdario WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
ALTER DATABASE colbdario OWNER TO postgres;

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1967 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 176 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1969 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;




-- Table: user_table

-- DROP TABLE user_table;

CREATE TABLE user_table
(
  user_id serial NOT NULL,
  name text,
  email text,
  login text,
  password text
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_table
  OWNER TO postgres;


-- Table: course_table

-- DROP TABLE course_table;

CREATE TABLE course_table
(
  course_id serial NOT NULL,
  name text,
  code text,
  start_date date,
  end_date date
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.course_table
  OWNER TO postgres;

-- Table: class_table

-- DROP TABLE class_table;

CREATE TABLE class_table
(
  course_id integer,
  class_id serial NOT NULL,
  week_day text,
  start_hour time without time zone,
  end_hour time without time zone,
  date date
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.class_table
  OWNER TO postgres;

-- Table: user_course

-- DROP TABLE user_course;

CREATE TABLE user_course
(
  course_id integer,
  user_id integer
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.user_course
  OWNER TO postgres;



