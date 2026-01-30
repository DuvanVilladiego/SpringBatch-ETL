# Spring Batch ETL Application

## Description

This Java batch application uses Spring Batch to read data from a CSV file, perform data transformation, and store it in a database. Additionally, an ETL process is implemented that extracts data from a source database, transforms the data, and loads the transformed data into a target database.

1. **Data Reading**

   * Includes a Spring Batch Job that reads a CSV file with the following fields: `id`, `nombre`, `email`.

2. **Data Transformation**

   * A processing step is implemented that converts names to uppercase and validates emails (ensuring they have a correct format).

3. **Data Loading**

   * A step is configured to write the processed data into a database (Postgress).

4. **ETL Process**

   * Includes a Job that extracts data from a source database, transforms the name by appending "_PROCESSED" at the end, and loads the transformed data into a target database (**MongoDb**).

## Specifications

### Sample CSV File

```csv
id,nombre,email
1,Juan Perez,juan.perez@example.com
2,Ana Gomez,ana.gomez@example.com
```

### Spring Batch Configuration

1. **Job to read CSV and store data in the database**

   * **CSV Reading:** The project already includes a valid **CSV** file. If you want to replace it, simply replace the file located at `/resources/archivo.csv` or update the environment variables with the correct file name and path starting from the `/resources` directory.
   * **Processing:** The `nombre` field is converted to uppercase and the email format is validated. If it is incorrect, it is left blank.
   * **Data Loading:** The project comes configured with a **Postgres** database for writing called `batchdb`.

2. **ETL Job**

   * **Data Extraction:** Connects to the **Postgres** database used previously and handles the same data structure.
   * **Processing:** The name of each user is retrieved and the string "_PROCESS" is appended at the end.
   * **Data Loading:** Data is stored in a non-relational **MongoDb** database in the `processed_users` collection.

## Project Structure

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

## Execution

### Prerequisites

* Java 17 or higher
* Maven 3.6 or higher
* Postgres
* MongoDb

### Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/DuvanVilladiego/SpringBatch-ETL.git
   cd springbatch-etl
   ```

2. **Database configuration:**

   * Execute the `schema-postgresql` and `user-table` scripts.
   * Make sure the database configuration in `application.properties` is correct.
   * The timeout values for the ImportUser and ProcessUser Jobs are set to `10000` and `15000` milliseconds respectively.
   * It is possible to customize the CSV file path from which the data will be extracted.

   ```properties
   spring.application.name=etl-migration
   
   # Postgres configuration
   spring.datasource.url=jdbc:postgresql://localhost:5432/batchdb
   spring.datasource.username=postgres
   spring.datasource.password=root
   spring.datasource.driver-class-name=org.postgresql.Driver

   # Spring Batch configuration
   spring.batch.job.enabled=true
   spring.batch.jdbc.initialize-schema=always

   # MongoDB configuration
   spring.data.mongodb.host=localhost
   spring.data.mongodb.port=27020
   spring.data.mongodb.database=imports

   # CSV path
   csv.file='archivo.csv'
   ```

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

5. **Verify the results:**

   * For the first Job (CSV reading and database storage), the processed data will be saved in the configured database.
   * For the second Job (ETL process), make sure the source and target databases are correctly configured in `application.properties`.

### Result Verification

1. **Verify data:**

   Run SQL queries to verify the processed and stored data:

   ```sql
   SELECT * FROM users;
   SELECT * FROM processed_users;
   ```

This covers the basic configuration and execution of the Spring Batch ETL application. Make sure to adjust the details according to your needs and the specific environment you are working in.

---
