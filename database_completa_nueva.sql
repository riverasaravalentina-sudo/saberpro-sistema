-- ===============================
-- BASE DE DATOS COMPLETA - SISTEMA SABER PRO UTS
-- ===============================

-- Eliminar base de datos si existe y crear nueva
DROP DATABASE IF EXISTS railway;
CREATE DATABASE railway CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE railway;

-- ===============================
-- 1. TABLA DE ROLES (Coordinación y Estudiante)
-- ===============================
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ===============================
-- 2. TABLA DE USUARIOS (Sistema de login)
-- ===============================
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ===============================
-- 3. TABLA USUARIO_ROLES (Relación muchos a muchos)
-- ===============================
CREATE TABLE usuario_roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    UNIQUE KEY unique_usuario_role (usuario_id, role_id)
) ENGINE=InnoDB;

-- ===============================
-- 4. TABLA ALUMNO (Estudiantes)
-- ===============================
CREATE TABLE alumno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(10) DEFAULT 'CC',
    numero_documento VARCHAR(20) NOT NULL UNIQUE,
    primer_apellido VARCHAR(100) NOT NULL,
    segundo_apellido VARCHAR(100),
    primer_nombre VARCHAR(100) NOT NULL,
    segundo_nombre VARCHAR(100),
    correo_electronico VARCHAR(100),
    numero_telefonico VARCHAR(20),
    programa VARCHAR(200) DEFAULT 'Ingeniería de Sistemas',
    semestre INT,
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_documento (numero_documento),
    INDEX idx_apellidos (primer_apellido, segundo_apellido)
) ENGINE=InnoDB;

-- ===============================
-- 5. TABLA RESULTADO (Resultados SABER PRO)
-- ===============================
CREATE TABLE resultado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    alumno_id BIGINT NOT NULL,
    numero_registro VARCHAR(50) NOT NULL UNIQUE,
    
    -- Puntaje Global
    puntaje_global INT,
    nivel_global VARCHAR(20),
    
    -- Comunicación Escrita
    comunicacion_escrita INT,
    comunicacion_escrita_nivel VARCHAR(20),
    
    -- Razonamiento Cuantitativo
    razonamiento_cuantitativo INT,
    razonamiento_cuantitativo_nivel VARCHAR(20),
    
    -- Lectura Crítica
    lectura_critica INT,
    lectura_critica_nivel VARCHAR(20),
    
    -- Competencias Ciudadanas
    competencias_ciudadanas INT,
    competencias_ciudadanas_nivel VARCHAR(20),
    
    -- Inglés (puntaje numérico)
    ingles INT,
    ingles_nivel VARCHAR(20),
    
    -- Formulación De Proyectos De Ingeniería
    formulacion_proyectos INT,
    formulacion_proyectos_nivel VARCHAR(20),
    
    -- Pensamiento Científico - Matemáticas Y Estadística
    pensamiento_cientifico INT,
    pensamiento_cientifico_nivel VARCHAR(20),
    
    -- Diseño De Software
    diseño_software INT,
    diseño_software_nivel VARCHAR(20),
    
    -- Nivel Inglés (A0, A1, A2, B1, B2, etc.)
    nivel_ingles VARCHAR(10),
    
    -- Información adicional
    fecha_presentacion DATE DEFAULT '2024-03-15',
    periodo_academico VARCHAR(20) DEFAULT '2024-1',
    estado VARCHAR(20) DEFAULT 'VALIDO',
    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    FOREIGN KEY (alumno_id) REFERENCES alumno(id) ON DELETE CASCADE,
    INDEX idx_puntaje_global (puntaje_global),
    INDEX idx_estado (estado),
    INDEX idx_fecha (fecha_presentacion)
) ENGINE=InnoDB;

-- ===============================
-- INSERTAR DATOS INICIALES
-- ===============================

-- Insertar Roles
INSERT INTO roles (nombre, descripcion) VALUES
('ROLE_COORDINACION', 'Coordinador - Acceso completo al sistema'),
('ROLE_ESTUDIANTE', 'Estudiante - Acceso limitado a consulta');

-- Insertar Usuarios (password: admin123 y estudiante123 - en producción usar BCrypt)
INSERT INTO usuarios (username, password, email, activo) VALUES
('coordinador', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'coordinador@uts.edu.co', TRUE),
('estudiante', '$2a$10$DowJonesH9Ng4K.RL8X3rOTR8Qi2O7Gi9aZRjBnNLq8qE7LjDlKrG', 'estudiante@uts.edu.co', TRUE);

-- Asignar Roles a Usuarios
INSERT INTO usuario_roles (usuario_id, role_id) VALUES
(1, 1), -- coordinador tiene rol COORDINACION
(2, 2); -- estudiante tiene rol ESTUDIANTE

-- ===============================
-- INSERTAR ALUMNOS CON DATOS COMPLETOS
-- ===============================
INSERT INTO alumno (tipo_documento, numero_documento, primer_apellido, segundo_apellido, primer_nombre, segundo_nombre, correo_electronico, numero_telefonico, semestre) VALUES
('CC', '1098765001', 'BARBOSA', 'MARTINEZ', 'CARLOS', 'ANDRES', 'carlos.barbosa@uts.edu.co', '3101234567', 10),
('CC', '1098765002', 'QUINTERO', 'LOPEZ', 'MARIA', 'FERNANDA', 'maria.quintero@uts.edu.co', '3101234568', 10),
('CC', '1098765003', 'PARRA', 'RODRIGUEZ', 'JUAN', 'SEBASTIAN', 'juan.parra@uts.edu.co', '3101234569', 10),
('CC', '1098765004', 'ANAYA', 'GOMEZ', 'LAURA', 'CRISTINA', 'laura.anaya@uts.edu.co', '3101234570', 10),
('CC', '1098765005', 'FLOR', 'DIAZ', 'MIGUEL', 'ANGEL', 'miguel.flor@uts.edu.co', '3101234571', 10),
('CC', '1098765006', 'GARCIA', 'HERNANDEZ', 'ANDREA', 'PAOLA', 'andrea.garcia@uts.edu.co', '3101234572', 10),
('CC', '1098765007', 'MANOSALVA', 'SILVA', 'DAVID', 'ALEJANDRO', 'david.manosalva@uts.edu.co', '3101234573', 10),
('CC', '1098765008', 'MENDOZA', 'CASTRO', 'DIANA', 'MARCELA', 'diana.mendoza@uts.edu.co', '3101234574', 10),
('CC', '1098765009', 'BELTRAN', 'RAMIREZ', 'JORGE', 'LUIS', 'jorge.beltran@uts.edu.co', '3101234575', 10),
('CC', '1098765010', 'SANTAMARIA', 'TORRES', 'CAMILA', 'ANDREA', 'camila.santamaria@uts.edu.co', '3101234576', 10),
('CC', '1098765011', 'SANCHEZ', 'MORENO', 'OSCAR', 'DANIEL', 'oscar.sanchez@uts.edu.co', '3101234577', 10),
('CC', '1098765012', 'ROMERO', 'VARGAS', 'NATALIA', 'SOFIA', 'natalia.romero@uts.edu.co', '3101234578', 10),
('CC', '1098765013', 'LUNA', 'JIMENEZ', 'FELIPE', 'SANTIAGO', 'felipe.luna@uts.edu.co', '3101234579', 10),
('CC', '1098765014', 'TRIANA', 'PEREZ', 'VALENTINA', 'ISABEL', 'valentina.triana@uts.edu.co', '3101234580', 10),
('CC', '1098765015', 'SUAREZ', 'ORTIZ', 'ANDRES', 'FELIPE', 'andres.suarez@uts.edu.co', '3101234581', 10),
('CC', '1098765016', 'GARCIA', 'RUIZ', 'PAOLA', 'ANDREA', 'paola.garcia@uts.edu.co', '3101234582', 10),
('CC', '1098765017', 'PINZON', 'MEJIA', 'CRISTIAN', 'CAMILO', 'cristian.pinzon@uts.edu.co', '3101234583', 10),
('CC', '1098765018', 'JAIMES', 'ARIAS', 'ANGELA', 'MARIA', 'angela.jaimes@uts.edu.co', '3101234584', 10),
('CC', '1098765019', 'NIÑO', 'CARDENAS', 'SANTIAGO', 'ANDRES', 'santiago.nino@uts.edu.co', '3101234585', 10),
('CC', '1098765020', 'FABIAN', 'MORA', 'JESSICA', 'PAOLA', 'jessica.fabian@uts.edu.co', '3101234586', 10),
('CC', '1098765021', 'HERNANDEZ', 'SUAREZ', 'LEONARDO', 'JAVIER', 'leonardo.hernandez@uts.edu.co', '3101234587', 10),
('CC', '1098765022', 'LARIOS', 'MEDINA', 'CATALINA', 'MARIA', 'catalina.larios@uts.edu.co', '3101234588', 10),
('CC', '1098765023', 'CALDERON', 'GUERRERO', 'DANIEL', 'ALBERTO', 'daniel.calderon@uts.edu.co', '3101234589', 10),
('CC', '1098765024', 'VILLARREAL', 'CAMPOS', 'JULIANA', 'ANDREA', 'juliana.villarreal@uts.edu.co', '3101234590', 10),
('CC', '1098765025', 'RESTREPO', 'ARANGO', 'NICOLAS', 'ALEJANDRO', 'nicolas.restrepo@uts.edu.co', '3101234591', 10),
('CC', '1098765026', 'CACERES', 'VEGA', 'STEFANIA', 'LUCIA', 'stefania.caceres@uts.edu.co', '3101234592', 10),
('CC', '1098765027', 'TABARES', 'MONTOYA', 'SEBASTIAN', 'FELIPE', 'sebastian.tabares@uts.edu.co', '3101234593', 10),
('CC', '1098765028', 'NARANJO', 'OSPINA', 'DANIELA', 'CRISTINA', 'daniela.naranjo@uts.edu.co', '3101234594', 10),
('CC', '1098765029', 'PRADA', 'ROJAS', 'ALEXANDER', 'DAVID', 'alexander.prada@uts.edu.co', '3101234595', 10),
('CC', '1098765030', 'VARGAS', 'LEON', 'CAROLINA', 'ANDREA', 'carolina.vargas@uts.edu.co', '3101234596', 10),
('CC', '1098765031', 'TORRES', 'PATIÑO', 'MANUEL', 'ALEJANDRO', 'manuel.torres@uts.edu.co', '3101234597', 10),
('CC', '1098765032', 'ORTIZ', 'SALAZAR', 'LUISA', 'FERNANDA', 'luisa.ortiz@uts.edu.co', '3101234598', 10),
('CC', '1098765033', 'VILLAMIZAR', 'CONTRERAS', 'JAIRO', 'ANDRES', 'jairo.villamizar@uts.edu.co', '3101234599', 10),
('CC', '1098765034', 'RESTREPO', 'MARIN', 'YULIANA', 'PATRICIA', 'yuliana.restrepo@uts.edu.co', '3101234600', 10),
('CC', '1098765035', 'HIGUERA', 'CASTILLO', 'ROBERTO', 'CARLOS', 'roberto.higuera@uts.edu.co', '3101234601', 10),
('CC', '1098765036', 'MATIZ', 'FORERO', 'SANDRA', 'MILENA', 'sandra.matiz@uts.edu.co', '3101234602', 10);

-- ===============================
-- INSERTAR RESULTADOS SABER PRO
-- ===============================
INSERT INTO resultado (alumno_id, numero_registro, puntaje_global, nivel_global, comunicacion_escrita, comunicacion_escrita_nivel, razonamiento_cuantitativo, razonamiento_cuantitativo_nivel, lectura_critica, lectura_critica_nivel, competencias_ciudadanas, competencias_ciudadanas_nivel, ingles, ingles_nivel, formulacion_proyectos, formulacion_proyectos_nivel, pensamiento_cientifico, pensamiento_cientifico_nivel, diseño_software, diseño_software_nivel, nivel_ingles, estado) VALUES
(1, 'EK20183007722', 200, 'Nivel 4', 128, 'Nivel 2', 182, 'Nivel 3', 202, 'Nivel 4', 206, 'Nivel 4', 183, 'Nivel 3', 185, 'Nivel 3', 160, 'Nivel 3', 197, 'Nivel 4', 'B1', 'VALIDO'),
(2, 'EK20183140703', 165, 'Nivel 3', 125, 'Nivel 1', 151, 'Nivel 2', 179, 'Nivel 3', 163, 'Nivel 3', 205, 'Nivel 4', 182, 'Nivel 3', 144, 'Nivel 2', 136, 'Nivel 2', 'B2', 'VALIDO'),
(3, 'EK20183040545', 164, 'Nivel 3', 159, 'Nivel 3', 172, 'Nivel 3', 182, 'Nivel 3', 142, 'Nivel 2', 165, 'Nivel 3', 167, 'Nivel 3', 132, 'Nivel 2', 148, 'Nivel 2', 'A2', 'VALIDO'),
(4, 'EK20183025381', 160, 'Nivel 3', 146, 'Nivel 2', 199, 'Nivel 4', 157, 'Nivel 3', 149, 'Nivel 2', 147, 'Nivel 2', 174, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'A2', 'VALIDO'),
(5, 'EK20183025335', 160, 'Nivel 3', 198, 'Nivel 4', 153, 'Nivel 2', 147, 'Nivel 2', 157, 'Nivel 3', 146, 'Nivel 2', 168, 'Nivel 3', 114, 'Nivel 1', 160, 'Nivel 3', 'A2', 'VALIDO'),
(6, 'EK20183122648', 157, 'Nivel 3', 179, 'Nivel 3', 172, 'Nivel 3', 158, 'Nivel 3', 140, 'Nivel 2', 136, 'Nivel 2', 128, 'Nivel 2', 121, 'Nivel 1', 142, 'Nivel 2', 'A1', 'VALIDO'),
(7, 'EK20183064605', 153, 'Nivel 2', 115, 'Nivel 1', 152, 'Nivel 2', 159, 'Nivel 3', 172, 'Nivel 3', 165, 'Nivel 3', 142, 'Nivel 2', 118, 'Nivel 1', 119, 'Nivel 1', 'A2', 'VALIDO'),
(8, 'EK20183187351', 151, 'Nivel 2', 132, 'Nivel 2', 123, 'Nivel 1', 125, 'Nivel 1', 169, 'Nivel 3', 204, 'Nivel 4', 173, 'Nivel 3', 127, 'Nivel 2', 171, 'Nivel 3', 'B2', 'VALIDO'),
(9, 'EK20183233820', 150, 'Nivel 2', 86, 'Nivel 1', 187, 'Nivel 3', 160, 'Nivel 3', 171, 'Nivel 3', 148, 'Nivel 2', 162, 'Nivel 3', 125, 'Nivel 1', 142, 'Nivel 2', 'A2', 'VALIDO'),
(10, 'EK20183030016', 150, 'Nivel 2', 175, 'Nivel 3', 149, 'Nivel 2', 145, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 162, 'Nivel 3', 76, 'Nivel 1', 125, 'Nivel 1', 'A1', 'VALIDO'),
(11, 'EK20183047073', 149, 'Nivel 2', 209, 'Nivel 4', 143, 'Nivel 2', 117, 'Nivel 1', 129, 'Nivel 2', 147, 'Nivel 2', 137, 'Nivel 2', 125, 'Nivel 1', 136, 'Nivel 2', 'A2', 'VALIDO'),
(12, 'EK20183236451', 146, 'Nivel 2', 93, 'Nivel 1', 183, 'Nivel 3', 155, 'Nivel 2', 164, 'Nivel 3', 133, 'Nivel 2', 174, 'Nivel 3', 130, 'Nivel 2', 154, 'Nivel 2', 'A1', 'VALIDO'),
(13, 'EK20183041714', 141, 'Nivel 2', 125, 'Nivel 1', 157, 'Nivel 3', 138, 'Nivel 2', 135, 'Nivel 2', 152, 'Nivel 2', 176, 'Nivel 3', 128, 'Nivel 2', 165, 'Nivel 3', 'A2', 'VALIDO'),
(14, 'EK20183187801', 141, 'Nivel 2', 150, 'Nivel 2', 136, 'Nivel 2', 145, 'Nivel 2', 150, 'Nivel 2', 126, 'Nivel 2', 148, 'Nivel 2', 129, 'Nivel 2', 131, 'Nivel 2', 'A1', 'VALIDO'),
(15, 'EK20183176566', 140, 'Nivel 2', 128, 'Nivel 2', 146, 'Nivel 2', 146, 'Nivel 2', 132, 'Nivel 2', 147, 'Nivel 2', 130, 'Nivel 2', 110, 'Nivel 1', 125, 'Nivel 1', 'A2', 'VALIDO'),
(16, 'EK20183204427', 139, 'Nivel 2', 129, 'Nivel 2', 138, 'Nivel 2', 148, 'Nivel 2', 146, 'Nivel 2', 135, 'Nivel 2', 109, 'Nivel 1', 107, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(17, 'EK20183196280', 138, 'Nivel 2', 153, 'Nivel 2', 123, 'Nivel 1', 127, 'Nivel 2', 147, 'Nivel 2', 140, 'Nivel 2', 145, 'Nivel 2', 143, 'Nivel 2', 160, 'Nivel 3', 'A1', 'VALIDO'),
(18, 'EK20183173799', 137, 'Nivel 2', 166, 'Nivel 3', 157, 'Nivel 3', 124, 'Nivel 1', 100, 'Nivel 1', 140, 'Nivel 2', 100, 'Nivel 1', 105, 'Nivel 1', 113, 'Nivel 1', 'A1', 'VALIDO'),
(19, 'EK20183009565', 134, 'Nivel 2', 165, 'Nivel 3', 137, 'Nivel 2', 136, 'Nivel 2', 118, 'Nivel 1', 116, 'Nivel 1', 146, 'Nivel 2', 122, 'Nivel 1', 154, 'Nivel 2', 'A0', 'VALIDO'),
(20, 'EK20183117756', 133, 'Nivel 2', 139, 'Nivel 2', 93, 'Nivel 1', 168, 'Nivel 3', 150, 'Nivel 2', 114, 'Nivel 1', 102, 'Nivel 1', 123, 'Nivel 1', 94, 'Nivel 1', 'A0', 'VALIDO'),
(21, 'EK20183044579', 132, 'Nivel 2', 116, 'Nivel 1', 166, 'Nivel 3', 136, 'Nivel 2', 104, 'Nivel 1', 140, 'Nivel 2', 158, 'Nivel 3', 125, 'Nivel 1', 154, 'Nivel 2', 'A1', 'VALIDO'),
(22, 'EK20183045760', 131, 'Nivel 2', 149, 'Nivel 2', 123, 'Nivel 1', 129, 'Nivel 2', 121, 'Nivel 1', 131, 'Nivel 2', 101, 'Nivel 1', 102, 'Nivel 1', 165, 'Nivel 3', 'A1', 'VALIDO'),
(23, 'EK20183034044', 130, 'Nivel 2', 127, 'Nivel 2', 147, 'Nivel 2', 134, 'Nivel 2', 111, 'Nivel 1', 131, 'Nivel 2', 65, 'Nivel 1', 112, 'Nivel 1', 94, 'Nivel 1', 'A1', 'VALIDO'),
(24, 'EK20183041521', 129, 'Nivel 2', 96, 'Nivel 1', 162, 'Nivel 3', 114, 'Nivel 1', 131, 'Nivel 2', 144, 'Nivel 2', 122, 'Nivel 1', 112, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(25, 'EK20183027436', 126, 'Nivel 2', 81, 'Nivel 1', 134, 'Nivel 2', 126, 'Nivel 2', 149, 'Nivel 2', 139, 'Nivel 2', 127, 'Nivel 2', 136, 'Nivel 2', 142, 'Nivel 2', 'A1', 'VALIDO'),
(26, 'EK20183031592', 125, 'Nivel 1', 124, 'Nivel 1', 135, 'Nivel 2', 108, 'Nivel 1', 92, 'Nivel 1', 165, 'Nivel 3', 132, 'Nivel 2', 104, 'Nivel 1', 131, 'Nivel 2', 'A2', 'VALIDO'),
(27, 'EK20183004153', 124, 'Nivel 1', 131, 'Nivel 2', 131, 'Nivel 2', 107, 'Nivel 1', 88, 'Nivel 1', 162, 'Nivel 3', 136, 'Nivel 2', 112, 'Nivel 1', 148, 'Nivel 2', 'A2', 'VALIDO'),
(28, 'EK20183030783', 122, 'Nivel 1', 166, 'Nivel 3', 113, 'Nivel 1', 113, 'Nivel 1', 112, 'Nivel 1', 106, 'Nivel 1', 135, 'Nivel 2', 117, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(29, 'EK20183024754', 122, 'Nivel 1', 119, 'Nivel 1', 125, 'Nivel 1', 137, 'Nivel 2', 107, 'Nivel 1', 123, 'Nivel 1', 83, 'Nivel 1', 104, 'Nivel 1', 119, 'Nivel 1', 'A1', 'VALIDO'),
(30, 'EK20183186200', 114, 'Nivel 1', 95, 'Nivel 1', 120, 'Nivel 1', 151, 'Nivel 2', 86, 'Nivel 1', 119, 'Nivel 1', 149, 'Nivel 2', 103, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(31, 'EK20183182410', 113, 'Nivel 1', 109, 'Nivel 1', 105, 'Nivel 1', 104, 'Nivel 1', 103, 'Nivel 1', 142, 'Nivel 2', 102, 'Nivel 1', 135, 'Nivel 2', 80, 'Nivel 1', 'A1', 'VALIDO'),
(32, 'EK20183213735', 107, 'Nivel 1', 128, 'Nivel 2', 81, 'Nivel 1', 107, 'Nivel 1', 102, 'Nivel 1', 119, 'Nivel 1', 130, 'Nivel 2', 111, 'Nivel 1', 125, 'Nivel 1', 'A0', 'VALIDO'),
(33, 'EK20183065220', 106, 'Nivel 1', 134, 'Nivel 2', 96, 'Nivel 1', 92, 'Nivel 1', 110, 'Nivel 1', 97, 'Nivel 1', 83, 'Nivel 1', 107, 'Nivel 1', 119, 'Nivel 1', 'A0', 'VALIDO'),
(34, 'EK20183028123', 96, 'Nivel 1', 0, 'Nivel 1', 117, 'Nivel 1', 122, 'Nivel 1', 105, 'Nivel 1', 137, 'Nivel 2', 157, 'Nivel 3', 96, 'Nivel 1', 131, 'Nivel 2', 'A1', 'VALIDO'),
(35, 'EK20183207870', NULL, 'ANULADO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ANULADO'),
(36, 'EK20183144329', NULL, 'ANULADO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ANULADO');

-- ===============================
-- VISTAS ÚTILES PARA REPORTES
-- ===============================

-- Vista: Estudiantes con puntajes sobresalientes (>171 para Saber PRO)
CREATE VIEW vista_beneficios_sobresalientes AS
SELECT 
    a.numero_documento,
    CONCAT(a.primer_nombre, ' ', IFNULL(a.segundo_nombre, ''), ' ', a.primer_apellido, ' ', IFNULL(a.segundo_apellido, '')) AS nombre_completo,
    r.puntaje_global,
    r.nivel_global,
    CASE 
        WHEN r.puntaje_global >= 241 THEN 'Beca 100% + Nota 5.0'
        WHEN r.puntaje_global BETWEEN 211 AND 240 THEN 'Beca 50% + Nota 4.7'
        WHEN r.puntaje_global BETWEEN 180 AND 210 THEN 'Nota 4.5'
    END AS beneficio
FROM alumno a
INNER JOIN resultado r ON a.id = r.alumno_id
WHERE r.puntaje_global >= 180 AND r.estado = 'VALIDO'
ORDER BY r.puntaje_global DESC;

-- Vista: Estudiantes que NO pueden graduarse (<80 puntos)
CREATE VIEW vista_no_pueden_graduarse AS
SELECT 
    a.numero_documento,
    CONCAT(a.primer_nombre, ' ', IFNULL(a.segundo_nombre, ''), ' ', a.primer_apellido, ' ', IFNULL(a.segundo_apellido, '')) AS nombre_completo,
    a.correo_electronico,
    r.puntaje_global,
    r.numero_registro
FROM alumno a
INNER JOIN resultado r ON a.id = r.alumno_id
WHERE r.puntaje_global < 80 AND r.estado = 'VALIDO'
ORDER BY r.puntaje_global ASC;

-- Vista: Top 10 mejores puntajes
CREATE VIEW vista_top10_mejores AS
SELECT 
    a.numero_documento,
    CONCAT(a.primer_nombre, ' ', IFNULL(a.segundo_nombre, ''), ' ', a.primer_apellido, ' ', IFNULL(a.segundo_apellido, '')) AS nombre_completo,
    r.puntaje_global,
    r.nivel_ingles,
    r.numero_registro
FROM alumno a
INNER JOIN resultado r ON a.id = r.alumno_id
WHERE r.estado = 'VALIDO'
ORDER BY r.puntaje_global DESC
LIMIT 10;

-- ===============================
-- VERIFICACIÓN FINAL
-- ===============================
SELECT 'Base de datos creada exitosamente' AS resultado;
SELECT COUNT(*) AS total_alumnos FROM alumno;
SELECT COUNT(*) AS total_resultados_validos FROM resultado WHERE estado = 'VALIDO';
SELECT COUNT(*) AS total_anulados FROM resultado WHERE estado = 'ANULADO';
SELECT COUNT(*) AS total_no_graduables FROM alumno a 
INNER JOIN resultado r ON a.id = r.alumno_id 
WHERE r.puntaje_global < 80 AND r.estado = 'VALIDO';
