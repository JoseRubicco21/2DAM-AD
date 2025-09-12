package menu.actions;
import java.util.Scanner;

import menu.MenuAction;

public class GetNumberOfStudentsTallerThan implements MenuAction {

    // We're going to be using the same type of stuff we do on the numerOfStudentsTallerThan.
    private Scanner sc = new Scanner(System.in);
    private float[] studentsHeight = new float[5];
    private int whileFlag = 0;

    @Override
    public void execute() {
        // We get the data the same way as we did with the for but with a while loop.
        while(whileFlag < 5){
            System.out.printf("Introduce la edad del alumno %d:%n", whileFlag+1);           
            studentsHeight[whileFlag] = sc.nextInt();
        }
        
        // We reset so we can re-use the flag.
        whileFlag = 0;
        int studentsTallerThan = 0;
        
        while(whileFlag < 5){
            if(studentsHeight[whileFlag] >= 1.75){
                studentsTallerThan += 1;
            }
        }

        // We display the whole stuff and close the resource we opened with the command.
        System.out.printf("Cantidad de alumnos con altura mayor o igual a 1.75m: %d", studentsTallerThan);
        sc.close(); 
    }
    
}
