# AirportAPP

## Anselmi-Onetto-Heredero

Proyecto de la materia Laboratorio Tic en el año 2023 que consiste en realizar una aplicación de gestión de aeropuertos.

## Requisitos Previos

- Java 11 (o versión superior)
- Maven

## Configuración del Proyecto

1. Clonar el Repositorio y Configuración del Proyecto
Clonar el Repositorio:

-Abre la Consola o Terminal. En Windows, puedes usar el "Símbolo del sistema" o "PowerShell". En macOS y Linux, usa la Terminal.

-Navega al Directorio de Destino usando el comando cd para cambiar al directorio donde deseas almacenar la copia del repositorio.

      cd ruta/de/tu/directorio

-Clona el repositorio ejecutando el siguiente comando:

      git clone https://github.com/joaco0o0/Proyecto-TIC.git
Esto descargará una copia completa del repositorio en tu máquina.

-Entrar al Directorio Clonado:

-Utiliza el comando cd para ingresar al directorio recién clonado.

   cd Proyecto-TIC
# Configuración del Proyecto
## Configuración de la Base de Datos:

Dependiendo de si desea utilizar una base de datos local o en la nube, realiza lo siguiente:

Configura la base de datos en el archivo application.yml. Por defecto, aparecerá la versión en la nube con la URL:
jdbc:postgresql://34.86.77.107:5432/Proyecto-TIC-I
Usuario: postgres, Contraseña: proyectotic1.

Si deseas cambiar a una base local, modifica la URL a:

jdbc:postgresql://localhost:5432/nombredelabasededatos
Modifica el nombre de usuario y la contraseña según sea necesario. Por defecto, suelen ser postgres y la contraseña suele ser vacía, aunque es recomendable cambiarla si es posible.

# Iniciar la Aplicación:

Abre una terminal en la ubicación del proyecto y ejecuta el siguiente comando:

mvn spring-boot:run
Si utilizas IntelliJ, también puedes ejecutarlo desde allí.

# Visualización de la Base de Datos
## Bases de Datos Locales:

Si has configurado la aplicación para utilizar una base de datos local, puedes usar herramientas como DBeaver para visualizarla. Ingresa las credenciales de la base de datos y la URL que configuraste en application.yml.
Bases de Datos en la Nube (Google Cloud):

## Ingresar a Google Cloud Console.
Accede a la sección "Cloud SQL" desde el menú de Google Cloud Console.
Haz clic en "Create Instance" y sigue los pasos para configurar tu instancia de Cloud SQL. Puedes elegir PostgreSQL como el motor de base de datos.
Conectar con DBeaver: Descarga e instala DBeaver. Crea una nueva conexión PostgreSQL en DBeaver utilizando las credenciales proporcionadas al configurar tu instancia de Cloud SQL como la URL, nombre de usuario y contraseña.

## Conectar con DBeaver:

Descarga e instala DBeaver.
Crea una nueva conexión PostgreSQL en DBeaver.
Utiliza las credenciales proporcionadas al configurar tu instancia de Cloud SQL como la URL, nombre de usuario y contraseña.
