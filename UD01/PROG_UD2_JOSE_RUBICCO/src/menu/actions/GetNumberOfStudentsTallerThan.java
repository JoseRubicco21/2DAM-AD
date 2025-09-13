package menu.actions;

import java.util.Scanner;

import menu.MenuAction;
import menu.MenuResult;

public class GetNumberOfStudentsTallerThan implements MenuAction {

    // We store them as Strings so we can split by both "," and "." and then do the calculations
    // on integers so that 1.75 -> 175 or anything upper than 175 is added to the counter of students
    // taller than the amount.
    private int[] studentsHeight = new int[5];
    private int studentsTallerThan = 0;
    private int iterator = 0;
    
    @Override
    public MenuResult execute(Scanner sc) {
        sc.nextLine(); // Clear the buffer.clear

        while (iterator < 5) {
            System.out.printf("Altura para el estudiante %d:%n", iterator);
            String input = sc.nextLine();
            String[] parts = input.split("\\.|,");
            int height = Integer.parseInt(String.join("", parts));
            studentsHeight[iterator] = height;
            iterator += 1;
        }

        iterator = 0;
        while (iterator < studentsHeight.length) {
            if(studentsHeight[iterator] > 175) studentsTallerThan +=1;
            iterator += 1;
        }

        System.out.printf("Cantidad de estudiantes mas altos que 1.75m: %d%n", studentsTallerThan);
        return MenuResult.CONTINUE;
    }
    
}
