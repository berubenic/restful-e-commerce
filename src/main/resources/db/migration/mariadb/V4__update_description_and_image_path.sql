ALTER TABLE categories
    MODIFY `description` VARCHAR(255);

ALTER TABLE products
    MODIFY `description` VARCHAR(255) NOT NULL;

ALTER TABLE products
    MODIFY image_path VARCHAR(255) NOT NULL;