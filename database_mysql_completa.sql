-- ===============================
-- BASE DE DATOS COMPLETA SABER PRO
-- Sistema de Información SABER PRO - UTS
-- ===============================

DROP DATABASE IF EXISTS saberpro_db;
CREATE DATABASE saberpro_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE saberpro_db;

-- ===============================
-- CREACIÓN DE TABLAS
-- ===============================

CREATE TABLE alumnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(10) DEFAULT 'CC',
    documento VARCHAR(20) NOT NULL UNIQUE,
    primer_apellido VARCHAR(100),
    segundo_apellido VARCHAR(100),
    primer_nombre VARCHAR(100),
    segundo_nombre VARCHAR(100),
    nombres VARCHAR(200),
    apellidos VARCHAR(200),
    correo VARCHAR(100),
    telefono VARCHAR(20),
    programa VARCHAR(200) DEFAULT 'Ingeniería de Sistemas',
    numero_registro VARCHAR(50) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE resultados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alumno_id BIGINT NOT NULL,
    puntaje_global INT,
    puntaje_nivel VARCHAR(20),
    comunicacion_escrita INT,
    comunicacion_escrita_nivel VARCHAR(20),
    razonamiento_cuantitativo INT,
    razonamiento_cuantitativo_nivel VARCHAR(20),
    lectura_critica INT,
    lectura_critica_nivel VARCHAR(20),
    competencias_ciudadanas INT,
    competencias_ciudadanas_nivel VARCHAR(20),
    ingles INT,
    ingles_nivel VARCHAR(20),
    formulacion_proyectos INT,
    formulacion_proyectos_nivel VARCHAR(20),
    pensamiento_cientifico INT,
    pensamiento_cientifico_nivel VARCHAR(20),
    diseno_software INT,
    diseno_software_nivel VARCHAR(20),
    nivel_ingles VARCHAR(10),
    fecha_presentacion DATE DEFAULT '2024-03-15',
    periodo_academico VARCHAR(20) DEFAULT '2024-1',
    estado VARCHAR(20) DEFAULT 'VALIDO',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (alumno_id) REFERENCES alumnos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ===============================
-- INSERCIÓN DE DATOS REALES
-- ===============================

-- Insertar alumnos
INSERT INTO alumnos (tipo_documento, documento, primer_apellido, segundo_apellido, numero_registro, correo, programa) VALUES
('CC', '1001', 'BARBOSA', '', 'EK20183007722', 'barbosa@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1002', 'QUINTERO', '', 'EK20183140703', 'quintero@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1003', 'PARRA', '', 'EK20183040545', 'parra@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1004', 'ANAYA', '', 'EK20183025381', 'anaya@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1005', 'FLOR', '', 'EK20183025335', 'flor@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1006', 'GARCIA', '', 'EK20183122648', 'garcia1@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1007', 'MANOSALVA', '', 'EK20183064605', 'manosalva@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1008', 'MENDOZA', '', 'EK20183187351', 'mendoza@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1009', 'BELTRAN', '', 'EK20183233820', 'beltran@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1010', 'SANTAMARIA', '', 'EK20183030016', 'santamaria@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1011', 'SANCHEZ', '', 'EK20183047073', 'sanchez@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1012', 'ROMERO', '', 'EK20183236451', 'romero@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1013', 'LUNA', '', 'EK20183041714', 'luna@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1014', 'TRIANA', '', 'EK20183187801', 'triana@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1015', 'SUAREZ', '', 'EK20183176566', 'suarez@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1016', 'GARCIA', '', 'EK20183204427', 'garcia2@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1017', 'PINZON', '', 'EK20183196280', 'pinzon@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1018', 'JAIMES', '', 'EK20183173799', 'jaimes@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1019', 'NIÑO', '', 'EK20183009565', 'nino@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1020', 'FABIAN', '', 'EK20183117756', 'fabian@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1021', 'HERNANDEZ', '', 'EK20183044579', 'hernandez@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1022', 'LARIOS', '', 'EK20183045760', 'larios@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1023', 'CALDERON', '', 'EK20183034044', 'calderon@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1024', 'VILLARREAL', '', 'EK20183041521', 'villarreal@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1025', 'RESTREPO', '', 'EK20183027436', 'restrepo1@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1026', 'CACERES', '', 'EK20183031592', 'caceres@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1027', 'TABARES', '', 'EK20183004153', 'tabares@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1028', 'NARANJO', '', 'EK20183030783', 'naranjo@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1029', 'PRADA', '', 'EK20183024754', 'prada@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1030', 'VARGAS', '', 'EK20183186200', 'vargas@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1031', 'TORRES', '', 'EK20183182410', 'torres@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1032', 'ORTIZ', '', 'EK20183213735', 'ortiz@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1033', 'VILLAMIZAR', '', 'EK20183065220', 'villamizar@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1034', 'RESTREPO', '', 'EK20183028123', 'restrepo2@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1035', 'HIGUERA', '', 'EK20183207870', 'higuera@uts.edu.co', 'Ingeniería de Sistemas'),
('CC', '1036', 'MATIZ', '', 'EK20183144329', 'matiz@uts.edu.co', 'Ingeniería de Sistemas');

-- Insertar resultados SABER PRO
INSERT INTO resultados (alumno_id, puntaje_global, puntaje_nivel, comunicacion_escrita, comunicacion_escrita_nivel, razonamiento_cuantitativo, razonamiento_cuantitativo_nivel, lectura_critica, lectura_critica_nivel, competencias_ciudadanas, competencias_ciudadanas_nivel, ingles, ingles_nivel, formulacion_proyectos, formulacion_proyectos_nivel, pensamiento_cientifico, pensamiento_cientifico_nivel, diseno_software, diseno_software_nivel, nivel_ingles, estado) VALUES
(1, 200, 'Nivel 4', 128, 'Nivel 2', 182, 'Nivel 3', 202, 'Nivel 4', 206, 'Nivel 4', 183, 'Nivel 3', 185, 'Nivel 3', 160, 'Nivel 3', 197, 'Nivel 4', 'B1', 'VALIDO'),
(2, 165, 'Nivel 3', 125, 'Nivel 1', 151, 'Nivel 2', 179, 'Nivel 3', 163, 'Nivel 3', 205, 'Nivel 4', 182, 'Nivel 3', 144, 'Nivel 2', 136, 'Nivel 2', 'B2', 'VALIDO'),
(3, 164, 'Nivel 3', 159, 'Nivel 3', 172, 'Nivel 3', 182, 'Nivel 3', 142, 'Nivel 2', 165, 'Nivel 3', 167, 'Nivel 3', 132, 'Nivel 2', 148, 'Nivel 2', 'A2', 'VALIDO'),
(4, 160, 'Nivel 3', 146, 'Nivel 2', 199, 'Nivel 4', 157, 'Nivel 3', 149, 'Nivel 2', 147, 'Nivel 2', 174, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'A2', 'VALIDO'),
(5, 160, 'Nivel 3', 198, 'Nivel 4', 153, 'Nivel 2', 147, 'Nivel 2', 157, 'Nivel 3', 146, 'Nivel 2', 168, 'Nivel 3', 114, 'Nivel 1', 160, 'Nivel 3', 'A2', 'VALIDO'),
(6, 157, 'Nivel 3', 179, 'Nivel 3', 172, 'Nivel 3', 158, 'Nivel 3', 140, 'Nivel 2', 136, 'Nivel 2', 128, 'Nivel 2', 121, 'Nivel 1', 142, 'Nivel 2', 'A1', 'VALIDO'),
(7, 153, 'Nivel 2', 115, 'Nivel 1', 152, 'Nivel 2', 159, 'Nivel 3', 172, 'Nivel 3', 165, 'Nivel 3', 142, 'Nivel 2', 118, 'Nivel 1', 119, 'Nivel 1', 'A2', 'VALIDO'),
(8, 151, 'Nivel 2', 132, 'Nivel 2', 123, 'Nivel 1', 125, 'Nivel 1', 169, 'Nivel 3', 204, 'Nivel 4', 173, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'B2', 'VALIDO'),
(9, 150, 'Nivel 2', 86, 'Nivel 1', 187, 'Nivel 3', 160, 'Nivel 3', 171, 'Nivel 3', 148, 'Nivel 2', 162, 'Nivel 3', 125, 'Nivel 1', 142, 'Nivel 2', 'A2', 'VALIDO'),
(10, 150, 'Nivel 2', 175, 'Nivel 3', 149, 'Nivel 2', 145, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 162, 'Nivel 3', 76, 'Nivel 1', 125, 'Nivel 1', 'A1', 'VALIDO'),
(11, 149, 'Nivel 2', 209, 'Nivel 4', 143, 'Nivel 2', 117, 'Nivel 1', 129, 'Nivel 2', 147, 'Nivel 2', 137, 'Nivel 2', 125, 'Nivel 1', 136, 'Nivel 2', 'A2', 'VALIDO'),
(12, 146, 'Nivel 2', 93, 'Nivel 1', 183, 'Nivel 3', 155, 'Nivel 2', 164, 'Nivel 3', 133, 'Nivel 2', 174, 'Nivel 3', 130, 'Nivel 2', 154, 'Nivel 2', 'A1', 'VALIDO'),
(13, 141, 'Nivel 2', 125, 'Nivel 1', 157, 'Nivel 3', 138, 'Nivel 2', 135, 'Nivel 2', 152, 'Nivel 2', 176, 'Nivel 3', 128, 'Nivel 2', 165, 'Nivel 3', 'A2', 'VALIDO'),
(14, 141, 'Nivel 2', 150, 'Nivel 2', 136, 'Nivel 2', 145, 'Nivel 2', 150, 'Nivel 2', 126, 'Nivel 2', 148, 'Nivel 2', 129, 'Nivel 2', 131, 'Nivel 2', 'A1', 'VALIDO'),
(15, 140, 'Nivel 2', 128, 'Nivel 2', 146, 'Nivel 2', 146, 'Nivel 2', 132, 'Nivel 2', 147, 'Nivel 2', 130, 'Nivel 2', 110, 'Nivel 1', 125, 'Nivel 1', 'A2', 'VALIDO'),
(16, 139, 'Nivel 2', 129, 'Nivel 2', 138, 'Nivel 2', 148, 'Nivel 2', 146, 'Nivel 2', 135, 'Nivel 2', 109, 'Nivel 1', 107, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(17, 138, 'Nivel 2', 153, 'Nivel 2', 123, 'Nivel 1', 127, 'Nivel 2', 147, 'Nivel 2', 140, 'Nivel 2', 145, 'Nivel 2', 143, 'Nivel 2', 160, 'Nivel 3', 'A1', 'VALIDO'),
(18, 137, 'Nivel 2', 166, 'Nivel 3', 157, 'Nivel 3', 124, 'Nivel 1', 100, 'Nivel 1', 140, 'Nivel 2', 100, 'Nivel 1', 105, 'Nivel 1', 113, 'Nivel 1', 'A1', 'VALIDO'),
(19, 134, 'Nivel 2', 165, 'Nivel 3', 137, 'Nivel 2', 136, 'Nivel 2', 118, 'Nivel 1', 116, 'Nivel 1', 146, 'Nivel 2', 122, 'Nivel 1', 154, 'Nivel 2', 'A0', 'VALIDO'),
(20, 133, 'Nivel 2', 139, 'Nivel 2', 93, 'Nivel 1', 168, 'Nivel 3', 150, 'Nivel 2', 114, 'Nivel 1', 102, 'Nivel 1', 123, 'Nivel 1', 94, 'Nivel 1', 'A0', 'VALIDO'),
(21, 132, 'Nivel 2', 116, 'Nivel 1', 166, 'Nivel 3', 136, 'Nivel 2', 104, 'Nivel 1', 140, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 154, 'Nivel 2', 'A1', 'VALIDO'),
(22, 131, 'Nivel 2', 149, 'Nivel 2', 123, 'Nivel 1', 129, 'Nivel 2', 121, 'Nivel 1', 131, 'Nivel 2', 101, 'Nivel 1', 102, 'Nivel 1', 165, 'Nivel 3', 'A1', 'VALIDO'),
(23, 130, 'Nivel 2', 127, 'Nivel 2', 147, 'Nivel 2', 134, 'Nivel 2', 111, 'Nivel 1', 131, 'Nivel 2', 65, 'Nivel 1', 112, 'Nivel 1', 94, 'Nivel 1', 'A1', 'VALIDO'),
(24, 129, 'Nivel 2', 96, 'Nivel 1', 162, 'Nivel 3', 114, 'Nivel 1', 131, 'Nivel 2', 144, 'Nivel 2', 122, 'Nivel 1', 112, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(25, 126, 'Nivel 2', 81, 'Nivel 1', 134, 'Nivel 2', 126, 'Nivel 2', 149, 'Nivel 2', 139, 'Nivel 2', 127, 'Nivel 2', 136, 'Nivel 2', 142, 'Nivel 2', 'A1', 'VALIDO'),
(26, 125, 'Nivel 1', 124, 'Nivel 1', 135, 'Nivel 2', 108, 'Nivel 1', 92, 'Nivel 1', 165, 'Nivel 3', 132, 'Nivel 2', 104, 'Nivel 1', 131, 'Nivel 2', 'A2', 'VALIDO'),
(27, 124, 'Nivel 1', 131, 'Nivel 2', 131, 'Nivel 2', 107, 'Nivel 1', 88, 'Nivel 1', 162, 'Nivel 3', 136, 'Nivel 2', 112, 'Nivel 1', 148, 'Nivel 2', 'A2', 'VALIDO'),
(28, 122, 'Nivel 1', 166, 'Nivel 3', 113, 'Nivel 1', 113, 'Nivel 1', 112, 'Nivel 1', 106, 'Nivel 1', 135, 'Nivel 2', 117, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(29, 122, 'Nivel 1', 119, 'Nivel 1', 125, 'Nivel 1', 137, 'Nivel 2', 107, 'Nivel 1', 123, 'Nivel 1', 83, 'Nivel 1', 104, 'Nivel 1', 119, 'Nivel 1', 'A1', 'VALIDO'),
(30, 114, 'Nivel 1', 95, 'Nivel 1', 120, 'Nivel 1', 151, 'Nivel 2', 86, 'Nivel 1', 119, 'Nivel 1', 149, 'Nivel 2', 103, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(31, 113, 'Nivel 1', 109, 'Nivel 1', 105, 'Nivel 1', 104, 'Nivel 1', 103, 'Nivel 1', 142, 'Nivel 2', 102, 'Nivel 1', 135, 'Nivel 2', 80, 'Nivel 1', 'A1', 'VALIDO'),
(32, 107, 'Nivel 1', 128, 'Nivel 2', 81, 'Nivel 1', 107, 'Nivel 1', 102, 'Nivel 1', 119, 'Nivel 1', 130, 'Nivel 2', 111, 'Nivel 1', 125, 'Nivel 1', 'A0', 'VALIDO'),
(33, 106, 'Nivel 1', 134, 'Nivel 2', 96, 'Nivel 1', 92, 'Nivel 1', 110, 'Nivel 1', 97, 'Nivel 1', 83, 'Nivel 1', 107, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(34, 96, 'Nivel 1', 0, 'Nivel 1', 117, 'Nivel 1', 122, 'Nivel 1', 105, 'Nivel 1', 137, 'Nivel 2', 157, 'Nivel 3', 96, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(35, NULL, 'ANULADO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ANULADO'),
(36, NULL, 'ANULADO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ANULADO');

-- ===============================
-- CONSULTAS ÚTILES
-- ===============================

-- Ver todos los alumnos con sus resultados
-- SELECT a.*, r.* FROM alumnos a LEFT JOIN resultados r ON a.id = r.alumno_id;

-- Ver estadísticas por nivel
-- SELECT puntaje_nivel, COUNT(*) as cantidad FROM resultados WHERE estado = 'VALIDO' GROUP BY puntaje_nivel ORDER BY cantidad DESC;

-- Ver alumnos que no pueden graduarse (puntaje < 100)
-- SELECT a.*, r.puntaje_global FROM alumnos a INNER JOIN resultados r ON a.id = r.alumno_id WHERE r.puntaje_global < 100 AND r.estado = 'VALIDO';

-- Ver alumnos sin resultados
-- SELECT a.* FROM alumnos a LEFT JOIN resultados r ON a.id = r.alumno_id WHERE r.id IS NULL;

-- ===============================
-- CONFIGURACIÓN DE USUARIOS
-- Los usuarios están configurados en SecurityConfig.java:
-- admin / admin123 (COORDINACION)
-- coordinador / admin123 (COORDINACION)  
-- estudiante1 / estudiante123 (ESTUDIANTE)
-- ===============================

SELECT 'Base de datos creada exitosamente con 36 alumnos y 34 resultados SABER PRO' AS mensaje;
