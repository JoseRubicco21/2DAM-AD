package menu.actions;
import java.util.Scanner;
import menu.MenuAction;
import menu.MenuResult;

public class GetNumberOfStudentsOfAge implements MenuAction{
    // Sadly there's no point in using the student class oon the models package
    // as it does not require the age of 5 students that are being held in memory. 
    // This is why we're using a int array instead of a collection of alumnos or students.
    private int[] studentsAge = new int[5];
    
    // I'm going to assume the input is always going to be an integer. I'm not going to validate this at seems
    // more over the top than what i'm already doing.
    @Override
    public MenuResult execute(Scanner sc) {
        // Store everything up in an array as we get the data from the user.
        for (int i = 0; i < 5; i++) {
            System.out.printf("Introduce la edad del alumno %d:%n", i+1);           
            studentsAge[i] = sc.nextInt();
        }

        // Iterate over the array and 
        int numberOfAdults = 0;

        // Augment the number of adults if meets the requirement.
        for(int i = 0; i < studentsAge.length; i++){
            if(studentsAge[i] >= 18){
                numberOfAdults += 1;
            }
        }
        // print out stuff and the close the resource we open with the creation of the command.
        System.out.println("Number of adults: " + numberOfAdults);

        return MenuResult.CONTINUE;
    }
    

}
