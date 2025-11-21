package model;

/**
 * Representa un estudiante con sus datos básicos.
 */
public class Student {

	/** Identificador único del estudiante. */
	private String id;

	/** Nombre propio del estudiante. */
	private String name;

	/** Apellidos del estudiante. */
	private String surname;

	/** Edad del estudiante. */
	private int age;

	/** Indicador textual si ha aprobado (por ejemplo "sí"/"no"). */
	private String hasPassed;

	/**
	 * Construye un estudiante con todos los campos.
	 *
	 * @param id identificador único
	 * @param name nombre
	 * @param surname apellidos
	 * @param age edad
	 * @param hasPassed texto que indica si ha aprobado
	 */
	public Student(String id, String name, String surname, int age, String hasPassed) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.hasPassed = hasPassed;
	}

	/** Constructor vacío (útil para frameworks o mapeo de resultados). */
	public Student() {

	}

	/** @return el id del estudiante */
	public String getId() {
		return id;
	}

	/** @param id establece el id del estudiante */
	public void setId(String id) {
		this.id = id;
	}

	/** @return el nombre del estudiante */
	public String getNombre() {
		return name;
	}

	/** @param name establece el nombre del estudiante */
	public void setNombre(String name) {
		this.name = name;
	}

	/** @return los apellidos del estudiante */
	public String getApellido() {
		return surname;
	}

	/** @param surname establece los apellidos del estudiante */
	public void setApellido(String surname) {
		this.surname = surname;
	}

	/** @return la edad del estudiante */
	public int getEdad() {
		return age;
	}

	/** @param age establece la edad del estudiante */
	public void setEdad(int age) {
		this.age = age;
	}

	/** @return texto que indica si ha aprobado */
	public String getAprobado() {
		return hasPassed;
	}

	/** @param hasPassed establece el valor de aprobado */
	public void setAprobado(String hasPassed) {
		this.hasPassed = hasPassed;
	}

	/**
	 * Devuelve una representación en cadena del estudiante.
	 *
	 * @return cadena con los datos principales del estudiante
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", hasPassed="
				+ hasPassed + "]";
	}

}
