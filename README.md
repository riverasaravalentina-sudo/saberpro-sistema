# 🎓 Sistema SABER PRO - Parcial Tercer Corte

## 📋 Descripción del Proyecto

Sistema web desarrollado en **Spring Boot** para la gestión integral de estudiantes, resultados de las pruebas SABER PRO y beneficios académicos de las **Unidades Tecnológicas de Santander**.

### ✨ Características Principales

- ✅ **Gestión completa de estudiantes** (CRUD)
- ✅ **Registro y consulta de resultados SABER PRO**
- ✅ **Sistema de beneficios automático** según puntajes
- ✅ **Reportes detallados y estadísticas**
- ✅ **Sistema de autenticación con roles** (Coordinación/Estudiante)
- ✅ **Dashboard interactivo con gráficos**
- ✅ **Interfaz responsiva con Bootstrap 5**
- ✅ **Base de datos H2 (local) y PostgreSQL (producción)**

---

## 🏗️ Estructura del Proyecto

```
saberpro-system/
├── src/main/java/com/saberpro/
│   ├── SaberProApplication.java          # Clase principal
│   ├── config/
│   │   └── SecurityConfig.java           # Configuración de seguridad
│   ├── controller/                       # Controladores web
│   │   ├── HomeController.java
│   │   ├── AuthController.java
│   │   ├── AlumnoController.java
│   │   ├── ResultadoController.java
│   │   └── ReportesController.java
│   ├── entity/                          # Entidades JPA
│   │   ├── Alumno.java
│   │   ├── Resultado.java
│   │   ├── Usuario.java
│   │   └── Rol.java
│   ├── repository/                      # Repositorios JPA
│   │   ├── AlumnoRepository.java
│   │   ├── ResultadoRepository.java
│   │   ├── UsuarioRepository.java
│   │   └── RolRepository.java
│   ├── service/                         # Servicios de negocio
│   │   ├── AlumnoService.java
│   │   ├── ResultadoService.java
│   │   └── BeneficioService.java
│   └── dto/                            # DTOs para transferencia de datos
│       ├── BeneficioDTO.java
│       ├── ReporteDetalladoDTO.java
│       ├── EstadisticasDTO.java
│       └── LoginDTO.java
├── src/main/resources/
│   ├── application.properties           # Configuración local (H2)
│   ├── application-prod.properties      # Configuración producción (PostgreSQL)
│   ├── data.sql                        # Datos iniciales
│   ├── templates/                      # Vistas Thymeleaf
│   │   ├── layout/base.html
│   │   ├── auth/login.html
│   │   ├── dashboard.html
│   │   └── alumnos/list.html
│   └── static/                         # Recursos estáticos
│       ├── css/custom.css
│       └── js/main.js
└── pom.xml                            # Dependencias Maven
```

---

## 🚀 Instalación y Configuración

### 📋 Prerrequisitos

- ✅ **Java 17** o superior
- ✅ **Maven 3.8+**
- ✅ **Spring Tool Suite 4** o IntelliJ IDEA
- ✅ **Git** (opcional)

### 🔧 Pasos de Instalación

#### **1. Importar el Proyecto en Spring Tool Suite**

1. **Abrir Spring Tool Suite**
2. **File** → **Import**
3. **Maven** → **Existing Maven Projects**
4. **Browse** → Seleccionar la carpeta: 
   ```
   C:\Users\Sara Rivera\OneDrive - Unidades Tecnológicas de Santander\Escritorio\Parcial_SaberPro_Sarichi
   ```
5. **Finish**

#### **2. Ejecutar el Proyecto Localmente**

1. **Click derecho** en el proyecto
2. **Run As** → **Spring Boot App**
3. O usar el terminal:
   ```bash
   mvn spring-boot:run
   ```

#### **3. Acceder al Sistema**

- **URL:** http://localhost:8080
- **Console H2:** http://localhost:8080/h2-console
  - **JDBC URL:** `jdbc:h2:mem:saberpro_db`
  - **Usuario:** `sa`
  - **Contraseña:** *(vacía)*

---

## 👥 Usuarios de Prueba

### 🔐 Credenciales Predefinidas

| Rol | Usuario | Contraseña | Descripción |
|-----|---------|------------|-------------|
| **Coordinación** | `admin` | `admin123` | Acceso completo al sistema |
| **Coordinación** | `coordinador` | `admin123` | Coordinador académico |
| **Estudiante** | `estudiante1` | `estudiante123` | Vista limitada de estudiante |

---

## 🎯 Funcionalidades por Rol

### 👩‍💼 **Coordinación** (CRUD Completo)
- ✅ Gestión completa de alumnos
- ✅ Registro de resultados SABER PRO
- ✅ Generación de reportes
- ✅ Visualización de beneficios
- ✅ Estadísticas del sistema
- ✅ Dashboard con métricas

### 👨‍🎓 **Estudiante** (Solo Consulta)
- ✅ Ver su resultado SABER PRO
- ✅ Consultar sus beneficios
- ✅ Actualizar perfil personal

---

## 📊 Sistema de Beneficios SABER PRO

| Puntaje Global | Nivel | Beneficio |
|---------------|--------|-----------|
| **< 80** | Insuficiente | ❌ NO PUEDE GRADUARSE |
| **80 - 150** | Mínimo | 🟡 Exoneración parcial (25%) |
| **151 - 170** | Satisfactorio | 🟢 Exoneración total + 50% grado |
| **> 170** | Avanzado | 🔵 Exoneración total + 100% grado |

---

## 📈 Reportes Disponibles

1. **📋 Reporte de Beneficios**
   - Lista completa de estudiantes con sus beneficios
   - Filtro por programa académico
   - Estadísticas de beneficios

2. **📊 Estadísticas Generales**
   - Distribución por nivel de desempeño
   - Promedios por módulo
   - Gráficos interactivos

3. **📑 Reporte Detallado**
   - Análisis por programa académico
   - Métricas específicas
   - Comparativas

4. **⚠️ Alumnos que NO pueden graduarse**
   - Lista de estudiantes con puntaje < 80
   - Requieren volver a presentar

---

## 🌐 Deploy en Render (Producción)

### **Preparación para Deploy**

1. **Crear repositorio GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit - Sistema SABER PRO"
   git branch -M main
   git remote add origin https://github.com/tu-usuario/saberpro-sistema.git
   git push -u origin main
   ```

2. **Configurar Render.com:**
   - Crear cuenta en [render.com](https://render.com)
   - **New** → **Web Service**
   - Conectar repositorio GitHub
   - **Environment:** `Java`
   - **Build Command:** `mvn clean install -DskipTests`
   - **Start Command:** `java -jar target/saberpro-system-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`

3. **Variables de Entorno en Render:**
   ```
   SPRING_PROFILES_ACTIVE=prod
   DATABASE_URL=jdbc:postgresql://your-db-host:5432/saberpro_db
   DATABASE_USERNAME=your_username
   DATABASE_PASSWORD=your_password
   ```

4. **Crear Base de Datos PostgreSQL:**
   - En Render: **New** → **PostgreSQL**
   - Copiar la URL de conexión
   - Configurar variables de entorno

---

## 🛠️ Tecnologías Utilizadas

### **Backend**
- ☕ **Java 17**
- 🍃 **Spring Boot 3.2.0**
- 🔒 **Spring Security 6**
- 🗃️ **Spring Data JPA**
- 🏛️ **Hibernate**

### **Frontend**
- 🎨 **Thymeleaf**
- 🎨 **Bootstrap 5.3**
- ⭐ **Font Awesome 6**
- 📊 **Chart.js**
- 💻 **JavaScript ES6+**

### **Base de Datos**
- 🗄️ **H2 Database** (Desarrollo)
- 🐘 **PostgreSQL** (Producción)

### **Herramientas**
- 📦 **Maven**
- 🔧 **Spring Tool Suite**
- ☁️ **Render** (Deploy)

---

## 📝 Datos de Ejemplo

El sistema incluye **datos de prueba** automáticamente:

### **👥 Estudiantes de Ejemplo**
- Ana María González - Ingeniería de Sistemas
- Carlos Eduardo Martínez - Ingeniería Industrial
- María Fernanda Sánchez - Administración de Empresas
- Diego Alejandro Ramírez - Ingeniería Civil
- Laura Cristina Herrera - Contaduría Pública

### **📊 Resultados Variados**
- Diferentes niveles de desempeño
- Múltiples programas académicos
- Diversos beneficios

---

## ✅ Testing y Validación

### **🧪 Pruebas Manuales**

1. **Autenticación:**
   - ✅ Login con credenciales válidas
   - ✅ Redirección según rol
   - ✅ Logout correcto

2. **CRUD Alumnos:**
   - ✅ Crear nuevo alumno
   - ✅ Editar información
   - ✅ Eliminar (validaciones)
   - ✅ Buscar y filtrar

3. **Gestión Resultados:**
   - ✅ Registrar resultados
   - ✅ Validar puntajes
   - ✅ Calcular beneficios

4. **Reportes:**
   - ✅ Generar todos los reportes
   - ✅ Filtros funcionales
   - ✅ Estadísticas correctas

---

## 🚨 Troubleshooting

### **Problemas Comunes**

1. **Puerto 8080 ocupado:**
   ```properties
   server.port=8081
   ```

2. **Error de base de datos:**
   - Verificar configuración en `application.properties`
   - Revisar console H2: http://localhost:8080/h2-console

3. **Errores de compilación:**
   ```bash
   mvn clean install -U
   ```

4. **Problemas de memoria:**
   ```bash
   export MAVEN_OPTS="-Xmx1024m"
   ```

---

## 📞 Soporte y Contacto

### **👨‍💻 Información del Desarrollador**
- **Proyecto:** Sistema SABER PRO - Parcial Tercer Corte
- **Institución:** Unidades Tecnológicas de Santander
- **Fecha:** Noviembre 2024

### **📖 Documentación Adicional**
- **Spring Boot:** https://spring.io/projects/spring-boot
- **Thymeleaf:** https://www.thymeleaf.org/
- **Bootstrap:** https://getbootstrap.com/

---

## 🎉 ¡Listo para Usar!

El proyecto está **completamente configurado** y listo para:

1. ✅ **Importar en Spring Tool Suite**
2. ✅ **Ejecutar localmente**
3. ✅ **Probar todas las funcionalidades**
4. ✅ **Desplegar en Render**

**¡El sistema está funcionando perfectamente! 🚀**