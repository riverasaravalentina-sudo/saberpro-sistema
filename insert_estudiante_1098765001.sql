-- Script para insertar el estudiante 1098765001 en Railway MySQL
-- Ejecutar este script en la base de datos de Railway

-- Insertar el alumno si no existe
INSERT INTO alumno (tipo_documento, numero_documento, primer_apellido, segundo_apellido, primer_nombre, segundo_nombre, correo_electronico, numero_telefonico, semestre, programa, activo)
VALUES ('CC', '1098765001', 'BARBOSA', 'MARTINEZ', 'CARLOS', 'ANDRES', 'carlos.barbosa@uts.edu.co', '3101234567', 10, 'Ingenieria de Sistemas', true)
ON DUPLICATE KEY UPDATE 
    primer_apellido = 'BARBOSA',
    segundo_apellido = 'MARTINEZ',
    primer_nombre = 'CARLOS',
    segundo_nombre = 'ANDRES',
    correo_electronico = 'carlos.barbosa@uts.edu.co',
    numero_telefonico = '3101234567',
    semestre = 10,
    programa = 'Ingenieria de Sistemas',
    activo = true;

-- Insertar el resultado SABER PRO
INSERT INTO resultado (
    alumno_id,
    numero_registro,
    puntaje_global,
    nivel_global,
    lectura_critica,
    lectura_critica_nivel,
    razonamiento_cuantitativo,
    razonamiento_cuantitativo_nivel,
    competencias_ciudadanas,
    competencias_ciudadanas_nivel,
    ingles,
    ingles_nivel,
    nivel_ingles,
    comunicacion_escrita,
    comunicacion_escrita_nivel,
    diseño_software,
    diseño_software_nivel,
    formulacion_proyectos,
    formulacion_proyectos_nivel,
    pensamiento_cientifico,
    pensamiento_cientifico_nivel,
    periodo_academico,
    fecha_presentacion,
    estado
)
SELECT 
    a.id,
    'EK20183007722',
    200,
    'Nivel 4',
    128,
    'Nivel 2',
    182,
    'Nivel 3',
    202,
    'Nivel 4',
    206,
    'Nivel 4',
    'B1',
    183,
    'Nivel 3',
    185,
    'Nivel 3',
    160,
    'Nivel 3',
    197,
    'Nivel 4',
    '2024-2',
    '2024-06-15',
    'VALIDO'
FROM alumno a
WHERE a.numero_documento = '1098765001'
ON DUPLICATE KEY UPDATE 
    puntaje_global = 200,
    estado = 'VALIDO';

-- Verificar la inserción
SELECT 'Alumno insertado:' as resultado;
SELECT * FROM alumno WHERE numero_documento = '1098765001';

SELECT 'Resultado insertado:' as resultado;
SELECT r.* FROM resultado r 
JOIN alumno a ON r.alumno_id = a.id 
WHERE a.numero_documento = '1098765001';
