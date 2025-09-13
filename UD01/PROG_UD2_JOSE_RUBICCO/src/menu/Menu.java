package menu;
import java.util.ArrayList;
import java.util.List;
import menu.MenuOption;

// This is a partial implementation of a Menu using Command + Builder pattern and something in between.
// The comments are also in English as it is and should be standard practice. Please notify if needed otherwise.

public class Menu {

    List<MenuOption> options;

    public Menu(){
        this.options = new ArrayList<MenuOption>();
    }

    // Displays all the options of the menu 
    public void display(){
        // Over each option display their description and add an index for the menu.        
    }

    // Builder pattern stuff.
    public Menu addOption(MenuOption option){
        this.options.add(option);
        return this;
    }

    public Menu removeOption(int index){
        this.options.remove(index);
        return this;
    }

}
