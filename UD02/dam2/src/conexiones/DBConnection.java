package conexiones;

import java.sql.Connection;

/**
 * Interfaz que unifica la forma de obtener las conexiones de los diferentes conectores.
 * <p>
 * Las implementaciones deben proporcionar al menos dos tipos de conexión:
 * <ul>
 *   <li>Una conexión a la base de datos concreta usada por la aplicación.</li>
 *   <li>Una conexión al servidor MySQL (sin seleccionar una base de datos concreta).</li>
 * </ul>
 * </p>
 */
public interface DBConnection {

    /**
     * Devuelve una conexión a la base de datos usada por la aplicación (por ejemplo, "dam2").
     *
     * @return instancia de {@link Connection} si la conexión se estableció correctamente,
     *         o {@code null} si hubo un error al obtener la conexión.
     */
    Connection getConnection();

    /**
     * Devuelve una conexión al servidor de base de datos (sin seleccionar una base de datos concreta).
     *
     * @return instancia de {@link Connection} al servidor o {@code null} si ocurre un error.
     */
    Connection getConnectionServer();
}
