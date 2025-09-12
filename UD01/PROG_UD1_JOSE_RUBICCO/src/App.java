import java.util.List;
import java.util.Scanner;
import java.lang.Math;
import java.text.NumberFormat;

public class App {
    // This is better initialized in a constructor.
    private final static Scanner sc = new Scanner(System.in);

    // Method 2. Sum of two decimals and get the remainder. 
    private static double getRemainder (double first, double second)  {
        // We resort to Math.Abs method to get the absolute value and use the % operator to get the remainder..
    	double result = Math.abs(first) % Math.abs(second);
        return result;
    }

    
    // Method 3. stringContainsChar, Checks if there's an a character. You could technically use a for loop for this. 
    private static boolean method3 (String str, char character) {
    	// This is a very funny implementation of what this is supposed to do. But the user doesn't really see it so...
    	return str.contains(Character.toString(character)); 
    	// If you want to use a loop here's the code: 
    }
   
    private static void method4 (double num1, double num2) {
    	// Cast to float as it is needed to.
    	float result = (float)(num1 + num2);
    	System.out.printf("El valor de la suma es %.2f\n", result);
    	System.out.printf("La parte entera de la suma es %.0f\n", result );
    	System.out.printf("La parte decimal de la suma es %.2f\n", result % 1);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Ejecutando método 2");
        // We could make a wrapper for the scanner but eeh.. We are going to do that later on. 
        System.out.println("Introduzca el número 1:");
        double a = sc.nextDouble();
        System.out.println("Introduzca el número 2:");
        double b = sc.nextDouble();
        System.out.println("resultado del método dos: " + getRemainder(a, b));
        
        // Method 3
        System.out.println("Método 3 ejecutando. Resultado: " + method3("bob", 'o'));
        
        // Method 4
        double num1 = 12;
        double num2 = 2;
        System.out.println("Método 4 ejecutando.");
        
        method4(num1, num2);
    }


    
}
