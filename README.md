# AirportAPP

## Anselmi-Onetto-Heredero

Proyecto de la materia Laboratorio Tic en el año 2023 que consiste en realizar una aplicación de gestión de aeropuertos.

## Requisitos Previos

- Java 11 (o versión superior)
- Maven

## Configuración del Proyecto

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/joaco0o0/Proyecto-TIC.git
   cd Proyecto-Tic

   Si no tinenes experiencia sigue ls siguentes pasos:
   Abre la Consola o Terminal:

En Windows, puedes usar el "Símbolo del sistema" o "PowerShell".
En macOS y Linux, usa la Terminal.
Navega al Directorio de Destino:

Utiliza el comando cd para cambiar al directorio donde deseas almacenar la copia del repositorio.

bash
Copy code
cd ruta/de/tu/directorio
Clona el Repositorio:

Ejecuta el siguiente comando para clonar el repositorio.

bash
Copy code
git clone https://github.com/joaco0o0/Proyecto-TIC.git
Esto descargará una copia completa del repositorio en tu máquina.

Entra al Directorio Clonado:

Utiliza el comando cd para ingresar al directorio recién clon
   
Configuración de la Base de Datos:

Dependiendo de si desea utilizar una base de datos local o en la nube, haga lo siguiente:

Configure la base de datos en el archivo application.yml. Por defecto, aparecerá la versión en la nube con la URL: jdbc:postgresql://34.86.77.107:5432/Proyecto-TIC-I, username: postgres y password: proyectotic1.
Si desea cambiar a una base local, simplemente cambie la URL a: jdbc:postgresql://localhost:5432/nombredelabasededatos, y modifique el nombre de usuario y la contraseña según sea necesario. Por defecto, suelen ser postgres y la contraseña suele ser vacía, aunque es recomendable cambiarla si es posible.
Si ha perdido o olvidado su usuario y contraseña de PostgreSQL, puede seguir este video tutorial que muestra el proceso de recuperación.
Iniciar la Aplicación:

Abre una terminal en la ubicación del proyecto y ejecuta el siguiente comando:
bash
Copy code
mvn spring-boot:run
(Si utilizas IntelliJ, también puedes ejecutarlo desde allí).
Visualización de la Base de Datos
Bases de Datos Locales
Si has configurado la aplicación para utilizar una base de datos local, puedes usar herramientas como DBeaver para visualizarla. Ingresa las credenciales de la base de datos y la URL que configuraste en application.yml.

Bases de Datos en la Nube (Google Cloud)
Ingresar a Google Cloud:

Accede a Google Cloud Console.
Inicia sesión con tu cuenta de Google.
Crear una Nueva Base de Datos:

Ve a la sección "Cloud SQL" desde el menú de Google Cloud Console.
Haz clic en "Create Instance".
Sigue los pasos para configurar tu instancia de Cloud SQL. Puedes elegir PostgreSQL como el motor de base de datos.
Conectar con DBeaver:

Descarga e instala DBeaver.
Crea una nueva conexión PostgreSQL en DBeaver.
Utiliza las credenciales proporcionadas al configurar tu instancia de Cloud SQL como la URL, nombre de usuario y contraseña.
