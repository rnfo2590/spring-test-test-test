INSERT INTO categories(name,creator,updater,ver_num,delete_flg) 
VALUES('本','謙汰','謙汰',0,0);
INSERT INTO categories(name,creator,updater,ver_num,delete_flg) 
VALUES('ゲーム','謙汰','謙汰',0,0);
INSERT INTO categories(name,creator,updater,ver_num,delete_flg) 
VALUES('雑貨','謙汰','謙汰',0,0);
INSERT INTO categories(name,creator,updater,ver_num,delete_flg) 
VALUES('ファッション','謙汰','謙汰',0,0);
INSERT INTO categories(name,creator,updater,ver_num,delete_flg) 
VALUES('電化製品','謙汰','謙汰',0,0);

INSERT INTO customers(name, password, tel, email,address,creator,updater,ver_num,delete_flg)
VALUES('桃太郎','momotaro','07023481003','peach@gmail.com','品川区南品川5-3-12','kurumi','kurumi',0,0);
INSERT INTO customers(name, password, tel, email,address,creator,updater,ver_num,delete_flg)
VALUES('田町','okashi','010987654','jet@gmail.com','渋谷区渋谷2-24-12','香穂','香穂',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 1, 'Javaの基本', 2500,pg_read_binary_file('C:/dragon.png'), '傷や汚れあり', 'Javaの学習用', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 2,  '呪術海鮮', 650,pg_read_binary_file('C:/jyujyu.png'), '新品、未使用', '呪術海鮮最新刊', 1,'香穂','香穂',0,0);
