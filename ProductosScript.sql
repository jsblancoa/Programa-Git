CREATE DATABASE PCInnovation;
USE PCInnovation;

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);
-- ======================================
-- TABLA 2: Productos
-- ======================================
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT,
    marca VARCHAR(100),
    imagen_url VARCHAR(255),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(150)
);
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2),
    estado ENUM('Pendiente','Pagado','Enviado','Entregado','Cancelado') DEFAULT 'Pendiente',
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

CREATE TABLE detalle_pedidos (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) GENERATED ALWAYS AS (cantidad * precio_unitario) STORED,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);
INSERT INTO categorias (nombre, descripcion) VALUES
('Computadores', 'Equipos de escritorio para oficina o gaming'),
('Portátiles', 'Laptops de diversas gamas'),
('Accesorios', 'Mouse, teclados, audífonos, cables'),
('Almacenamiento', 'Discos duros, SSD y memorias USB');
INSERT INTO productos (nombre, descripcion, precio, stock, id_categoria, marca, imagen_url) VALUES
('Computador Gamer Ryzen 7', 'PC potente con tarjeta RTX 4060', 4800000, 5, 1, 'ASUS', 'imagenes/pc_ryzen7.jpg'),
('Portátil Lenovo IdeaPad 3', 'Laptop ideal para estudio y oficina', 2500000, 8, 2, 'Lenovo', 'imagenes/ideapad3.jpg'),
('Mouse Logitech G203', 'Mouse gamer RGB con sensor óptico', 120000, 20, 3, 'Logitech', 'imagenes/g203.jpg'),
('Teclado Redragon K552', 'Teclado mecánico retroiluminado', 180000, 15, 3, 'Redragon', 'imagenes/k552.jpg'),
('Disco Duro Seagate 1TB', 'Disco externo USB 3.0', 250000, 10, 4, 'Seagate', 'imagenes/seagate1tb.jpg');
SELECT * FROM categorias;
SELECT * FROM productos;
SHOW TABLES;
DESCRIBE productos;
SELECT * FROM productos;
USE PCINNOVATION
