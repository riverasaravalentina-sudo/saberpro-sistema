-- ===============================
-- LIMPIEZA COMPLETA DE RAILWAY
-- ===============================
-- Ejecuta esto en la consola de Railway (botón Connect)

-- 1. Eliminar TODO
DELETE FROM resultados;
DELETE FROM alumnos;

-- 2. Reiniciar AUTO_INCREMENT
ALTER TABLE alumnos AUTO_INCREMENT = 1;
ALTER TABLE resultados AUTO_INCREMENT = 1;

-- 3. Verificar que quedó vacío
SELECT COUNT(*) as alumnos_restantes FROM alumnos;
SELECT COUNT(*) as resultados_restantes FROM resultados;

-- ===============================
-- Mensaje de confirmación
-- ===============================
SELECT 'Base de datos limpia. Ahora ejecuta database_railway.sql completo' AS mensaje;
