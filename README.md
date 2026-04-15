Sistema de venta de videojuegos

Repositorio del proyecto, desarrollado en Java con Spring Boot y conectado a una base de datos MariaDB. El sistema incluye un panel principal (Dashboard) donde se pueden ver las métricas del proyecto.
Requisitos para correr el proyecto

    Java 17 o superior.

    IntelliJ IDEA.

    MariaDB.

Pasos para probar el sistema

    Preparar la base de datos:
    Abre tu cliente de base de datos (como DBeaver o la consola) y ejecuta todo el código que está en el archivo VideojuegosM.sql. Esto va a crear la base de datos (ventas_videojuegos) y las tablas necesarias con los datos de prueba.

    Revisar la conexión:
    En el proyecto, abre el archivo application.properties que está en la carpeta de recursos. Revisa que el usuario de la base de datos sea "admin" y la contraseña "admin123". Si en tu equipo usas otras credenciales o la base de datos quedó con otro nombre, actualiza ese archivo para que se pueda conectar sin problemas.

    Abrir en IntelliJ:
    Abre IntelliJ IDEA, selecciona Abrir y escoge la carpeta del proyecto. Deja que el programa descargue las dependencias de Maven automáticamente.

    Arrancar la aplicación:
    Busca el archivo SistemadeVideojuegosApplication.java y córrelo. Cuando la consola indique que ya inició, abre tu navegador y entra a la página principal en: http://localhost:8080/login
    Para ingresar al sistema, puedes iniciar sesión con el usuario "hazel" y la contraseña "12345".

¿Dónde están las métricas implementadas?

Para la evaluación del proyecto, las métricas se integraron directo en el controlador del panel principal. 

Métricas calculadas en ese archivo:

    Complejidad Ciclomática: Se declararon las variables reales para las diferentes secciones (complejidadVentas, complejidadUsuarios, complejidadClientes, complejidadVideojuegos y complejidadConsolas). El código suma estos valores y calcula el promedio general (complejidadPromedio) para tener una medida de qué tan mantenible es el código de los controladores.

    Trabajo en Progreso (WIP): Se agregó la variable wipActual que funciona como nuestro KPI principal. Esto nos ayuda a aplicar conceptos Kanban midiendo las tareas en progreso de forma directa.

Una vez que inicies sesión en el login, solo hay que ir a la vista del Dashboard y ahí se mostrarán gráficamente estos indicadores con los datos que manda el controlador.
