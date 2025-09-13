package menu;
import java.util.ArrayList;
import java.util.List;
import menu.MenuOption;

// This is a partial implementation of a Menu using Command + Builder pattern and something in between.
// The comments are also in English as it is and should be standard practice. Please notify if needed otherwise.

public abstract class Menu {

    protected List<MenuOption> options;

    public Menu(){
        this.options = new ArrayList<MenuOption>();
    }

    // Displays all the options of the menu, we let it off to the implementations.
    // This lets us define display for each menu, letting us have I.- 1.- and other 
    // types of numberings. 
    
    public abstract void display();

    // Every menu should behave as a list of things that can grow or shrink, as so these two
    // methods for adding and removing Options are on the abstract class.

    public Menu addOption(MenuOption option){
        this.options.add(option);
        return this;
    }

    public Menu removeOption(int index){
        this.options.remove(index);
        return this;
    }

}
