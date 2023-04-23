-- - product.csv: fichero con los siguientes campos:
-- o id: identificador de producto.
-- o sequence: posición del producto en la parrilla.
-- - size.csv: fichero con los siguientes campos:
-- o id: identificador de talla.
-- o productId: identificador de producto.
-- o backSoon: true si la talla es back soon o false en caso contrario.
-- o special: true si la talla es especial o false en caso contrario.
-- - stock.csv: fichero con los siguientes campos:
-- o sizeId: identificador de talla.
-- o quantity: unidades disponibles en almacén de dicha talla.
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS size;
DROP TABLE IF EXISTS product;

CREATE TABLE product (
    id LONG NOT NULL,
    sequence INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE size (
    id LONG NOT NULL,
    product_id INT NOT NULL,
    back_soon BOOLEAN NOT NULL,
    special BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE stock (
    size_id LONG NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (size_id),
    FOREIGN KEY (size_id) REFERENCES size(id)
);