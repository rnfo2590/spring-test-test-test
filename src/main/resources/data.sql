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
VALUES(3, 2, 'トイレのスリッパ', 1200,pg_read_binary_file('C:/toilet.png'),'未使用に近い','トイレのスリッパです。デザインが好みではありませんでした。', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 2, 'ドラえもん毎日すごろく', 4200,pg_read_binary_file('C:/doraemon.png'),'未使用に近い','ドラえもんのゲームです。', 1,'梨乃','梨乃',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 2, '伝記3冊セット', 1980,pg_read_binary_file('C:/denki.png'),'未使用に近い','小学生のおんどく伝記の3冊セットです。<br>1度だけ読みましたが、いらなくなったので出品します。', 1,'梨乃','梨乃',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 1,  'ぐりとぐら', 700,pg_read_binary_file('C:/guri.jpg'), '新品、未使用', '社会人向け絵本', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 1,  'ポケモン初代', 30000,pg_read_binary_file('C:/poke.jfif'), '新品、未使用', 'ゲームボーイ、初代ポケモン赤、緑、青', 1,'謙汰','謙汰',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(4, 1,  '紳士靴', 9400,pg_read_binary_file('C:/kutu.jpg'), '新品、未使用', 'その辺に落ちてた靴', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(5, 1,  '冷蔵庫', 50000,pg_read_binary_file('C:/rei.jpg'), '新品、未使用', '扉の開かない冷蔵庫', 1,'謙汰','謙汰',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 1,  '人間失格/太宰治', 150,pg_read_binary_file('C:/ningen.jpg'), '新品、未使用', 'かっこつけて買いましたが、読みませんでした。', 1,'久瑠美','久瑠美',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 1,  'NieR:Automata', 2300,pg_read_binary_file('C:/nier.avif'), '未使用に近い', 'RPGは向いてなかったので出品します。', 1,'久瑠美','久瑠美',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  'カチューシャ（アリエルVer.）', 800,pg_read_binary_file('C:/Disney.jpg'), '傷や汚れあり', '使用は一度だけですが、めっちゃ汗かいた日に使ったので傷や汚れありにしています。', 1,'久瑠美','久瑠美',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(4, 1,  'Travis×Fragment×NIKE AJ1', 129800,pg_read_binary_file('C:/AJ1.jpg'), '未使用に近い', '24.5cmです。シューグーとインソール入れて使っていたので美品です。付属品そろっています。<br>スニダンでは安くても15万くらいするので、お買い得だと思います。', 1,'久瑠美','久瑠美',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(5, 1,  '女子大生の使用済みPC', 6666,pg_read_binary_file('C:/PC.png'), '状態が悪い', '大学4年間使用した思い出の品です。<br>ステッカー大量に貼ってあります。多分使えます。', 1,'久瑠美','久瑠美',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(1, 1,  'ハイキューセカンドシーズンDVD', 15000,pg_read_binary_file('C:/haikyu.png'), '新品、未使用', '泣けます。もう一度青春を味わいたい人におすすめです。', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(2, 1,  'マリオパーティ DS', 300,pg_read_binary_file('C:/mario.jpg'), '傷や汚れあり', 'DS用です。傷や汚れがありますので、神経質な方はお控えください。', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  '呪術廻戦　ならぶんです　コンプ品', 5000,pg_read_binary_file('C:/jyu_jyu.jpg'), '新品、未使用', 'コンプしてます。', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(4, 1,  'カプシーヌ 2024 SS', 9999999,pg_read_binary_file('C:/bag.jfif'), '未使用に近い', '頂き物です。家で保管していましたが、使用する目処がないので出品しました。', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  '♡様 専用', 10000,pg_read_binary_file('C:/Image.jfif'), '新品、未使用', '♡様以外のご購入はお控えください', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(5, 1,  '超音波トリートメント', 10000,pg_read_binary_file('C:/yaman.jpg'), '未使用に近い', '同期からおすすめしてもらいましたが、自分には合わなかったので出品しました。同期曰く髪質改善するみたいです。', 1,'香穂','香穂',0,0);

INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  'ミュウ', 1510000,pg_read_binary_file('C:/151.png'), '新品、未使用', '分類：しんしゅポケモン<br>タイプ：エスパータイプ<br>高さ：0.4m　重さ：4.0kg<br>特性：シンクロ<br>あらゆる技を使うためポケモンの先祖と考える学者がたくさんいる', 1,'謙汰','謙汰',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 2, 'ヒトカゲ', 3200,pg_read_binary_file('C:/kage.png'),'新品、未使用','野球部', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 2, 'ゼニガメ', 7000,pg_read_binary_file('C:/zeni.png'),'新品、未使用','一番かわいいです', 1,'香穂','香穂',0,0);
INSERT INTO items(category_id, customer_id, name, price, image, condition, detail, stock, creator,updater,ver_num,delete_flg) 
VALUES(3, 1,  'フシギダネ', 5000,pg_read_binary_file('C:/fusi.png'), '新品、未使用', 'バケモノの子', 1,'謙汰','謙汰',0,0);

