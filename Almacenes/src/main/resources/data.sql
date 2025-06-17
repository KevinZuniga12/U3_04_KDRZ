INSERT INTO cede (id, clave, estado, municipio) VALUES
(1, 'C1-01012023-1234', 'Estado1', 'Municipio1'),
(2, 'C2-02022023-5678', 'Estado2', 'Municipio2');

INSERT INTO almacen (id, clave, fecha_registro, precio_venta, tamaño) VALUES
(1, 'C1-A1', '2023-01-01', 100000, 'G'),
(2, 'C1-A2', '2023-01-02', 150000, 'M'),
(3, 'C2-A1', '2023-02-01', 200000, 'P');

INSERT INTO cliente (id, nombre_completo, numero_telefono, correo_electronico) VALUES
(1, 'Cliente Uno', '1234567890', 'clienteuno@example.com'),
(2, 'Cliente Dos', '0987654321', 'clientedos@example.com');