# Spring Batch ETL Application

## Descripción

Esta aplicación batch en Java utiliza Spring Batch para leer datos de un archivo CSV, realizar una transformación de datos y almacenarlos en una base de datos. Adicionalmente, se implementa un proceso ETL que extrae datos de una base de datos fuente, transforma los datos, y carga los datos transformados en otra base de datos destino.

## Requisitos

1. **Lectura de Datos**
   - Cuenta con un Job en Spring Batch que lee un archivo CSV con los siguientes campos: `id`, `nombre`, `email`.

2. **Transformación de Datos**
   - Se implementa un paso de procesamiento que convierta los nombres a mayúsculas y valida los emails (asegurándose de que tengan un formato correcto).

3. **Carga de Datos**
   - Se configura un paso que escribe los datos procesados en una base de datos (Postgress).

4. **Proceso ETL**
   - Cuenta con un Job que extre datos de una base de datos fuente, transforma el nombre agregando "_PROCESSED" al final, y carga los datos transformados en otra base de datos destino (**MongoDb**).

## Especificaciones

### Archivo CSV de Ejemplo

```csv
id,nombre,email
1,Juan Perez,juan.perez@example.com
2,Ana Gomez,ana.gomez@example.com
```

### Configuración de Spring Batch

1. **Job para leer CSV y almacenar en base de datos**

    - **Lectura del CSV:** El proyecto ya cuenta con un **CSV** valido, en caso de querer reemplazarlo simplemente reemplace el que se encuentra en la ruta `/resources/archivo.csv` o actualice las variables de entorno con el nombre y ruta correctas partiendo del directorio `/resources`.
    - **Procesamiento:** Se convierte el campo `nombre` a MAYUSCULAS y se valida el formato del correo, en caso de ser incorrecto se deja en blanco.
    - **Carga de datos:** El proyecto viene configurado con una base de datos en **Postgres** para escritura llamada `batchdb`.

2. **Job para proceso ETL**

    - **Extracción de datos:** Se conecta a la base de datos **Postgres** utilizada anteriormente y se maneja la misma estructura de datos.
    - **Procesamiento:** Se obtiene el nombre de cada usuario y se agrega la palabra "_PROCESS" al final de cada uno.
    - **Carga de datos:** Se almacenan en una base de datos no relacional **MongoDb** dentro de la coleccion `processed_users`.

## Estructura del Proyecto

```plaintext
src
├── main
│   ├── java
│   │   └── com.example.app
│   │       ├── config
│   │       │   └── BatchConfig.java
│   │       ├── job
│   │       │   └── ImportUserJobConfig.java
│   │       │   └── ProcessUserJobConfig.java
│   │       ├── processor
│   │       │   └── ImportUserProcessor.java
│   │       │   └── ProcessUserProcessor.java
│   │       ├── reader
│   │       │   └── ImportUserReader.java
│   │       │   └── ProcessUserReader.java
│   │       ├── writer
│   │       │   └── ImportUserWriter.java
│   │       │   └── ProcessUserWriter.java
│   │       ├── model
│   │       │   └── User.java
│   │       ├── utils
│   │       │   └── Constants.java
│   │       │   └── Methods.java
│   │       │   └── UserFieldSetMapper.java
│   │       └── EtlMigrationApplication.java
│   ├── resources
│   │   └── archivo.csv
│   │   └── application.properties
└── test
    └── java
        └── com.example.app
            └── EtlMigrationApplicationTests.java
```

## Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Instrucciones

1. **Clonar el repositorio:**

    ```bash
    git clone https://github.com/DuvanVilladiego/SpringBatch-ETL.git
    cd springbatch-etl
    ```

2. **Configuraciones la base de datos:**

  - Asegúrate de que la configuración de la base de datos en `application.properties` sea correcta.
  - Es posible personalizar los tiempos de espera de cada Job ya que trabajan de forma independiente, los valroes estan en `milisegundos`.
  - Es posible personalizar el ruta del archivo cvs desde donde se extraeran los datos.

    ```properties
    spring.application.name=etl-migration

    # Configuración de la base de datos
    spring.datasource.url=jdbc:postgresql://localhost:5432/batchdb
    spring.datasource.username=postgres
    spring.datasource.password=root
    spring.datasource.driver-class-name=org.postgresql.Driver
    
    # Configuración de Spring Batch
    spring.batch.job.enabled=true
    spring.batch.jdbc.initialize-schema=always
    
    # Configuración de MongoDB
    spring.data.mongodb.host=localhost
    spring.data.mongodb.port=27020
    spring.data.mongodb.database=imports
    
    # Tiempos de espera de Jobs en ms
    import.fixedrate=10000
    etl.fixedrate=15000
    
    # Ruta de archivo csv
    csv.file='archivo.csv'
    ```

4. **Construir el proyecto:**

    ```bash
    mvn clean install
    ```

5. **Ejecutar la aplicación:**

    ```bash
    mvn spring-boot:run
    ```

6. **Verificar los resultados:**

    - Para el primer Job (lectura de CSV y almacenamiento en base de datos), los datos procesados se guardarán en la base de datos configurada.
    - Para el segundo Job (proceso ETL), asegúrate de tener la base de datos fuente y destino configuradas correctamente en `application.properties`.

### Comprobación de resultados

1. **Verificar datos:**

    Ejecuta consultas SQL para verificar los datos procesados y almacenados:

    ```sql
    SELECT * FROM users;
    SELECT * FROM processed_users;
    ```
Esto cubre la configuración y ejecución básica de la aplicación Spring Batch ETL. Asegúrate de ajustar los detalles según tus necesidades y el entorno específico en el que estés trabajando.
