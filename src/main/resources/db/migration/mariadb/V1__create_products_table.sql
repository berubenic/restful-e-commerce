CREATE TABLE products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  price DOUBLE NOT NULL,
  stock_quantity INT NOT NULL,
  image_path VARCHAR(255)
);