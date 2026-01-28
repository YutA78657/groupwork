CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  pass VARCHAR(255) NOT NULL,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(255),
  admin_flg TINYINT(1) NOT NULL DEFAULT 0
);

CREATE TABLE category(category_name) (
  id INT AUTO_INCREMENT PRIMARY KEY,
  category(category_name)_name VARCHAR(100) NOT NULL
);

CREATE TABLE series (
  id INT AUTO_INCREMENT PRIMARY KEY,
  series_name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  stock INT NOT NULL,
  author VARCHAR(100),
  description TEXT,
  category(category_name)_id INT,
  series_id INT,
  publisher VARCHAR(100),
  recommend_flg TINYINT DEFAULT 0,
  img VARCHAR(100),
  FOREIGN KEY (category(category_name)_id) REFERENCES category(category_name)(id),
  FOREIGN KEY (series_id) REFERENCES series(id)
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status VARCHAR(20) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  price INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);