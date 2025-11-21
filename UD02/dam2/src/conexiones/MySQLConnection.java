package conexiones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación de {@link DBConnection} para MySQL.
 * <p>
 * Proporciona conexiones tanto a la base de datos concreta usada por la
 * aplicación como al servidor MySQL (sin base de datos seleccionada).
 * </p>
 */
public class MySQLConnection implements DBConnection {

    /** URL base para conectar al servidor MySQL (sin DB seleccionada). */
    private String URLserver = "jdbc:mysql://localhost:3306/";

    /** URL completa para conectar a la base de datos 'dam2'. */
    private String URL = "jdbc:mysql://localhost:3306/dam2";

    /** Usuario por defecto para la conexión. */
    private String USER = "root";

    /** Contraseña por defecto para la conexión. */
    private String PASSWORD = "root";

    /**
     * Obtiene una conexión a la base de datos usada por la aplicación.
     *
     * @return {@link Connection} conectado a la base de datos 'dam2', o {@code null}
     *         si ocurre un error al establecer la conexión.
     */
    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a Mysql coa Base de datos: dam2" + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene una conexión al servidor MySQL sin seleccionar una base de datos.
     *
     * @return {@link Connection} al servidor MySQL, o {@code null} si ocurre un error.
     */
    @Override
    public Connection getConnectionServer() {

        try {
            return DriverManager.getConnection(URLserver, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR conectando a MySql: " + e.getMessage());
            return null;
        }
    }
}
