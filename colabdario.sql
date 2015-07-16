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


