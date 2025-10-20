package controllers.io;

/**
 * Colector de Data, sirve para validar automáticamente los tipos de datos y recolectar los datos necesarios. Funciona como una especie de Wrapper, que utiliza el patrón
 * "Facade" o de fachada para simplificar las validaciones y recolecciones de datos con una interaz simple.
 * 
 * Importante: Esto hace que la devolución de datos deba utilizar recursividad y también implica que la validación debe ocurrir en clases de tipo
 * DataColector. Esto puede llegar a ser contraproducente si no se tiene en cuenta. Va en contra del principio de única responsabilidad. Probablemente lo idea
 * sea utilizar un DataColector único y crear métodos validadores que actuen sobre los strings obtenidos del colector de datos.
 */
import java.util.Scanner;

public class DataColector {

	/**
	 * Un objeto de la clase {@link java.util.Scanner Scanner} que permite obtener los datos del usuario.
	 */
	private Scanner sc;

	/**
	 * Constructor por defecto del data colector, inicializa el objeto Scanner para la recolección de datos.
	 */
	public DataColector() {
		this.sc = new Scanner(System.in);
	}

	/**
	 * Método para obtener un String introducido por el usuario para su posterior procesamiento.
	 * @return String dato introducido por el usuario.
	 */
	private String collectData() {
		return this.sc.nextLine();
	}
	
	/**
	 * Método para obtener un String del usuario.
	 * @param displayInformation Mensaje que se dispondra en pantalla antes de obtener el dato.
	 * @return String el dato recogido por el usuario.
	 */
	public String collectString(String displayInformation) {
		boolean isDataValid = false;
		String eval;
		
		do {
			try {
				
				System.out.println(displayInformation);
				eval = this.collectData();
				isDataValid=true;
				return eval;
				
			} catch (NullPointerException npe) {
				isDataValid=false;
				throw new NullPointerException(
						"El dato introducido es nulo. Aseguresé de que un dato no nulo es introducido.\n" + npe.getLocalizedMessage());
			}

		} while (isDataValid);

	}
	

	/**
	 * Método para obtener un Entero del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return int El introducido por usuario
	 */
	public int collectInteger(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Integer.parseInt(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectInteger(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	
	/**
	 * Método para obtener un Float del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return float El introducido por usuario
	 */
	public float collectFloat(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Float.parseFloat(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectFloat(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	
	/**
	 * Método para obtener un double del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return double El introducido por usuario
	 */
	public double collectDouble(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Double.parseDouble(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectDouble(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	/**
	 * Método para obtener un short del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return short El introducido por usuario
	 */
	public short collectShort(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Short.parseShort(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectShort(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	/**
	 * Método para obtener un long del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return long El introducido por usuario
	 */
	public long collectLong(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Long.parseLong(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectLong(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	
	/**
	 * Método para obtener un byte del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return byte El introducido por usuario
	 */
	public byte collectByte(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Byte.parseByte(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectByte(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	

	/**
	 * Método para obtener un boolean del usuario.
	 * @param displayInformation Mensaje que se dispondrá en pantalla antes de obtener el dato.
	 * @param errorInformation Mensaje que se dispondrá en pantalla después de obtener el dato si este es erroneo o no cumple con la validacion
	 * @return boolean El introducido por usuario
	 */
	public boolean collectBoolean(String displayInformation, String errorInformation) {
		boolean isDataValid = false;
		String eval;
		do {
			try {
				System.out.println(displayInformation);
				eval = this.collectData();
				return Boolean.parseBoolean(eval);			
			} catch (NumberFormatException nfe) {
				System.out.println(errorInformation + "\n");
				return this.collectBoolean(displayInformation, errorInformation);
			}
		} while (!isDataValid);
	}
	
}
