package model;

import java.util.ArrayList;

import gui.StudentsView;

/**
 * Controlador principal de la aplicación.
 * <p>
 * Orquesta las operaciones del gestor de datos {@link ManageStudents} y la
 * vista {@link StudentsView}.</p>
 */
public class AppStudents {

	/** Gestor de persistencia para estudiantes. */
	private ManageStudents manager;

	/** Vista de la aplicación donde se muestran los estudiantes. */
	private StudentsView view;

	/**
	 * Inicializa la aplicación creando el gestor y la vista y mostrando la UI.
	 */
	public AppStudents() {
		this.manager = new ManageStudents();
		this.view = new StudentsView(this);
		this.view.setVisible(true);
	}

	/**
	 * Inserta un nuevo estudiante y actualiza la vista en caso de éxito.
	 *
	 * @param id identificador del estudiante
	 * @param name nombre
	 * @param surname apellidos
	 * @param age edad
	 * @param hasPassed cadena indicando si ha aprobado
	 */
	public void enrollStudent(String id, String name, String surname, int age, String hasPassed) {
		Student student = new Student(id, name, surname, age, hasPassed);
		boolean inserted = manager.addStudent(student);
		if (inserted) {
			view.showMessage("ESTUDIANTE MATRICULADO CORRECTAMENTE.");
			view.clear();
			view.addStudent(id, name, surname, age, hasPassed);

		} else {
			view.showMessage("NO SE HA PODIDO MATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Elimina un estudiante por id y refresca la vista si la operación fue
	 * correcta.
	 *
	 * @param id identificador del estudiante a eliminar
	 */
	public void dropStudent(String id) {
		boolean deleted = manager.deleteStudent(id);
		if (deleted) {
			view.showMessage("SE HA BORRADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO DESMATRICULAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Actualiza los datos de un estudiante y refresca la vista si se pudo
	 * actualizar.
	 *
	 * @param id identificador
	 * @param name nombre
	 * @param surname apellidos
	 * @param age edad
	 * @param hasPassed aprobado
	 */
	public void updateStudent(String id, String name, String surname, int age, String hasPassed) {
		Student student = new Student(id, name, surname, age, hasPassed);
		boolean modified = manager.modifyStudent(student);
		if (modified) {
			view.showMessage("SE HA ACTUALIZADO CON ÉXITO AL ESTUDIANTE.");
			view.refresh();
		} else {
			view.showMessage("NO SE HA PODIDO ACTUALIZAR AL ESTUDIANTE.");
		}
	}

	/**
	 * Solicita la lista completa de estudiantes al gestor y los envía a la vista.
	 */
	public void showAllStudents() {
		ArrayList<Student> students = manager.getStudentsList();
		for (Student student : students) {
			view.addStudent(student.getId(), student.getNombre(), student.getApellido(), student.getEdad(), student.getAprobado());
		}
	}
}
