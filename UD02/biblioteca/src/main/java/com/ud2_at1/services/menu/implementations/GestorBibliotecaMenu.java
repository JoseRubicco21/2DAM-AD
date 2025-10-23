package com.ud2_at1.services.menu.implementations;

import com.ud2_at1.services.menu.Menu;
import com.ud2_at1.services.menu.components.MenuOption;
import com.ud2_at1.services.menu.exceptions.InvalidInputException;
import com.ud2_at1.services.menu.exceptions.InvalidOptionException;

public class GestorBibliotecaMenu extends Menu {

    public GestorBibliotecaMenu(){
        this.addOption(new MenuOption("Crear Base de datos", ))
    }


    @Override
    public void display() {
       for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
       }
    }

    @Override
    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException();
        int option = Integer.parseInt(str);
        if(option > options.size() || option < 0) throw new InvalidOptionException();
        return option;
    }

}
