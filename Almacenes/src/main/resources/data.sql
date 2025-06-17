-- Tabla cede
CREATE TABLE cede (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      clave VARCHAR(255) NOT NULL,
                      estado VARCHAR(255) NOT NULL,
                      municipio VARCHAR(255) NOT NULL
);

-- Tabla almacenes
CREATE TABLE almacenes (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           clave VARCHAR(255) NOT NULL UNIQUE,
                           fecha_registro DATE NOT NULL,
                           precio_venta DOUBLE NOT NULL,
                           precio_renta DOUBLE NOT NULL,
                           tamaño VARCHAR(5) NOT NULL
);

-- Tabla cliente
CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre_completo VARCHAR(255) NOT NULL,
                         numero_telefono VARCHAR(20),
                         correo_electronico VARCHAR(255)
);

-- Inserts
INSERT INTO cede (id, clave, estado, municipio) VALUES
                                                    (1, 'C1-01012023-1234', 'Estado1', 'Municipio1'),
                                                    (2, 'C2-02022023-5678', 'Estado2', 'Municipio2');

INSERT INTO almacenes (id, clave, fecha_registro, precio_venta, precio_renta, tamaño) VALUES
                                                                                          (1, 'C1-A1', '2023-01-01', 100000, 5000, 'G'),
                                                                                          (2, 'C1-A2', '2023-01-02', 150000, 7000, 'M'),
                                                                                          (3, 'C2-A1', '2023-02-01', 200000, 8000, 'P');

INSERT INTO cliente (id, nombre_completo, numero_telefono, correo_electronico) VALUES
                                                                                   (1, 'Admin', 'admin123', 'clienteuno@example.com'),
                                                                                   (2, 'Cliente Dos', '0987654321', 'clientedos@example.com');
