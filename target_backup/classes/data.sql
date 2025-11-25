-- ===============================
-- DATOS INICIALES SABER PRO
-- ===============================

-- ===============================
-- CONFIGURACIÓN DE USUARIOS EN MEMORIA
-- Los usuarios están configurados en SecurityConfig.java:
-- admin / admin123 (COORDINACION)
-- coordinador / admin123 (COORDINACION)  
-- estudiante1 / estudiante123 (ESTUDIANTE)
-- ===============================

-- Insertar programas académicos de ejemplo
INSERT INTO alumnos (documento, nombres, apellidos, programa, correo) VALUES 
('1234567890', 'Ana María', 'González Rodríguez', 'Ingeniería de Sistemas', 'ana.gonzalez@uts.edu.co'),
('0987654321', 'Carlos Eduardo', 'Martínez López', 'Ingeniería Industrial', 'carlos.martinez@uts.edu.co'),
('1122334455', 'María Fernanda', 'Sánchez Torres', 'Administración de Empresas', 'maria.sanchez@uts.edu.co'),
('2233445566', 'Diego Alejandro', 'Ramírez Castro', 'Ingeniería Civil', 'diego.ramirez@uts.edu.co'),
('3344556677', 'Laura Cristina', 'Herrera Gómez', 'Contaduría Pública', 'laura.herrera@uts.edu.co'),
('4455667788', 'Andrés Felipe', 'Morales Vargas', 'Ingeniería de Sistemas', 'andres.morales@uts.edu.co'),
('5566778899', 'Natalia', 'Rojas Mendoza', 'Psicología', 'natalia.rojas@uts.edu.co'),
('6677889900', 'Sebastian', 'Díaz Peña', 'Ingeniería Mecánica', 'sebastian.diaz@uts.edu.co'),
('7788990011', 'Valentina', 'Cruz Jiménez', 'Derecho', 'valentina.cruz@uts.edu.co'),
('8899001122', 'Ricardo', 'Vega Muñoz', 'Ingeniería Industrial', 'ricardo.vega@uts.edu.co');

-- Insertar resultados SABER PRO (variados para mostrar diferentes niveles)
INSERT INTO resultados (puntaje_global, modulo_lectura, modulo_razonamiento, modulo_ingles, fecha_presentacion, periodo_academico, alumno_id) VALUES 
(185, 87, 89, 92, '2024-03-15', '2024-1', 1),  -- Avanzado
(165, 78, 82, 85, '2024-03-15', '2024-1', 2),  -- Satisfactorio  
(145, 72, 73, 70, '2024-03-15', '2024-1', 3),  -- Mínimo
(195, 95, 88, 93, '2024-03-15', '2024-1', 4),  -- Avanzado
(125, 65, 68, 62, '2024-03-15', '2024-1', 5),  -- Mínimo
(175, 85, 87, 88, '2024-03-15', '2024-1', 6),  -- Avanzado
(75, 45, 48, 52, '2024-03-15', '2024-1', 7),   -- Insuficiente (No puede graduarse)
(158, 76, 79, 81, '2024-03-15', '2024-1', 8);  -- Satisfactorio

-- Los alumnos 9 y 10 no tienen resultados aún (deben presentar SABER PRO)

-- Actualizar periodo académico para algunos
UPDATE resultados SET periodo_academico = '2024-2' WHERE id IN (7, 8);