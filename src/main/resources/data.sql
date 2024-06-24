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

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(5, 2, '家', 10000,pg_read_binary_file('C:/ouchi.png'),'状態が悪い','家です', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(4, 2, 'とあるブランドの紙袋', 300,pg_read_binary_file('C:/celine.png'),'傷や汚れあり','celineの紙袋です。', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 2, '家', 1200,pg_read_binary_file('C:/toilet.png'),'未使用に近い','トイレのスリッパです。デザインが好みではありませんでした。', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 2, 'ドラえもん毎日すごろく', 4200,pg_read_binary_file('C:/doraemon.png'),'未使用に近い','ドラえもんのゲームです。', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 2, '伝記3冊セット', 1980,pg_read_binary_file('C:/denki.png'),'未使用に近い','小学生のおんどく伝記の3冊セットです。<br>1度だけ読みましたが、いらなくなったので出品します。', 1,'梨乃','梨乃',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 1,  'ぐりとぐら', 700,pg_read_binary_file('C:/guri.jpg'), '新品、未使用', '社会人向け絵本', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 1,  'ポケモン初代', 30000,pg_read_binary_file('C:/poke.jfif'), '新品、未使用', 'ゲームボーイ、初代ポケモン赤、緑、青', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  'フシギダネ', 5000,pg_read_binary_file('C:/fusi.png'), '新品、未使用', 'バケモノの子', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(4, 1,  '紳士靴', 9400,pg_read_binary_file('C:/kutu.jpg'), '新品、未使用', 'その辺に落ちてた靴', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(5, 1,  '冷蔵庫', 50000,pg_read_binary_file('C:/rei.jpg'), '新品、未使用', '扉の開かない冷蔵庫', 1,'謙汰','謙汰',0,0);