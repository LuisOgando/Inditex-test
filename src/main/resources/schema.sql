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

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id INT NOT NULL,
    sequence INT NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS size;
CREATE TABLE size (
    id INT NOT NULL,
    productid INT NOT NULL,
    backsoon BOOLEAN NOT NULL,
    special BOOLEAN NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (productid) REFERENCES product(id)
);

DROP TABLE IF EXISTS stock;
CREATE TABLE stock (
    sizeid INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (sizeid),
    FOREIGN KEY (sizeid) REFERENCES size(id)
);