# MyCliniK
## _Software de gestión de clínicas_

**MyCliniK** pretende facilitar la gestión de todo tipo de clínicas privadas (estética, dental, etc.).
La idea es dar una solución a diversas tareas que tienen los trabajadores/dueños de estos centros, como son mantener una agenda de citas, tener un historial de pacientes o facilitar la facturación.
También pretende automatizar procesos como la creación de documentos de consentimiento para imprimir y firmar.

La idea principal se basa en crear un punto central de control de la clínica, un lugar en el que el trabajador/dueño pueda tener de forma clara y controlada todos los datos de su clínica, facilitando la gestión de ésta.

## Funcionalidad del servicio

Algunas de las funcionalidades más relevantes que se proponen son las siguientes:

- **Calendario**: Con diversas vistas (mensual, semanal y diario) podremos observar las citas, para tener todo preparado previamente, y las horas libres en las que podemos asignar nuevas citas. Estas citas tendrán ligadas el perfil del paciente, el profesional que se encargará y los datos más relevantes del servicio adquirido por éste. La creación de citas recurrentes serán automatizadas, agilizando el proceso. También podremos filtrar los eventos a mostrar, pudiendo ver citas de un paciente en concreto, eventos de gestión (gestiones como acudir al banco, reuniones con proveedores, etc.).
- **Pacientes**: Registro de pacientes del centro. Incluye datos personales (nombre, apellidos, número de contacto, e-mail, etc.), citas pasadas y próximas y otra información relevante.
- **Contabilidad**: Se trata de un registro de la contabilidad de la clínica, incluyendo compras de material, pagos a trabajadores, cobros de clientes, etc. Además podremos filtrar por el estado, fecha o tipo. Adicionalmente se mostrarán estadísticas para facilitar la visualización del estado económico del centro y se permitirá la descarga en formato Excel para una inspección más avanzada.
- **Documentos**: Documentos de consentimiento que se generan automáticamente con los datos del cliente y se imprimen para la firma de éste.

Por el carácter de los datos almacenados, será de vital importancia asegurar el cumplimiento de la Ley Orgánica de Protección de Datos.

## Propuesta de realización
La interfaz de usuario se implementará para navegadores Web convencionales.
Adicionalmente, se plantea la realización de una aplicación móvil más simple que permita la visualización de datos que requieren visualización con mayor asiduidad, como la agenda.

La primera versión de la aplicación se desarrollará con el framework de Java Spring Boot, desarrollando el frontend con la biblioteca de JavaScript React.js.
Se usarán las plantillas de Thymeleaf para conectar los componentes de React con Spring Boot.
La persistencia de datos se desarrollará usando Java Persistence API (JPA) con Hibernate sobre base de datos.

El equipo seguirá una metodología ágil de desarrollo basada en Scrum para desarrollar un prototipo básico con las características de Mínimo Producto Viable (MPV) que permita demostrar la funcionalidad del servicio para un escenario típico de uso, la viabilidad del enfoque y la adecuación de la arquitectura software elegida.
El desarrollo se realizará mediante git, concretamente en este repositorio, con el fin de mantener un sistema de versiones y poder dividir por equipos el desarrollo de diversas funcionalidades de manera paralela (mediante diferentes ramas).
La aplicación que se plantea es un sistema con típicos requisitos de seguridad como autenticación, autorización, confidencialidad, integridad de datos etc.
La plataforma tecnológica y el modelo de negocio del sistema deben permitir el cumplimiento de la Directiva Europea sobre protección de datos de carácter personal.

## Development
### Dependencias
- JDK
- Maven
- Spring Tool Suite
- PostgreSQL

### Set up
- Cree una base de datos llamada `myclinik`
- Si se tiene el fichero `.env` cargue las variables de entorno de éste, si no, cree las siguientes variables:
	- `SPRING_MYCLINIK_DB_USERNAME` con el usuario de la base de datos `myclinik`.
	- `SPRING_MYCLINIK_DB_PASSWORD` con la contraseña de ese usuario.
	- `SPRING_MAIL_USERNAME` (opcional) con la dirección de correo electrónico.
	- `SPRING_MAIL_PASSWORD` (opcional) con la contraseña del correo.

### Arrancar entorno
- Arrancar PostgreSQL
- Ejecutar: `./mvnw spring-boot:run`
