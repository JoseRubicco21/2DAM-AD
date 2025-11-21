package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexiones.MySQLConnection;

/**
 * Clase de acceso a datos que realiza operaciones CRUD sobre la tabla
 * "estudiante" usando MySQL.
 */
public class ManageStudents {

	/** Conexión MySQL usada para ejecutar sentencias. */
	private MySQLConnection connection;

	/**
	 * Crea un manejador que utiliza una nueva instancia de {@link MySQLConnection}.
	 */
	public ManageStudents() {
		this.connection = new MySQLConnection();
	}

	/**
	 * Inserta un estudiante en la base de datos.
	 *
	 * @param student estudiante a insertar
	 * @return {@code true} si se insertó al menos una fila, {@code false} en caso de
	 *         error
	 */
	public boolean addStudent(Student student) {
		try (PreparedStatement sentence = this.connection.getConnection()
				.prepareStatement("INSERT INTO estudiante (id, nombre, apellidos, edad, aprobado) VALUES (?, ?, ?, ?, ?)")) {
				sentence.setString(1, student.getId());
				sentence.setString(2, student.getNombre());
				sentence.setString(3, student.getApellido());
				sentence.setInt(4, student.getEdad());
				sentence.setString(5, student.getAprobado());
				int rows = sentence.executeUpdate();
				return rows > 0;
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;    
		}
	}

	/**
	 * Obtiene un estudiante por su identificador.
	 *
	 * @param id identificador del estudiante
	 * @return {@link Student} con los datos encontrados, o {@code null} si hay
	 *         error. Si no existe el estudiante, se devuelve un {@link Student}
	 *         vacío (sin campos) según la implementación actual.
	 */
	public Student getStudent(String id) {
		try (PreparedStatement query = this.connection.getConnection()
				.prepareStatement("SELECT * FROM estudiante WHERE id = ?")) {
			query.setString(1, id);
			ResultSet result = query.executeQuery();
			Student student = new Student();
			while (result.next()) {
				student.setId(result.getString("id"));
				student.setNombre(result.getString("nombre"));
				student.setApellido(result.getString("apellidos"));
				student.setEdad(result.getInt("edad"));
				student.setAprobado(result.getString("aprobado"));
			}
			return student;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Elimina un estudiante por su id.
	 *
	 * @param id identificador del estudiante a eliminar
	 * @return {@code true} si se borró exactamente una fila, {@code false}
	 *         en caso de error o si no se borró ninguna fila
	 */
	public boolean deleteStudent(String id) {
		try (PreparedStatement query = this.connection.getConnection()
				.prepareStatement("DELETE FROM estudiante WHERE id = ?")) {
			query.setString(1, id);
			int deletedRow = query.executeUpdate();
			return deletedRow == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Actualiza los datos de un estudiante existente.
	 *
	 * @param student estudiante con los nuevos datos (debe contener el id)
	 * @return {@code true} si se actualizó exactamente una fila, {@code false}
	 *         en caso contrario
	 */
	public boolean modifyStudent(Student student) {
		try (PreparedStatement sentence = this.connection.getConnection()
				.prepareStatement("UPDATE estudiante SET nombre=?, apellidos=?, edad=?, aprobado=? WHERE id=?")) {
			sentence.setString(1, student.getNombre());
			sentence.setString(2, student.getApellido());
			sentence.setInt(3, student.getEdad());
			sentence.setString(4, student.getAprobado());
			sentence.setString(5, student.getId());
			int rowsUpdated = sentence.executeUpdate();
			return rowsUpdated == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Recupera todos los estudiantes de la tabla.
	 *
	 * @return lista con los estudiantes, o {@code null} si ocurre un error
	 */
	public ArrayList<Student> getStudentsList() {
		try (PreparedStatement query = this.connection.getConnection().prepareStatement("SELECT * FROM estudiante")) {
			ResultSet result = query.executeQuery();
			ArrayList<Student> students = new ArrayList<Student>();
			while (result.next()) {
				Student student = new Student();
				student.setId(result.getString("id"));
				student.setNombre(result.getString("nombre"));
				student.setApellido(result.getString("apellidos"));
				student.setEdad(result.getInt("edad"));
				student.setAprobado(result.getString("aprobado"));
				students.add(student);
			}
			return students;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
