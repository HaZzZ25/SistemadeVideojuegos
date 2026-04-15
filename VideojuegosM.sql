INSERT INTO usuarios (usuario, password) VALUES ('hazel', '12345');

-- 1. Crear la base de datos y usarla
CREATE DATABASE IF NOT EXISTS ventas_videojuegos;
USE ventas_videojuegos;

-- 2. Eliminar las tablas si ya existían (para empezar en limpio)
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS videojuegos;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS consolas;

-- 3. Crear tabla de Usuarios (con el nuevo campo de ROL)
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- 4. Crear tabla de Videojuegos
CREATE TABLE videojuegos (
    id_videojuego INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    genero VARCHAR(100),
    plataforma VARCHAR(100),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

-- 5. Crear tabla de Clientes
CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);

-- 6. Crear tabla de Consolas
CREATE TABLE consolas (
    id_consola INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(200) NOT NULL,
    marca VARCHAR(100),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE ventas (
    id_venta BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    cliente VARCHAR(200) NOT NULL,
    producto VARCHAR(200) NOT NULL,
    cantidad INT NOT NULL,
    total DOUBLE NOT NULL
);

-- 7. Insertar los usuarios de prueba (Administrador y Empleado)
INSERT INTO usuarios (usuario, password, rol) VALUES ('hazel', '12345', 'ADMIN');
INSERT INTO usuarios (usuario, password, rol) VALUES ('nancy', '12345', 'ADMIN');
INSERT INTO usuarios (usuario, password, rol) VALUES ('jonathan', '12345', 'EMPLEADO');

-- 8. (Opcional) Insertar algunos datos de prueba para verlos mas adelante
INSERT INTO videojuegos (nombre, genero, plataforma, precio, stock) VALUES 
('The Last of Us Part II', 'Acción/Aventura', 'PlayStation 4', 899.00, 15),
('Halo Infinite', 'Shooter', 'Xbox Series X', 1199.00, 20),
('Super Mario Odyssey', 'Plataformas', 'Nintendo Switch', 1299.00, 10);

INSERT INTO clientes (nombre, direccion, telefono) VALUES 
('Juan Perez', 'Av. Siempre Viva 123', '555-1234'),
('María Gomez', 'Calle Falsa 456', '555-9876');

INSERT INTO consolas (nombre, marca, precio, stock) VALUES 
('PlayStation 5', 'Sony', 12000.00, 5),
('Nintendo Switch OLED', 'Nintendo', 7500.00, 8);
