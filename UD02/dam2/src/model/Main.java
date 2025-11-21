package model;

import conexiones.MySQLConnection;

/**
 * Punto de entrada de la aplicación.
 * <p>
 * Contiene un método de prueba de conexión y el método main que inicia la
 * aplicación gráfica.</p>
 */
public class Main {

	/**
	 * Prueba simple que intenta obtener una conexión y escribe el resultado por
	 * consola.
	 */
	public static void testConnection() {
		MySQLConnection connection = new MySQLConnection();
		if (connection.getConnection() != null) {
			System.out.println("Conexión a la base de datos realizada con éxito.");
		} else {
			System.out.println("No se ha podido conectar a la base de datos.");
		}
	}

	/**
	 * Inicializa la aplicación: prueba la conexión y arranca la UI de estudiantes.
	 *
	 * @param args argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		testConnection();
		AppStudents app = new AppStudents();
		app.showAllStudents();
	}
}
