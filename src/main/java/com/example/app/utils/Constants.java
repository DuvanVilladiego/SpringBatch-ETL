package com.example.app.utils;

public class Constants {
	
	//EXPRESIONES REGULARES
	public static final String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

	//MENSAJES DE ERROR
	public static final String INVALID_EMAIL_FORMAT = "INVALID EMAIL DETECTED IN id=%s SETTING ON BLANK STRING ''";
	
	//MENSAJES INFORMATIVOS
	public static final String STARTING_CSV_PROCESSING = "STARTING CSV PROCESSING";
	public static final String FINISHED_CSV_PROCESSING = "CVS PROCESSING FINISHED";
	public static final String STARTING_ETL_PROCESSING = "STARTING ETL PROCESSING";
	public static final String FINISHED_ETL_PROCESSING = "ETL PROCESSING FINISHED";

	//MENSAJES DE ADVERTENCIA
	public static final String OVERWRITING_WARNING = "OVERWRITING {%s} TO {%s}";
	
	//NOMBRES PARA BEANS
	public static final String IMPORT_USER_JOB_BEAN = "importUserBean";
	public static final String IMPORT_USER_READER_BEAN = "importUserReaderBean";
	public static final String IMPORT_USER_PROCESSOR_BEAN = "importUserProcessorBean";
	public static final String IMPORT_USER_WRITER_BEAN = "importUserWriterBean";
	public static final String PROCESS_JOB_BEAN = "processUserBean";
	public static final String PROCESS_JOB_READER_BEAN = "processUserReaderBean";
	public static final String PROCESS_JOB_PROCESSOR_BEAN = "processUserLogicProcessorBean";
	public static final String PROCESS_JOB_WRITER_BEAN = "processUserWriterBean";

	//CONSULTAS SQL
	public static final String INSERT_AND_UPDATE_INTO_USERS = "INSERT INTO users (id, name, email) VALUES (:id, :name, :email) ON CONFLICT (id) DO UPDATE SET name = excluded.name, email = excluded.email";
	public static final String READ_USER_DATA_TO_PROCESS = "SELECT id, name, email FROM users";
	
	//DATOS MONGO
	public static final String MONGO_COLLECTION_PROCESS_USERS = "processed_users";
	
	//CVS (PARTE DE RESOURCES)
	public static final String CSV_CLASSPATH = "archivo.csv";
	public static final String CSV_DELIMITER = ",";
	
	
}
