

-- Inserts
INSERT INTO cedes (id, clave, estado, municipio) VALUES
                                                    (1, 'C1-01012023-1234', 'Estado1', 'Municipio1'),
                                                    (2, 'C2-02022023-5678', 'Estado2', 'Municipio2');

INSERT INTO almacenes (id, clave, fecha_registro, precio_venta, precio_renta, tamano) VALUES
                                                                                          (1, 'C1-A1', '2023-01-01', 100000, 5000, 'G'),
                                                                                          (2, 'C1-A2', '2023-01-02', 150000, 7000, 'M'),
                                                                                          (3, 'C2-A1', '2023-02-01', 200000, 8000, 'P');

INSERT INTO clientes (id, nombre_completo, numero_telefono, correo_electronico) VALUES
                                                                                   (1, 'Admin', 'admin123', 'clienteuno@example.com'),
                                                                                   (2, 'Cliente Dos', '0987654321', 'clientedos@example.com');
