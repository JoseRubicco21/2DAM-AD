package menu.actions;
import java.util.Scanner;
import menu.MenuAction;


public class AugmentSalaryMenuAction implements MenuAction{

    private Scanner sc = new Scanner(System.in);
    

    @Override
    public void execute() {
        
        double salary = sc.nextDouble();
        if(salary < 1000){
            System.out.printf("El salario con el aumento 15% incorporado es: %d", salary*0.15+salary);
        } else {
            System.out.printf("El salario con el aumento del 12% incorporado es: %d", salary*0.12+salary);
        }
    }
    

}