
-- productテーブルの動作確認後の行削除とインクリメントリセット
DELETE FROM product;
ALTER TABLE product AUTO_INCREMENT = 1;

