ALTER TABLE ITEMS RENAME COLUMN item_id TO id;

ALTER TABLE ITEMS RENAME COLUMN name TO name;

ALTER TABLE ITEMS RENAME COLUMN description TO description;

ALTER TABLE ITEMS RENAME COLUMN item_created TO created;

ALTER TABLE comments RENAME COLUMN comment_id TO id;

ALTER TABLE comments RENAME COLUMN comment_text TO text;

ALTER TABLE comments RENAME COLUMN comment_item_id TO item_id;

ALTER table  COMMENTS
    DROP CONSTRAINT comments_comment_item_id_fkey,
    ADD CONSTRAINT comments_id_fkey
        FOREIGN KEY (ITEM_ID) REFERENCES ITEMS(id) ON DELETE RESTRICT;