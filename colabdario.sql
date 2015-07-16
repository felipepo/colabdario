-- Database: colabdario

DROP DATABASE colabdario;

CREATE DATABASE colabdario
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;


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
ALTER TABLE user_table
  OWNER TO postgres;


-- Table: course_table

-- Database: colabdario

DROP DATABASE colabdario;

CREATE DATABASE colabdario
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'pt_BR.UTF-8'
       LC_CTYPE = 'pt_BR.UTF-8'
       CONNECTION LIMIT = -1;



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
ALTER TABLE course_table
  OWNER TO postgres;

-- Table: class_table

-- DROP TABLE class_table;

CREATE TABLE class_table
(
  course_id integer
  class_id serial NOT NULL,
  week_day text,
  start_hour date,
  end_hour date, 
)
WITH (
  OIDS=FALSE
);
ALTER TABLE class_table
  OWNER TO postgres;



