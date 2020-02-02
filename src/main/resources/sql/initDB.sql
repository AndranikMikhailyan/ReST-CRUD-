DROP TABLE IF EXISTS users_skills;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS teams_projects;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE projects(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  badget REAL NOT NULL,
  customer_id INTEGER,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE teams(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE teams_projects(
  team_id INTEGER,
  project_id INTEGER,
  FOREIGN KEY (team_id) REFERENCES teams(id),
  FOREIGN KEY (project_id) REFERENCES projects(id),
  UNIQUE (team_id, project_id)
);

CREATE TABLE skills(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE users(
  id INTEGER auto_increment PRIMARY KEY,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  specialty VARCHAR(256) NOT NULL,
  team_id INTEGER,
  FOREIGN KEY (team_id) REFERENCES users(id)
);

CREATE TABLE users_skills(
  user_id INTEGER NOT NULL,
  skill_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (skill_id) REFERENCES skills(id),
  UNIQUE (user_id, skill_id)
);