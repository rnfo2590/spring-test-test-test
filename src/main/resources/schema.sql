-- 各種テーブル削除
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_details CASCADE;

-- カテゴリーテーブル
CREATE TABLE categories
(
   id SERIAL PRIMARY KEY,
   name VARCHAR(50),
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
   
);
-- 顧客テーブル
CREATE TABLE customers
(
   id SERIAL PRIMARY KEY,
   name VARCHAR(20),
   address TEXT,
   tel VARCHAR(15),
   email VARCHAR(254),
   password VARCHAR(12), /* パスワード追加 */
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
);
-- 商品テーブル
CREATE TABLE items
(
   id SERIAL PRIMARY KEY,
   category_id INTEGER REFERENCES categories (id),
   customer_id INTEGER REFERENCES customers (id),
   name VARCHAR(200),
   price INTEGER,
   image BYTEA,
   condition TEXT,
   detail TEXT,
   stock INTEGER,
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
);
-- 住所テーブル
CREATE TABLE address
(
   id SERIAL PRIMARY KEY,
   customer_id INTEGER REFERENCES customers(id),
   add_address TEXT,
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
);
-- 注文テーブル
CREATE TABLE orders
(
   id SERIAL PRIMARY KEY,
   customer_id INTEGER REFERENCES customers(id),
   ordered_on DATE DEFAULT now(),
   total_price INTEGER,
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
   
);
-- 注文明細テーブル
CREATE TABLE order_details
(
   id SERIAL PRIMARY KEY,
   order_id INTEGER REFERENCES orders(id),
   item_id INTEGER REFERENCES items(id),
   creator TEXT,
   date_created DATE DEFAULT now(),
   updater TEXT,
   date_updata DATE DEFAULT now(),
   ver_num INTEGER,
   delete_flg INTEGER
);