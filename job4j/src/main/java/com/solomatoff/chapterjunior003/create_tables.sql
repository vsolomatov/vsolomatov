CREATE TABLE Roles
(
  role_id serial PRIMARY KEY,
  role_name character varying,
  role_description text
);

CREATE TABLE Rules
(
  rule_id serial PRIMARY KEY,
  rule_name character varying NOT NULL,
  rule_description text
);

CREATE TABLE RolesRules
(
  rolesrules_role_id serial references Roles,
  rolesrules_rule_id serial references Rules,
  CONSTRAINT rolesrules_pkey PRIMARY KEY (rolesrules_role_id, rolesrules_rule_id)
);

CREATE TABLE Users
(
  user_id serial PRIMARY KEY,
  user_login character varying NOT NULL,
  user_password character varying NOT NULL,
  user_created_date timestamp without time zone DEFAULT now(),
  user_name text NOT NULL
);

CREATE TABLE UsersRoles
(
  usersroles_user_id integer NOT NULL references Users,
  usersroles_role_id integer NOT NULL references Roles,
  CONSTRAINT usersroles_pkey PRIMARY KEY (usersroles_user_id, usersroles_role_id)
);

CREATE TABLE States
(
  state_id serial PRIMARY KEY,
  state_name character varying NOT NULL,
  state_description text
);

CREATE TABLE Categories
(
  category_id serial PRIMARY KEY,
  category_name character varying NOT NULL,
  category_description text
);

CREATE TABLE Items
(
  item_id serial PRIMARY KEY,
  item_name character varying NOT NULL,
  item_state_id integer NOT NULL references states,
  item_created_date timestamp without time zone DEFAULT now(),
  item_created_user_id integer NOT NULL references Users,
  item_updated_user_id integer references Users
);

CREATE TABLE ItemsCategories
(
  itemscategories_item_id integer NOT NULL references Items,
  itemscategories_category_id integer NOT NULL references Categories,
  CONSTRAINT itemscategories_pkey PRIMARY KEY (itemscategories_item_id, itemscategories_category_id)
);

CREATE TABLE Attachs
(
  attach_id serial PRIMARY KEY ,
  attach_item_id integer NOT NULL references Items,
  attach_file text NOT NULL
);

CREATE TABLE Comments
(
  comment_id serial PRIMARY KEY,
  comment_item_id integer references Items,
  comment_description text NOT NULL
);





