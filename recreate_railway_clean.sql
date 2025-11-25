-- ===============================
-- SCRIPT PARA RECREAR TABLAS EN RAILWAY
-- ===============================
-- Ejecutar este script en Railway MySQL para limpiar y recrear

-- 1. ELIMINAR TABLAS EXISTENTES
DROP TABLE IF EXISTS resultados;
DROP TABLE IF EXISTS alumnos;

-- 2. RECREAR TABLA ALUMNOS
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

-- 3. RECREAR TABLA RESULTADOS
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

-- 4. VERIFICAR ESTRUCTURA
DESCRIBE alumnos;
DESCRIBE resultados;

SELECT 'Tablas recreadas correctamente' AS resultado;
