-- ============================================================
-- INSERTS PARA: gestion_paquetes
-- Ciudad: Loja, Ecuador
-- Generado: 2026-05-29
-- Contraseña personal (hash bcrypt de 'abc'): $2b$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p392ap2uVK5.HLaBCGOzve
-- Contraseña clientes (hash bcrypt de '000'): $2b$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW
-- ============================================================


-- ============================================================
-- 1. UBICACION (sin dependencias)
-- ============================================================
INSERT INTO `ubicacion` (`idLocal`, `nombre`, `direccion`, `ciudad`, `tipo`) VALUES
(1, 'Bodega Central Loja',       'Av. Universitaria y Av. Cuxibamba',       'Loja', 'Bodega'),
(2, 'Bodega Norte Loja',         'Av. Emiliano Ortega y Calle Paris',        'Loja', 'Bodega'),
(3, 'Local Centro Loja',         'Calle Bolívar 10-45 y Sucre',              'Loja', 'Local'),
(4, 'Local El Valle',            'Av. Pío Jaramillo Alvarado 2-30',          'Loja', 'Local'),
(5, 'Local Argelia',             'Av. Eduardo Kingman 5-67',                 'Loja', 'Local'),
(6, 'Punto de Retiro Malacatos', 'Calle Eloy Alfaro 3-12, Malacatos',        'Malacatos', 'Punto de Retiro'),
(7, 'Punto de Retiro Vilcabamba','Calle Diego Vaca de Vega s/n, Vilcabamba', 'Vilcabamba', 'Punto de Retiro'),
(8, 'Punto de Retiro Catamayo',  'Av. Juan de Salinas 1-22, Catamayo',       'Catamayo', 'Punto de Retiro'),
(9, 'Punto de Retiro Catacocha', 'Calle 24 de Mayo 4-15, Catacocha',         'Catacocha', 'Punto de Retiro'),
(10, 'Destino', ' -',         '-', 'Destino');


-- ============================================================
-- 2. PERSONA (sin dependencias)
-- Passwords:
--   Personal (abc):     $2b$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p392ap2uVK5.HLaBCGOzve
--   Clientes (000):     $2b$10$EixZaYVK1fsbw1ZfbX3OXePaWxn96p36WQoeG6Lruj3vjPGga31lW
-- ============================================================
INSERT INTO `persona` (`cedula`, `nombre`, `apellido`, `numero`, `password`, `email`) VALUES
-- Supervisores
('1104001001', 'Carlos',   'Andrade',   '0991234501', 'abc', 'c.andrade@correorapido.ec'),
('1104001002', 'Patricia', 'Montaño',   '0992234502', 'abc', 'p.montano@correorapido.ec'),
-- Recepcionistas
('1104001003', 'Gabriela', 'Loaiza',    '0993234503', 'abc', 'g.loaiza@correorapido.ec'),
('1104001004', 'Jorge',    'Valdivieso','0994234504', 'abc', 'j.valdivieso@correorapido.ec'),
('1104001005', 'Mariana',  'Cueva',     '0995234505', 'abc', 'm.cueva@correorapido.ec'),
('1104001006', 'Luis',     'Soto',      '0996234506', 'abc', 'l.soto@correorapido.ec'),
-- Operadores de despacho
('1104001007', 'Roberto',  'Jaramillo', '0997234507', 'abc', 'r.jaramillo@correorapido.ec'),
('1104001008', 'Silvia',   'Eras',      '0998234508', 'abc', 's.eras@correorapido.ec'),
-- Repartidores
('1104001009', 'Andrés',   'Carrión',   '0999234509', 'abc', 'a.carrion@correorapido.ec'),
('1104001010', 'Diana',    'Palacios',  '0990234510', 'abc', 'd.palacios@correorapido.ec'),
('1104001011', 'Miguel',   'Cabrera',   '0991234511', 'abc', 'm.cabrera@correorapido.ec'),
-- Clientes
('1104002001', 'Fernanda', 'Aguirre',   '0992234512', '000', 'fernanda.aguirre@gmail.com'),
('1104002002', 'Ricardo',  'Mora',      '0993234513', '000', 'ricardo.mora@gmail.com'),
('1104002003', 'Verónica', 'Ordóñez',   '0994234514', '000', 'veronica.ordonez@gmail.com'),
('1104002004', 'Santiago', 'Quezada',   '0995234515', '000', 'santiago.quezada@gmail.com'),
('1104002005', 'Isabel',   'Tandazo',   '0996234516', '000', 'isabel.tandazo@gmail.com'),
('1104002006', 'Pablo',    'Villalta',  '0997234517', '000', 'pablo.villalta@gmail.com'),
('1104002007', 'Lorena',   'Espinoza',  '0998234518', '000', 'lorena.espinoza@gmail.com'),
('1104002008', 'Esteban',  'Feijoo',    '0999234519', '000', 'esteban.feijoo@gmail.com'),
('1104002009', 'Carmen',   'Hinojosa',  '0990234520', '000', 'carmen.hinojosa@gmail.com'),
('1104002010', 'Marco',    'Iñiguez',   '0991234521', '000', 'marco.iniguez@gmail.com');


-- ============================================================
-- 3. SUPERVISOR
-- ============================================================
INSERT INTO `supervisor` (`idSupervisor`, `cedula`) VALUES
(1, '1104001001'),
(2, '1104001002');


-- ============================================================
-- 4. RECEPCIONISTA
-- ============================================================
INSERT INTO `recepcionista` (`idRecepcionista`, `turno`, `cedula`, `local`) VALUES
(1, 'Mañana', '1104001003', 3),
(2, 'Tarde',  '1104001004', 3),
(3, 'Mañana', '1104001005', 4),
(4, 'Tarde',  '1104001006', 5);


-- ============================================================
-- 5. OPERADOR_DESPACHO
-- ============================================================
INSERT INTO `operador_despacho` (`idOperador`, `cedula`, `bodega`) VALUES
(1, '1104001007', 1),
(2, '1104001008', 2);


-- ============================================================
-- 6. REPARTIDOR
-- ============================================================
INSERT INTO `repartidor` (`idRepartidor`, `placa`, `vehiculo`, `estado`, `cedula`) VALUES
(1, 'LJA-1234', 'Moto Yamaha 150', 'Activo',   '1104001009'),
(2, 'LJA-5678', 'Moto Honda 125',  'Activo',   '1104001010'),
(3, 'LJA-9012', 'Camioneta Chevrolet D-Max', 'Inactivo', '1104001011');


-- ============================================================
-- 7. TARIFA
-- ============================================================
INSERT INTO `tarifa` (`idTarifa`, `descripcion`, `precio_base`, `kg_extra`) VALUES
(1, 'Tarifa Estándar Local',       3.50,  0.50),
(2, 'Tarifa Exprés Local',         5.00,  0.75),
(3, 'Tarifa Estándar Interprovincial', 7.00, 1.00),
(4, 'Tarifa Exprés Interprovincial',  10.00, 1.50),
(5, 'Tarifa Documentos',           2.50,  0.00);


-- ============================================================
-- 8. CLIENTE
-- ============================================================
INSERT INTO `cliente` (`idCliente`, `direccion`, `ciudad`, `cedula`, `idRecepcionista`) VALUES
(1,  'Av. Universitaria 14-32',           'Loja', '1104002001', 1),
(2,  'Calle Sucre 08-22 y Bolívar',       'Loja', '1104002002', 1),
(3,  'Av. Pío Jaramillo 3-45',            'Loja', '1104002003', 2),
(4,  'Av. Emiliano Ortega 6-12',          'Loja', '1104002004', 2),
(5,  'Calle Paris 2-67, El Valle',        'Loja', '1104002005', 3),
(6,  'Av. Eduardo Kingman 9-14',          'Loja', '1104002006', 3),
(7,  'Calle 18 de Noviembre 5-40',        'Loja', '1104002007', 1),
(8,  'Av. Iberoamérica 1-20, Argelia',    'Loja', '1104002008', 4),
(9,  'Urbanización Los Ceibos, Casa 7',   'Loja', '1104002009', 4),
(10, 'Calle Lourdes 3-78 y Colón',        'Loja', '1104002010', 2);


-- ============================================================
-- 9. PAQUETE
-- Código de seguimiento: LOH-2026-0001, LOH-2026-0002, ...
-- ============================================================
INSERT INTO `paquete` (
    `codigo_unico`, `peso`, `tipo_envio`, `estado`,
    `ciudad_envio`, `direccion_entrega`, `ciudad_destino`,
    `nro_seguimiento`, `fecha_hora`,
    `idRecepcionista`, `idCliente`, `idTarifa`,
    `destinatarioNomb`, `destinatarioTel`
) VALUES
(1,  1.50, 'Paquete pequeño', 'Entregado',    'Loja', 'Calle Bolívar 10-45',         'Loja',      'LOH-2026-0001', '2026-04-01 08:30:00', 1, 1,  1, 'Pedro Ramírez',    '0991100001'),
(2,  3.20, 'Paquete mediano', 'Entregado',    'Loja', 'Av. Universitaria 5-10',     'Loja',      'LOH-2026-0002', '2026-04-02 09:00:00', 1, 2,  1, 'Ana Gutiérrez',    '0991100002'),
(3,  0.30, 'Documentos',      'Entregado',    'Loja', 'Calle Sucre 3-20',            'Loja',      'LOH-2026-0003', '2026-04-05 10:15:00', 2, 3,  5, 'Luis Torres',      '0991100003'),
(4,  7.80, 'Paquete grande',  'Entregado',    'Loja', 'Av. Colón 2-45',              'Cuenca',    'LOH-2026-0004', '2026-04-07 08:00:00', 2, 4,  3, 'Rosa Pacheco',     '0991100004'),
(5,  2.10, 'Paquete pequeño', 'Entregado',    'Loja', 'Calle Orellana 6-12',         'Machala',   'LOH-2026-0005', '2026-04-10 11:00:00', 3, 5,  3, 'José Celi',        '0991100005'),
(6,  5.00, 'Paquete mediano', 'En Transito',  'Loja', 'Av. Huayna Cápac 7-34',       'Quito',     'LOH-2026-0006', '2026-05-15 09:30:00', 3, 6,  3, 'Marta Sánchez',    '0991100006'),
(7,  1.00, 'Documentos',      'En Transito',  'Loja', 'Calle Rocafuerte 4-22',       'Guayaquil', 'LOH-2026-0007', '2026-05-18 10:00:00', 4, 7,  5, 'Tomás Ávila',      '0991100007'),
(8,  9.50, 'Paquete grande',  'En Transito',  'Loja', 'Av. de las Américas 3-56',    'Ambato',    'LOH-2026-0008', '2026-05-20 08:45:00', 4, 8,  3, 'Sofía Bravo',      '0991100008'),
(9,  0.80, 'Paquete pequeño', 'Receptado',    'Loja', 'Calle Lourdes 2-15',          'Loja',      'LOH-2026-0009', '2026-05-28 14:00:00', 1, 9,  1, 'Hugo Medina',      '0991100009'),
(10, 4.60, 'Paquete mediano', 'Receptado',    'Loja', 'Av. Iberoamérica 8-90',       'Cuenca',    'LOH-2026-0010', '2026-05-29 09:15:00', 2, 10, 3, 'Natalia Castillo', '0991100010'),
(11, 2.30, 'Paquete pequeño', 'Receptado',    'Loja', 'Calle José A. Eguiguren 5-3', 'Zamora',    'LOH-2026-0011', '2026-05-29 11:30:00', 3, 1,  2, 'Víctor Peña',      '0991100011');


-- ============================================================
-- 10. HISTORIAL_ESTADO
-- ============================================================
INSERT INTO `historial_estado` (`idHistorial`, `observaciones`, `estado`, `fecha_hora`, `codigo_unico`, `ubicacion`) VALUES
-- Paquete 1 (Entregado)
(1,  'Paquete recibido en local Centro',      'Receptado',   '2026-04-01 08:30:00', 1, 3),
(2,  'Salió de bodega hacia repartidor',      'En Transito', '2026-04-01 14:00:00', 1, 1),
(3,  'Entregado al destinatario sin novedad', 'Entregado',   '2026-04-01 17:30:00', 1, 3),
-- Paquete 2 (Entregado)
(4,  'Paquete recibido en local Centro',      'Receptado',   '2026-04-02 09:00:00', 2, 3),
(5,  'En ruta de entrega',                    'En Transito', '2026-04-02 13:30:00', 2, 1),
(6,  'Entregado al destinatario sin novedad', 'Entregado',   '2026-04-02 16:45:00', 2, 3),
-- Paquete 3 (Entregado)
(7,  'Documentos recibidos en local Centro',  'Receptado',   '2026-04-05 10:15:00', 3, 3),
(8,  'En ruta de entrega',                    'En Transito', '2026-04-05 12:00:00', 3, 1),
(9,  'Entregado al destinatario',             'Entregado',   '2026-04-05 14:30:00', 3, 3),
-- Paquete 4 (Entregado)
(10, 'Paquete recibido en local Centro',      'Receptado',   '2026-04-07 08:00:00', 4, 3),
(11, 'Trasladado a bodega central',           'En Transito', '2026-04-07 10:00:00', 4, 1),
(12, 'Entregado en Cuenca sin novedad',       'Entregado',   '2026-04-08 16:00:00', 4, 1),
-- Paquete 5 (Entregado)
(13, 'Paquete recibido en local El Valle',    'Receptado',   '2026-04-10 11:00:00', 5, 4),
(14, 'En tránsito a Machala',                 'En Transito', '2026-04-10 14:00:00', 5, 1),
(15, 'Entregado en Machala',                  'Entregado',   '2026-04-11 15:00:00', 5, 1),
-- Paquete 6 (En Transito)
(16, 'Paquete recibido en local Argelia',     'Receptado',   '2026-05-15 09:30:00', 6, 5),
(17, 'Despachado desde bodega hacia Quito',   'En Transito', '2026-05-15 15:00:00', 6, 1),
-- Paquete 7 (En Transito)
(18, 'Documentos recibidos en local Centro',  'Receptado',   '2026-05-18 10:00:00', 7, 3),
(19, 'En tránsito hacia Guayaquil',           'En Transito', '2026-05-18 16:00:00', 7, 1),
-- Paquete 8 (En Transito)
(20, 'Paquete recibido en local Centro',      'Receptado',   '2026-05-20 08:45:00', 8, 3),
(21, 'Despachado desde bodega norte',         'En Transito', '2026-05-20 13:00:00', 8, 2),
-- Paquetes 9, 10, 11 (Receptado — solo estado inicial)
(22, 'Paquete recibido, pendiente despacho',  'Receptado',   '2026-05-28 14:00:00', 9, 3),
(23, 'Paquete recibido, pendiente despacho',  'Receptado',   '2026-05-29 09:15:00', 10, 3);


-- ============================================================
-- 11. FACTURA (solo para paquetes Entregados: 1-5)
-- ============================================================
INSERT INTO `factura` (`idFactura`, `nro_factura`, `fecha`, `subtotal`, `iva`, `total`, `codigo_unico`) VALUES
(1,  'FAC-001-000001', '2026-04-01 17:30:00',  3.50,  0.42,  3.92,  1),
(2,  'FAC-001-000002', '2026-04-02 16:45:00',  5.10,  0.61,  5.71,  2),
(3,  'FAC-001-000003', '2026-04-05 14:30:00',  2.50,  0.30,  2.80,  3),
(4,  'FAC-001-000004', '2026-04-08 16:00:00', 14.80,  1.78, 16.58,  4),
(5,  'FAC-001-000005', '2026-04-11 15:00:00',  9.10,  1.09, 10.19,  5),
-- Facturas anticipadas para paquetes en tránsito
(6,  'FAC-001-000006', '2026-05-15 09:30:00',  9.00,  1.08, 10.08,  6),
(7,  'FAC-001-000007', '2026-05-18 10:00:00',  2.50,  0.30,  2.80,  7),
(8,  'FAC-001-000008', '2026-05-20 08:45:00', 16.50,  1.98, 18.48,  8),
(9,  'FAC-001-000009', '2026-05-28 14:00:00',  3.50,  0.42,  3.92,  9),
(10, 'FAC-001-000010', '2026-05-29 09:15:00', 11.60,  1.39, 12.99, 10);


-- ============================================================
-- 12. DESPACHA (paquetes que salieron de bodega: 1-8)
-- ============================================================
INSERT INTO `despacha` (`fecha_hora`, `codigo_unico`, `idOperador`) VALUES
('2026-04-01 14:00:00', 1, 1),
('2026-04-02 13:30:00', 2, 1),
('2026-04-05 12:00:00', 3, 2),
('2026-04-07 10:00:00', 4, 1),
('2026-04-10 14:00:00', 5, 2),
('2026-05-15 15:00:00', 6, 1),
('2026-05-18 16:00:00', 7, 2),
('2026-05-20 13:00:00', 8, 1);


-- ============================================================
-- 13. ASIGNA_PAQUETE (paquetes asignados a repartidores)
-- ============================================================
INSERT INTO `asigna_paquete` (`paquete`, `repartidor`, `estado`, `fechaAsignada`) VALUES
(1, 1, 'Entregado',   '2026-04-01 14:00:00'),
(2, 2, 'Entregado',   '2026-04-02 13:30:00'),
(3, 1, 'Entregado',   '2026-04-05 12:00:00'),
(4, 1, 'Entregado',   '2026-04-07 10:00:00'),
(5, 2, 'Entregado',   '2026-04-10 14:00:00'),
(6, 2, 'En Transito', '2026-05-15 15:00:00'),
(7, 1, 'En Transito', '2026-05-18 16:00:00'),
(8, 1, 'En Transito', '2026-05-20 13:00:00');


-- ============================================================
-- 14. ENTREGA (solo paquetes efectivamente entregados: 1-5)
-- ============================================================
INSERT INTO `entrega` (`nombre_rec`, `observaciones`, `fecha_hora`, `codigo_unico`, `idRepartidor`) VALUES
('Pedro Ramírez',    'Recibido en buenas condiciones',  '2026-04-01 17:30:00', 1, 1),
('Ana Gutiérrez',    'Recibido en buenas condiciones',  '2026-04-02 16:45:00', 2, 2),
('Luis Torres',      'Firma del destinatario',          '2026-04-05 14:30:00', 3, 1),
('Rosa Pacheco',     'Entrega en domicilio Cuenca',     '2026-04-08 16:00:00', 4, 1),
('José Celi',        'Entrega en domicilio Machala',    '2026-04-11 15:00:00', 5, 2);