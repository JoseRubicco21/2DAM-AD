package views;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Clase general de menu. Implementa las funcines básicas de un menu.
 */
public class Menu {
   /**
    * Objeto de scanner utilizado para introducir datos.
    */
	private Scanner sc;
	/**
	 * Entero para controlar el flujo del menú. 
	 */
	private int opcion;

	/**
	 * List de las opciones del menu
	 */
	private List<String> opciones;
	
	/**
	 * Titulo del menu. 
	 */
	private String title;
	
	/**
	 * Constructor por defecto. Instanca un Scanner e inicializa la variable opcion a -1
	 */
	public Menu() {
		this.sc = new Scanner(System.in);
		this.opcion = -1;
		this.opciones = new ArrayList<String>();
	}
	/**
	 * Constructor que utiliza un scanner como parametro
	 * @param sc Objeto de tipo Scanner que será utilizado para la obtención de datos
	 */
	public Menu(Scanner sc) {
		this.sc = sc;
		this.opcion = -1;
	}
	
	/**
	 * Constructor del menu que especifica las opciones.
	 * @param strings
	 */
	public Menu(String title, String ... strings) {
		this.sc = new Scanner(System.in);
		this.opcion = -1;
		this.opciones = new ArrayList<String>(List.of(strings));
		this.title = title;
	}

	/**
	 * Constructor que utiliza un scanner y una opción diferente a -1.
	 * @param sc Objeto de tipo Scanner
	 * @param opcion Entero que indica la opción del menu.
	 */
	public Menu(Scanner sc, int opcion) {
		this.sc = sc;
		this.opcion = opcion;
	}

	/**
	 * Método para obtener el Objeto Scanner que pertenece al menu.
	 * @return Scanner El objeto de tipo Scanner que recibe el dato de opción.
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * Método para establecer un nuevo Scanner 
	 * @param sc El nuevo Scanner que se desea establecer.
	 */
	public void setSc(Scanner sc) {
		this.sc = sc;
	}

	/**
	 * Método que obtiene la opción del menu.
	 * @return int Opción del menu actual.
	 */
	public int getOpcion() {
		return opcion;
	}

	/**
	 * Método que establece la opción del menu 
	 * @param opcion el número de la opción que se quiere establecer.
	 */
	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
	
	/**
	 * Método que establece la opción del menu. En este caso la opción es la entrada por teclado del usuario.
	 */

	public int setOpcion() {
		this.opcion = Integer.parseInt(sc.nextLine());
		return this.opcion;
	}

	// Resets opción so that menu always comes back to menu. #MenuVuelveAMenu
	/**
	 * Reinicia la opción del menu a su valor estandar.
	 */
	public void resetOpcion() {
		this.opcion = -1;
	}

	public void displayTitle() {
		int titleLength = this.title.length();
		String tableFormat = "%1$s %2$s %1$s%n";
		
		System.out.printf(tableFormat, "*", "-".repeat(titleLength)); // +2 Comes from spaces
		System.out.printf(tableFormat, "|", this.title);
		System.out.printf(tableFormat, "*", "-".repeat(titleLength)); // +2 Comes from spaces
		
		
	}
	
	public void displayMenu() {
		this.displayTitle();		
		for(int i = 0; i < this.opciones.size(); i++) {
			System.out.printf("[%d] %s%n", i+1, this.opciones.get(i));
		}
	};
}