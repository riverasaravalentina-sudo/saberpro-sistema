-- Script SQL completo con datos de estudiantes y resultados Saber Pro
-- Generado: 2025-11-24

-- Limpiar datos existentes (en orden correcto por las foreign keys)
DELETE FROM resultado;
DELETE FROM alumno;

-- Reiniciar auto_increment
ALTER TABLE resultado AUTO_INCREMENT = 1;
ALTER TABLE alumno AUTO_INCREMENT = 1;

-- Insertar estudiantes con datos completos
INSERT INTO alumno (tipo_documento, numero_documento, primer_apellido, segundo_apellido, primer_nombre, segundo_nombre, correo_electronico, numero_telefonico) VALUES
('CC', '1098765001', 'BARBOSA', 'MARTINEZ', 'CARLOS', 'ANDRES', 'carlos.barbosa@uts.edu.co', '3101234567'),
('CC', '1098765002', 'QUINTERO', 'LOPEZ', 'MARIA', 'FERNANDA', 'maria.quintero@uts.edu.co', '3101234568'),
('CC', '1098765003', 'PARRA', 'RODRIGUEZ', 'JUAN', 'SEBASTIAN', 'juan.parra@uts.edu.co', '3101234569'),
('CC', '1098765004', 'ANAYA', 'GOMEZ', 'LAURA', 'CRISTINA', 'laura.anaya@uts.edu.co', '3101234570'),
('CC', '1098765005', 'FLOR', 'DIAZ', 'MIGUEL', 'ANGEL', 'miguel.flor@uts.edu.co', '3101234571'),
('CC', '1098765006', 'GARCIA', 'HERNANDEZ', 'ANDREA', 'PAOLA', 'andrea.garcia@uts.edu.co', '3101234572'),
('CC', '1098765007', 'MANOSALVA', 'SILVA', 'DAVID', 'ALEJANDRO', 'david.manosalva@uts.edu.co', '3101234573'),
('CC', '1098765008', 'MENDOZA', 'CASTRO', 'DIANA', 'MARCELA', 'diana.mendoza@uts.edu.co', '3101234574'),
('CC', '1098765009', 'BELTRAN', 'RAMIREZ', 'JORGE', 'LUIS', 'jorge.beltran@uts.edu.co', '3101234575'),
('CC', '1098765010', 'SANTAMARIA', 'TORRES', 'CAMILA', 'ANDREA', 'camila.santamaria@uts.edu.co', '3101234576'),
('CC', '1098765011', 'SANCHEZ', 'MORENO', 'OSCAR', 'DANIEL', 'oscar.sanchez@uts.edu.co', '3101234577'),
('CC', '1098765012', 'ROMERO', 'VARGAS', 'NATALIA', 'SOFIA', 'natalia.romero@uts.edu.co', '3101234578'),
('CC', '1098765013', 'LUNA', 'JIMENEZ', 'FELIPE', 'SANTIAGO', 'felipe.luna@uts.edu.co', '3101234579'),
('CC', '1098765014', 'TRIANA', 'PEREZ', 'VALENTINA', 'ISABEL', 'valentina.triana@uts.edu.co', '3101234580'),
('CC', '1098765015', 'SUAREZ', 'ORTIZ', 'ANDRES', 'FELIPE', 'andres.suarez@uts.edu.co', '3101234581'),
('CC', '1098765016', 'GARCIA', 'RUIZ', 'PAOLA', 'ANDREA', 'paola.garcia@uts.edu.co', '3101234582'),
('CC', '1098765017', 'PINZON', 'MEJIA', 'CRISTIAN', 'CAMILO', 'cristian.pinzon@uts.edu.co', '3101234583'),
('CC', '1098765018', 'JAIMES', 'ARIAS', 'ANGELA', 'MARIA', 'angela.jaimes@uts.edu.co', '3101234584'),
('CC', '1098765019', 'NIÑO', 'CARDENAS', 'SANTIAGO', 'ANDRES', 'santiago.nino@uts.edu.co', '3101234585'),
('CC', '1098765020', 'FABIAN', 'MORA', 'JESSICA', 'PAOLA', 'jessica.fabian@uts.edu.co', '3101234586'),
('CC', '1098765021', 'HERNANDEZ', 'SUAREZ', 'LEONARDO', 'JAVIER', 'leonardo.hernandez@uts.edu.co', '3101234587'),
('CC', '1098765022', 'LARIOS', 'MEDINA', 'CATALINA', 'MARIA', 'catalina.larios@uts.edu.co', '3101234588'),
('CC', '1098765023', 'CALDERON', 'GUERRERO', 'DANIEL', 'ALBERTO', 'daniel.calderon@uts.edu.co', '3101234589'),
('CC', '1098765024', 'VILLARREAL', 'CAMPOS', 'JULIANA', 'ANDREA', 'juliana.villarreal@uts.edu.co', '3101234590'),
('CC', '1098765025', 'RESTREPO', 'ARANGO', 'NICOLAS', 'ALEJANDRO', 'nicolas.restrepo@uts.edu.co', '3101234591'),
('CC', '1098765026', 'CACERES', 'VEGA', 'STEFANIA', 'LUCIA', 'stefania.caceres@uts.edu.co', '3101234592'),
('CC', '1098765027', 'TABARES', 'MONTOYA', 'SEBASTIAN', 'FELIPE', 'sebastian.tabares@uts.edu.co', '3101234593'),
('CC', '1098765028', 'NARANJO', 'OSPINA', 'DANIELA', 'CRISTINA', 'daniela.naranjo@uts.edu.co', '3101234594'),
('CC', '1098765029', 'PRADA', 'ROJAS', 'ALEXANDER', 'DAVID', 'alexander.prada@uts.edu.co', '3101234595'),
('CC', '1098765030', 'VARGAS', 'LEON', 'CAROLINA', 'ANDREA', 'carolina.vargas@uts.edu.co', '3101234596'),
('CC', '1098765031', 'TORRES', 'PATIÑO', 'MANUEL', 'ALEJANDRO', 'manuel.torres@uts.edu.co', '3101234597'),
('CC', '1098765032', 'ORTIZ', 'SALAZAR', 'LUISA', 'FERNANDA', 'luisa.ortiz@uts.edu.co', '3101234598'),
('CC', '1098765033', 'VILLAMIZAR', 'CONTRERAS', 'JAIRO', 'ANDRES', 'jairo.villamizar@uts.edu.co', '3101234599'),
('CC', '1098765034', 'RESTREPO', 'MARIN', 'YULIANA', 'PATRICIA', 'yuliana.restrepo@uts.edu.co', '3101234600'),
('CC', '1098765035', 'HIGUERA', 'CASTILLO', 'ROBERTO', 'CARLOS', 'roberto.higuera@uts.edu.co', '3101234601'),
('CC', '1098765036', 'MATIZ', 'FORERO', 'SANDRA', 'MILENA', 'sandra.matiz@uts.edu.co', '3101234602');

-- Insertar resultados Saber Pro (solo para estudiantes que presentaron el examen)
INSERT INTO resultado (alumno_id, numero_registro, puntaje_global, nivel_global, comunicacion_escrita, comunicacion_escrita_nivel, razonamiento_cuantitativo, razonamiento_cuantitativo_nivel, lectura_critica, lectura_critica_nivel, competencias_ciudadanas, competencias_ciudadanas_nivel, ingles, ingles_nivel, formulacion_proyectos, formulacion_proyectos_nivel, pensamiento_cientifico, pensamiento_cientifico_nivel, diseño_software, diseño_software_nivel, nivel_ingles) VALUES
((SELECT id FROM alumno WHERE numero_documento = '1098765001'), 'EK20183007722', 200, 'Nivel 4', 128, 'Nivel 2', 182, 'Nivel 3', 202, 'Nivel 4', 206, 'Nivel 4', 183, 'Nivel 3', 185, 'Nivel 3', 160, 'Nivel 3', 197, 'Nivel 4', 'B1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765002'), 'EK20183140703', 165, 'Nivel 3', 125, 'Nivel 1', 151, 'Nivel 2', 179, 'Nivel 3', 163, 'Nivel 3', 205, 'Nivel 4', 182, 'Nivel 3', 144, 'Nivel 2', 136, 'Nivel 2', 'B2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765003'), 'EK20183040545', 164, 'Nivel 3', 159, 'Nivel 3', 172, 'Nivel 3', 182, 'Nivel 3', 142, 'Nivel 2', 165, 'Nivel 3', 167, 'Nivel 3', 132, 'Nivel 2', 148, 'Nivel 2', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765004'), 'EK20183025381', 160, 'Nivel 3', 146, 'Nivel 2', 199, 'Nivel 4', 157, 'Nivel 3', 149, 'Nivel 2', 147, 'Nivel 2', 174, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765005'), 'EK20183025335', 160, 'Nivel 3', 198, 'Nivel 4', 153, 'Nivel 2', 147, 'Nivel 2', 157, 'Nivel 3', 146, 'Nivel 2', 168, 'Nivel 3', 114, 'Nivel 1', 160, 'Nivel 3', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765006'), 'EK20183122648', 157, 'Nivel 3', 179, 'Nivel 3', 172, 'Nivel 3', 158, 'Nivel 3', 140, 'Nivel 2', 136, 'Nivel 2', 128, 'Nivel 2', 121, 'Nivel 1', 142, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765007'), 'EK20183064605', 153, 'Nivel 2', 115, 'Nivel 1', 152, 'Nivel 2', 159, 'Nivel 3', 172, 'Nivel 3', 165, 'Nivel 3', 142, 'Nivel 2', 118, 'Nivel 1', 119, 'Nivel 1', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765008'), 'EK20183187351', 151, 'Nivel 2', 132, 'Nivel 2', 123, 'Nivel 1', 125, 'Nivel 1', 169, 'Nivel 3', 204, 'Nivel 4', 173, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'B2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765009'), 'EK20183233820', 150, 'Nivel 2', 86, 'Nivel 1', 187, 'Nivel 3', 160, 'Nivel 3', 171, 'Nivel 3', 148, 'Nivel 2', 162, 'Nivel 3', 125, 'Nivel 1', 142, 'Nivel 2', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765010'), 'EK20183030016', 150, 'Nivel 2', 175, 'Nivel 3', 149, 'Nivel 2', 145, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 162, 'Nivel 3', 76, 'Nivel 1', 125, 'Nivel 1', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765011'), 'EK20183047073', 149, 'Nivel 2', 209, 'Nivel 4', 143, 'Nivel 2', 117, 'Nivel 1', 129, 'Nivel 2', 147, 'Nivel 2', 137, 'Nivel 2', 125, 'Nivel 1', 136, 'Nivel 2', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765012'), 'EK20183236451', 146, 'Nivel 2', 93, 'Nivel 1', 183, 'Nivel 3', 155, 'Nivel 2', 164, 'Nivel 3', 133, 'Nivel 2', 174, 'Nivel 3', 130, 'Nivel 2', 154, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765013'), 'EK20183041714', 141, 'Nivel 2', 125, 'Nivel 1', 157, 'Nivel 3', 138, 'Nivel 2', 135, 'Nivel 2', 152, 'Nivel 2', 176, 'Nivel 3', 128, 'Nivel 2', 165, 'Nivel 3', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765014'), 'EK20183187801', 141, 'Nivel 2', 150, 'Nivel 2', 136, 'Nivel 2', 145, 'Nivel 2', 150, 'Nivel 2', 126, 'Nivel 2', 148, 'Nivel 2', 129, 'Nivel 2', 131, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765015'), 'EK20183176566', 140, 'Nivel 2', 128, 'Nivel 2', 146, 'Nivel 2', 146, 'Nivel 2', 132, 'Nivel 2', 147, 'Nivel 2', 130, 'Nivel 2', 110, 'Nivel 1', 125, 'Nivel 1', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765016'), 'EK20183204427', 139, 'Nivel 2', 129, 'Nivel 2', 138, 'Nivel 2', 148, 'Nivel 2', 146, 'Nivel 2', 135, 'Nivel 2', 109, 'Nivel 1', 107, 'Nivel 1', 131, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765017'), 'EK20183196280', 138, 'Nivel 2', 153, 'Nivel 2', 123, 'Nivel 1', 127, 'Nivel 2', 147, 'Nivel 2', 140, 'Nivel 2', 145, 'Nivel 2', 143, 'Nivel 2', 160, 'Nivel 3', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765018'), 'EK20183173799', 137, 'Nivel 2', 166, 'Nivel 3', 157, 'Nivel 3', 124, 'Nivel 1', 100, 'Nivel 1', 140, 'Nivel 2', 100, 'Nivel 1', 105, 'Nivel 1', 113, 'Nivel 1', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765019'), 'EK20183009565', 134, 'Nivel 2', 165, 'Nivel 3', 137, 'Nivel 2', 136, 'Nivel 2', 118, 'Nivel 1', 116, 'Nivel 1', 146, 'Nivel 2', 122, 'Nivel 1', 154, 'Nivel 2', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765020'), 'EK20183117756', 133, 'Nivel 2', 139, 'Nivel 2', 93, 'Nivel 1', 168, 'Nivel 3', 150, 'Nivel 2', 114, 'Nivel 1', 102, 'Nivel 1', 123, 'Nivel 1', 94, 'Nivel 1', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765021'), 'EK20183044579', 132, 'Nivel 2', 116, 'Nivel 1', 166, 'Nivel 3', 136, 'Nivel 2', 104, 'Nivel 1', 140, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 154, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765022'), 'EK20183045760', 131, 'Nivel 2', 149, 'Nivel 2', 123, 'Nivel 1', 129, 'Nivel 2', 121, 'Nivel 1', 131, 'Nivel 2', 101, 'Nivel 1', 102, 'Nivel 1', 165, 'Nivel 3', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765023'), 'EK20183034044', 130, 'Nivel 2', 127, 'Nivel 2', 147, 'Nivel 2', 134, 'Nivel 2', 111, 'Nivel 1', 131, 'Nivel 2', 65, 'Nivel 1', 112, 'Nivel 1', 94, 'Nivel 1', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765024'), 'EK20183041521', 129, 'Nivel 2', 96, 'Nivel 1', 162, 'Nivel 3', 114, 'Nivel 1', 131, 'Nivel 2', 144, 'Nivel 2', 122, 'Nivel 1', 112, 'Nivel 1', 131, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765025'), 'EK20183027436', 126, 'Nivel 2', 81, 'Nivel 1', 134, 'Nivel 2', 126, 'Nivel 2', 149, 'Nivel 2', 139, 'Nivel 2', 127, 'Nivel 2', 136, 'Nivel 2', 142, 'Nivel 2', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765026'), 'EK20183031592', 125, 'Nivel 1', 124, 'Nivel 1', 135, 'Nivel 2', 108, 'Nivel 1', 92, 'Nivel 1', 165, 'Nivel 3', 132, 'Nivel 2', 104, 'Nivel 1', 131, 'Nivel 2', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765027'), 'EK20183004153', 124, 'Nivel 1', 131, 'Nivel 2', 131, 'Nivel 2', 107, 'Nivel 1', 88, 'Nivel 1', 162, 'Nivel 3', 136, 'Nivel 2', 112, 'Nivel 1', 148, 'Nivel 2', 'A2'),
((SELECT id FROM alumno WHERE numero_documento = '1098765028'), 'EK20183030783', 122, 'Nivel 1', 166, 'Nivel 3', 113, 'Nivel 1', 113, 'Nivel 1', 112, 'Nivel 1', 106, 'Nivel 1', 135, 'Nivel 2', 117, 'Nivel 1', 119, 'Nivel 1', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765029'), 'EK20183024754', 122, 'Nivel 1', 119, 'Nivel 1', 125, 'Nivel 1', 137, 'Nivel 2', 107, 'Nivel 1', 123, 'Nivel 1', 83, 'Nivel 1', 104, 'Nivel 1', 119, 'Nivel 1', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765030'), 'EK20183186200', 114, 'Nivel 1', 95, 'Nivel 1', 120, 'Nivel 1', 151, 'Nivel 2', 86, 'Nivel 1', 119, 'Nivel 1', 149, 'Nivel 2', 103, 'Nivel 1', 119, 'Nivel 1', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765031'), 'EK20183182410', 113, 'Nivel 1', 109, 'Nivel 1', 105, 'Nivel 1', 104, 'Nivel 1', 103, 'Nivel 1', 142, 'Nivel 2', 102, 'Nivel 1', 135, 'Nivel 2', 80, 'Nivel 1', 'A1'),
((SELECT id FROM alumno WHERE numero_documento = '1098765032'), 'EK20183213735', 107, 'Nivel 1', 128, 'Nivel 2', 81, 'Nivel 1', 107, 'Nivel 1', 102, 'Nivel 1', 119, 'Nivel 1', 130, 'Nivel 2', 111, 'Nivel 1', 125, 'Nivel 1', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765033'), 'EK20183065220', 106, 'Nivel 1', 134, 'Nivel 2', 96, 'Nivel 1', 92, 'Nivel 1', 110, 'Nivel 1', 97, 'Nivel 1', 83, 'Nivel 1', 107, 'Nivel 1', 119, 'Nivel 1', 'A0'),
((SELECT id FROM alumno WHERE numero_documento = '1098765034'), 'EK20183028123', 96, 'Nivel 1', 0, 'Nivel 1', 117, 'Nivel 1', 122, 'Nivel 1', 105, 'Nivel 1', 137, 'Nivel 2', 157, 'Nivel 3', 96, 'Nivel 1', 131, 'Nivel 2', 'A1');

-- Nota: Los estudiantes HIGUERA (1098765035) y MATIZ (1098765036) tienen examen ANULADO, 
-- por lo tanto NO se insertan resultados para ellos (quedan en estado pendiente)

COMMIT;
