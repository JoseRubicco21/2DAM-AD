package menu.actions;
import java.util.Scanner;
import menu.MenuAction;
import menu.MenuResult;


public class AugmentSalaryMenuAction implements MenuAction{

    @Override
    public MenuResult execute(Scanner sc) {
        System.out.println("Introduzca el salario que desea aumentar");
        double salary = sc.nextDouble();
        if(salary < 1000){
            System.out.printf("El salario con el aumento 15%% incorporado es: %f%n", (salary*0.15+salary));
        } else {
            System.out.printf("El salario con el aumento del 12%% incorporado es: %f%n", (salary*0.12+salary));
        }

        return MenuResult.CONTINUE;
    }
    

}