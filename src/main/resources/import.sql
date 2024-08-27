INSERT INTO tb_user (name, email, balance, cell_phone, password) VALUES ('fulano', 'fulano@gmail.com',1112.0, '123456789', 'abcdefg123');
INSERT INTO tb_user (name, email, balance, cell_phone, password) VALUES ('ciclano', 'ciclano@gmail.com',200.0, '987654321', 'defghj123');
INSERT INTO tb_user (name, email, balance, cell_phone, password) VALUES ('beltrano', 'beltrano@gmail.com',200.0, '123459876', 'asdbcs123');

INSERT INTO tb_role (authority) VALUES ('Admin');
INSERT INTO tb_role (authority) VALUES ('Cliente');
INSERT INTO tb_role (authority) VALUES ('Visitante');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1,1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1,3);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2,2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3,3);


INSERT INTO tb_category (name) VALUES ('Aventura');
INSERT INTO tb_category (name) VALUES ('Acao');
INSERT INTO tb_category (name) VALUES ('Biografia');
INSERT INTO tb_category (name) VALUES ('Ficcao');
INSERT INTO tb_category (name) VALUES ('Bruxismo');

INSERT INTO tb_order (date, status, user_id) VALUES ('2020-8-12T15:50:30', 0, 1L);
-- INSERT INTO tb_order (date, status, user_id) VALUES ('2017-4-05T08:15:50', 'Pendente', 1L);
-- INSERT INTO tb_order (date, status, user_id) VALUES ('2022-3-30T22:30:00', 'FE', 1L);


INSERT INTO tb_ebook (name, author, description, price) VALUES ('Senhor dos aneis', 'Tolkien', 'Livro bao', 150.0);
INSERT INTO tb_ebook (name, author, description, price) VALUES ('Pactos e infernos', 'Xuxa', 'Livro ruim', 300.0);
INSERT INTO tb_ebook (name, author, description, price) VALUES ('Santos e silvios', 'Silvio santos', 'Isso conta como livro???', 0.25);


INSERT INTO tb_order_ebook (order_id, ebook_id) VALUES (1L, 1L);
INSERT INTO tb_order_ebook (order_id, ebook_id) VALUES (1L, 2L);
INSERT INTO tb_order_ebook (order_id, ebook_id) VALUES (1L, 3L);


-- INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (1L, 1L);
-- INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (1L, 2L);
-- INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (1L, 3L);
INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (2L, 2L);
INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (2L, 3L);
INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (3L, 3L);
INSERT INTO tb_user_ebook (user_id, ebook_id) VALUES (3L, 2L);

INSERT INTO tb_ebook_category (ebook_id, category_id) VALUES (1L, 1L);
INSERT INTO tb_ebook_category (ebook_id, category_id) VALUES (1L, 2L);
INSERT INTO tb_ebook_category (ebook_id, category_id) VALUES (2L, 2L);
INSERT INTO tb_ebook_category (ebook_id, category_id) VALUES (3L, 2L);
INSERT INTO tb_ebook_category (ebook_id, category_id) VALUES (3L, 3L);

