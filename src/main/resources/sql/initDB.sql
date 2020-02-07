DROP TABLE IF EXISTS users_skills;
DROP TABLE IF EXISTS usersId;
DROP TABLE IF EXISTS skillsId;
DROP TABLE IF EXISTS teams_projects;
DROP TABLE IF EXISTS teamsId;
DROP TABLE IF EXISTS projectsId;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE projectsId(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  badget REAL NOT NULL,
  customer_id INTEGER,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE teamsId(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE teams_projects(
  team_id INTEGER,
  project_id INTEGER,
  FOREIGN KEY (team_id) REFERENCES teamsId(id),
  FOREIGN KEY (project_id) REFERENCES projectsId(id),
  UNIQUE (team_id, project_id)
);

CREATE TABLE skillsId(
  id INTEGER auto_increment PRIMARY KEY,
  name VARCHAR(256) NOT NULL
);

CREATE TABLE usersId(
  id INTEGER auto_increment PRIMARY KEY,
  first_name VARCHAR(256) NOT NULL,
  last_name VARCHAR(256) NOT NULL,
  specialty VARCHAR(256) NOT NULL,
  team_id INTEGER,
  FOREIGN KEY (team_id) REFERENCES usersId(id)
);

CREATE TABLE users_skills(
  user_id INTEGER NOT NULL,
  skill_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES usersId(id),
  FOREIGN KEY (skill_id) REFERENCES skillsId(id),
  UNIQUE (user_id, skill_id)
);