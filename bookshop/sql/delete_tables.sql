
-- productテーブルの動作確認後の行削除とインクリメントリセット
-- ※テーブルデータ削除
DELETE FROM product;
ALTER TABLE product AUTO_INCREMENT = 1;

-- usersバージョン
DELETE FROM users;
ALTER TABLE users AUTO_INCREMENT = 1;
