CREATE DATABASE my_db
  WITH OWNER = postgres
       ENCODING = 'WIN1251'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
	   
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

INSERT INTO public.roles (role_id, role_name, role_description) VALUES (1, 'Роль 1', 'Роль номер один');
INSERT INTO public.roles (role_id, role_name, role_description) VALUES (2, 'Роль 2', 'Роль номер два');
INSERT INTO public.roles (role_id, role_name, role_description) VALUES (3, 'Роль 3', 'Роль номер три');
INSERT INTO public.rules (rule_id, rule_name, rule_description) VALUES (1, 'Право 1', 'Право номер один для ролей');
INSERT INTO public.rules (rule_id, rule_name, rule_description) VALUES (2, 'Право 2', 'Право номер два для ролей');
INSERT INTO public.rules (rule_id, rule_name, rule_description) VALUES (3, 'Право 3', 'Право номер три для ролей');
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (1, 3);
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (2, 2);
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (3, 1);
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (2, 1);
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (3, 2);
INSERT INTO public.rolesrules (rolesrules_role_id, rolesrules_rule_id) VALUES (3, 3);
INSERT INTO public.users (user_id, user_login, user_password, user_created_date, user_name) VALUES (1, 'root', 'root', '2018-04-26 12:38:22.972802', 'Администратор');
INSERT INTO public.users (user_id, user_login, user_password, user_created_date, user_name) VALUES (2, 'user1', 'root', '2018-04-26 12:37:43.644718', 'Вячеслав');
INSERT INTO public.users (user_id, user_login, user_password, user_created_date, user_name) VALUES (3, 'manager', 'root', '2018-04-26 12:38:55.362418', 'Менеджер');
INSERT INTO public.usersroles (usersroles_user_id, usersroles_role_id) VALUES (1, 3);
INSERT INTO public.usersroles (usersroles_user_id, usersroles_role_id) VALUES (1, 2);
INSERT INTO public.usersroles (usersroles_user_id, usersroles_role_id) VALUES (1, 1);
INSERT INTO public.states (state_id, state_name, state_description) VALUES (1, 'Новая', 'Состояние заявки номер один');
INSERT INTO public.states (state_id, state_name, state_description) VALUES (2, 'В работе', 'Состояние заявки номер два');
INSERT INTO public.states (state_id, state_name, state_description) VALUES (3, 'Завершена', 'Состояние заявки номер три');
INSERT INTO public.categories (category_id, category_name, category_description) VALUES (1, 'Категория 1', 'Категория номер один');
INSERT INTO public.categories (category_id, category_name, category_description) VALUES (2, 'Категория 2', 'Категория номер два');
INSERT INTO public.categories (category_id, category_name, category_description) VALUES (3, 'Категория 3', 'Категория номер три');
INSERT INTO public.items (item_id, item_name, item_state_id, item_created_date, item_created_user_id, item_updated_user_id) VALUES (1, 'Заявка 1', 1, '2018-04-26 12:36:06.789143', 1, NULL);
INSERT INTO public.items (item_id, item_name, item_state_id, item_created_date, item_created_user_id, item_updated_user_id) VALUES (2, 'Заявка 2', 1, '2018-04-26 12:36:26.220935', 2, NULL);
INSERT INTO public.items (item_id, item_name, item_state_id, item_created_date, item_created_user_id, item_updated_user_id) VALUES (3, 'Заявка 3', 2, '2018-04-26 12:40:25.619541', 3, NULL);
INSERT INTO public.items (item_id, item_name, item_state_id, item_created_date, item_created_user_id, item_updated_user_id) VALUES (4, 'Заявка 4', 3, '2018-04-26 12:57:07.241217', 3, NULL);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (1, 1);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (1, 2);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (1, 3);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (2, 1);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (2, 2);
INSERT INTO public.itemscategories (itemscategories_item_id, itemscategories_category_id) VALUES (3, 1);
INSERT INTO public.attachs (attach_id, attach_item_id, attach_file) VALUES (1, 1, 'Путь к файлу вложения в заявке 1');
INSERT INTO public.attachs (attach_id, attach_item_id, attach_file) VALUES (2, 2, 'Путь к файлу вложения в заявке 2');
INSERT INTO public.attachs (attach_id, attach_item_id, attach_file) VALUES (3, 3, 'Путь к файлу вложения в заявке 3');
INSERT INTO public.attachs (attach_id, attach_item_id, attach_file) VALUES (4, 3, 'Путь к еще одному файлу вложения в заявке 3');
INSERT INTO public.attachs (attach_id, attach_item_id, attach_file) VALUES (5, 2, 'Путь к еще одному файлу вложения в заявке 2');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (1, 1, 'Комментарий 1 к заявке 1');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (3, 2, 'Комментарий 2 к заявке 2');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (4, 3, 'Комментарий 1 к заявке 3');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (5, 3, 'Комментарий 2 к заявке 3');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (6, 3, 'Комментарий 3 к заявке 3');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (7, 2, 'Комментарий 1 к заявке 2');
INSERT INTO public.comments (comment_id, comment_item_id, comment_description) VALUES (2, 3, 'Комментарий 4 к заявке 3');
SELECT pg_catalog.setval('public.attachs_attach_id_seq', 6, true);
SELECT pg_catalog.setval('public.categories_category_id_seq', 4, false);
SELECT pg_catalog.setval('public.comments_comment_id_seq', 8, false);
SELECT pg_catalog.setval('public.items_item_id_seq', 5, false);
SELECT pg_catalog.setval('public.roles_role_id_seq', 4, false);
SELECT pg_catalog.setval('public.rules_rule_id_seq', 4, false);
SELECT pg_catalog.setval('public.states_state_id_seq', 4, false);
SELECT pg_catalog.setval('public.users_user_id_seq', 4, false);







