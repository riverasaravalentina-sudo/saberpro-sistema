-- =====================================================
-- SCRIPT SQL PARA MySQL - SISTEMA SABER PRO
-- Base de datos: saberpro_db
-- =====================================================

-- Crear base de datos
CREATE DATABASE IF NOT EXISTS saberpro_db 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE saberpro_db;

-- =====================================================
-- TABLA: roles
-- =====================================================
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: usuarios
-- =====================================================
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    nombre_completo VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: usuario_roles (relación muchos a muchos)
-- =====================================================
CREATE TABLE IF NOT EXISTS usuario_roles (
    usuario_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: alumnos
-- =====================================================
CREATE TABLE IF NOT EXISTS alumnos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    documento VARCHAR(255) NOT NULL UNIQUE,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    programa VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- TABLA: resultados
-- =====================================================
CREATE TABLE IF NOT EXISTS resultados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alumno_id BIGINT UNIQUE,
    puntaje_global INT NOT NULL,
    modulo_lectura INT NOT NULL,
    modulo_razonamiento INT NOT NULL,
    modulo_ingles INT NOT NULL,
    fecha_presentacion DATE,
    periodo_academico VARCHAR(255),
    FOREIGN KEY (alumno_id) REFERENCES alumnos(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =====================================================
-- INSERTAR ROLES
-- =====================================================
INSERT INTO roles (nombre, descripcion) VALUES
('ROLE_COORDINACION', 'Coordinador académico con acceso completo'),
('ROLE_ESTUDIANTE', 'Estudiante con acceso limitado');

-- =====================================================
-- INSERTAR USUARIOS
-- =====================================================
INSERT INTO usuarios (username, password, email, nombre_completo, activo) VALUES
('admin', '{noop}admin123', 'admin@uts.edu.co', 'Coordinador Sistema', TRUE),
('estudiante1', '{noop}estudiante123', 'estudiante1@uts.edu.co', 'Estudiante Demo', TRUE);

-- =====================================================
-- ASIGNAR ROLES A USUARIOS
-- =====================================================
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES
(1, 1), -- admin tiene ROLE_COORDINACION
(2, 2); -- estudiante1 tiene ROLE_ESTUDIANTE

-- =====================================================
-- INSERTAR ALUMNOS (36 registros)
-- =====================================================
INSERT INTO alumnos (documento, nombres, apellidos, correo, programa) VALUES
('EK20183007722', 'N/A', 'BARBOSA', 'barbosa.ek20183007722@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183140703', 'N/A', 'QUINTERO', 'quintero.ek20183140703@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183040545', 'N/A', 'PARRA', 'parra.ek20183040545@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183025381', 'N/A', 'ANAYA', 'anaya.ek20183025381@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183025335', 'N/A', 'FLOR', 'flor.ek20183025335@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183122648', 'N/A', 'GARCIA', 'garcia.ek20183122648@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183064605', 'N/A', 'MANOSALVA', 'manosalva.ek20183064605@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183187351', 'N/A', 'MENDOZA', 'mendoza.ek20183187351@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183233820', 'N/A', 'BELTRAN', 'beltran.ek20183233820@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183030016', 'N/A', 'SANTAMARIA', 'santamaria.ek20183030016@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183047073', 'N/A', 'SANCHEZ', 'sanchez.ek20183047073@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183236451', 'N/A', 'ROMERO', 'romero.ek20183236451@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183041714', 'N/A', 'LUNA', 'luna.ek20183041714@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183187801', 'N/A', 'TRIANA', 'triana.ek20183187801@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183176566', 'N/A', 'SUAREZ', 'suarez.ek20183176566@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183204427', 'N/A', 'GARCIA', 'garcia2.ek20183204427@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183196280', 'N/A', 'PINZON', 'pinzon.ek20183196280@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183173799', 'N/A', 'JAIMES', 'jaimes.ek20183173799@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183009565', 'N/A', 'NIÑO', 'nino.ek20183009565@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183117756', 'N/A', 'FABIAN', 'fabian.ek20183117756@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183044579', 'N/A', 'HERNANDEZ', 'hernandez.ek20183044579@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183045760', 'N/A', 'LARIOS', 'larios.ek20183045760@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183034044', 'N/A', 'CALDERON', 'calderon.ek20183034044@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183041521', 'N/A', 'VILLARREAL', 'villarreal.ek20183041521@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183027436', 'N/A', 'RESTREPO', 'restrepo.ek20183027436@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183031592', 'N/A', 'CACERES', 'caceres.ek20183031592@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183004153', 'N/A', 'TABARES', 'tabares.ek20183004153@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183030783', 'N/A', 'NARANJO', 'naranjo.ek20183030783@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183024754', 'N/A', 'PRADA', 'prada.ek20183024754@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183186200', 'N/A', 'VARGAS', 'vargas.ek20183186200@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183182410', 'N/A', 'TORRES', 'torres.ek20183182410@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183213735', 'N/A', 'ORTIZ', 'ortiz.ek20183213735@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183065220', 'N/A', 'VILLAMIZAR', 'villamizar.ek20183065220@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183028123', 'N/A', 'RESTREPO', 'restrepo2.ek20183028123@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183207870', 'N/A', 'HIGUERA', 'higuera.ek20183207870@uts.edu.co', 'Ingeniería de Sistemas'),
('EK20183144329', 'N/A', 'MATIZ', 'matiz.ek20183144329@uts.edu.co', 'Ingeniería de Sistemas');

-- =====================================================
-- INSERTAR RESULTADOS (34 registros - excluyendo ANULADOS)
-- =====================================================
INSERT INTO resultados (alumno_id, puntaje_global, modulo_lectura, modulo_razonamiento, modulo_ingles, fecha_presentacion, periodo_academico) VALUES
(1, 200, 202, 182, 128, '2018-11-15', '2018-2'),
(2, 165, 179, 151, 125, '2018-11-15', '2018-2'),
(3, 164, 182, 172, 159, '2018-11-15', '2018-2'),
(4, 160, 157, 199, 146, '2018-11-15', '2018-2'),
(5, 160, 147, 153, 198, '2018-11-15', '2018-2'),
(6, 157, 158, 172, 179, '2018-11-15', '2018-2'),
(7, 153, 159, 152, 115, '2018-11-15', '2018-2'),
(8, 151, 125, 123, 132, '2018-11-15', '2018-2'),
(9, 150, 160, 187, 86, '2018-11-15', '2018-2'),
(10, 150, 145, 149, 175, '2018-11-15', '2018-2'),
(11, 149, 117, 143, 209, '2018-11-15', '2018-2'),
(12, 146, 155, 183, 93, '2018-11-15', '2018-2'),
(13, 141, 138, 157, 125, '2018-11-15', '2018-2'),
(14, 141, 145, 136, 150, '2018-11-15', '2018-2'),
(15, 140, 146, 146, 128, '2018-11-15', '2018-2'),
(16, 139, 148, 138, 129, '2018-11-15', '2018-2'),
(17, 138, 127, 123, 153, '2018-11-15', '2018-2'),
(18, 137, 124, 157, 166, '2018-11-15', '2018-2'),
(19, 134, 136, 137, 165, '2018-11-15', '2018-2'),
(20, 133, 168, 93, 139, '2018-11-15', '2018-2'),
(21, 132, 136, 166, 116, '2018-11-15', '2018-2'),
(22, 131, 129, 123, 149, '2018-11-15', '2018-2'),
(23, 130, 134, 147, 127, '2018-11-15', '2018-2'),
(24, 129, 114, 162, 96, '2018-11-15', '2018-2'),
(25, 126, 126, 134, 81, '2018-11-15', '2018-2'),
(26, 125, 108, 135, 124, '2018-11-15', '2018-2'),
(27, 124, 107, 131, 131, '2018-11-15', '2018-2'),
(28, 122, 113, 113, 166, '2018-11-15', '2018-2'),
(29, 122, 137, 125, 119, '2018-11-15', '2018-2'),
(30, 114, 151, 120, 95, '2018-11-15', '2018-2'),
(31, 113, 104, 105, 109, '2018-11-15', '2018-2'),
(32, 107, 107, 81, 128, '2018-11-15', '2018-2'),
(33, 106, 92, 96, 134, '2018-11-15', '2018-2'),
(34, 96, 122, 117, 0, '2018-11-15', '2018-2');

-- =====================================================
-- CONSULTAS DE VERIFICACIÓN
-- =====================================================

-- Ver todos los alumnos
SELECT * FROM alumnos ORDER BY id;

-- Ver todos los resultados con información del alumno
SELECT 
    a.documento,
    a.apellidos,
    a.nombres,
    r.puntaje_global,
    r.modulo_lectura,
    r.modulo_razonamiento,
    r.modulo_ingles,
    r.periodo_academico
FROM alumnos a
LEFT JOIN resultados r ON a.id = r.alumno_id
ORDER BY r.puntaje_global DESC;

-- Ver estadísticas generales
SELECT 
    COUNT(*) as total_alumnos,
    COUNT(r.id) as con_resultados,
    COUNT(*) - COUNT(r.id) as sin_resultados,
    ROUND(AVG(r.puntaje_global), 2) as promedio_global,
    MAX(r.puntaje_global) as mejor_puntaje,
    MIN(r.puntaje_global) as menor_puntaje
FROM alumnos a
LEFT JOIN resultados r ON a.id = r.alumno_id;

-- =====================================================
-- FIN DEL SCRIPT
-- =====================================================
