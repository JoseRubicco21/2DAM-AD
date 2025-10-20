package com.exercise.services.menu.implementations;
import com.exercise.services.menu.Menu;
import com.exercise.services.menu.exceptions.InvalidInputException;
import com.exercise.services.menu.exceptions.InvalidOptionException;

public class MainMenu extends Menu {


    // We create the constructor we're the implementation details of the menu is actually set. This is to have a set state.
    public MainMenu(){
    }


    @Override
    public void display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
    }


}
