CREATE DATABASE tienda_virtual;
USE tienda_virtual;
-- TABLA DE CATEGORÍAS
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);


-- TABLA DE PRODUCTOS
CREATE TABLE productos (
    categoriasid_categoriaid_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DEid_categoriaid_categoriaCIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT,
    imagen VARCHAR(255),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- TABLA DE CLIENTES
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(150)
);

-- TABLA DE PEDIDOS
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    estado ENUM('Pendiente', 'Pagado', 'Enviado', 'Entregado', 'Cancelado') DEFAULT 'Pendiente',
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

-- TABLA DETALLE DE PEDIDOS
CREATE TABLE detalle_pedidos (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT,
    id_producto INT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    subtotal DECIMAL(10,2),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

INSERT INTO categorias (nombre, descripcion) VALUES
('Computadores', 'Equipos de escritorio para oficina y gaming'),
('Portátiles', 'Laptops de diversas gamas'),
('Accesorios', 'Mouse, teclados, audífonos, cables'),
('Almacenamiento', 'Discos duros y SSDs');

INSERT INTO productos (nombre, descripcion, precio, stock, id_categoria) VALUES
('Computador Gamer Ryzen 7', 'Equipo potente con RTX 4060', 4500000, 5, 1),
('Portátil Lenovo IdeaPad 3', 'Laptop ideal para estudio', 2500000, 8, 2),
('Mouse Logitech G203', 'Mouse gamer RGB', 120000, 15, 3),
('Teclado Redragon K552', 'Teclado mecánico retroiluminado', 180000, 10, 3),
('Disco Duro Seagate 1TB', 'Disco duro externo USB 3.0', 250000, 6, 4);


SHOW TABLES;
SELECT * FROM productos;
DESCRIBE productos;
USE tienda_virtual;
SELECT * FROM productos;
SELECT


