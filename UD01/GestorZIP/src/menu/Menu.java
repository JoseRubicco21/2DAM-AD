package menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import menu.components.MenuOption;
import menu.exceptions.InvalidInputException;
import menu.exceptions.InvalidOptionException;
import menu.state.MenuResult;
import menu.state.MenuState;

// This is a partial implementation of a Menu using Command + Builder pattern and something in between.
// The comments are also in English as it is and should be standard practice. Please notify if needed otherwise.


public abstract class Menu {

    private int currentOption;
    private MenuResult lastResult;
    protected List<MenuOption> options;
    protected MenuState state;

    public Menu(){
        this.options = new ArrayList<MenuOption>();
        this.state = MenuState.INACTIVE;
        this.currentOption = -1;
    }

    // Displays all the options of the menu, we let it off to the implementations.
    // This lets us define display for each menu, letting us have I.- 1.- and other 
    // types of numberings. 
    
    public abstract void display();

    public void choose(Scanner sc) throws InvalidInputException, InvalidOptionException{
        this.setCurrentOption(this.validateInput(sc.nextLine()));
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }

    public void execute(){
        this.setLastResult(
            this.getOptions()
            .get(this.currentOption)
            .getAction()
            .execute()
        );        
    }
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

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public boolean isActive(){
        return state == MenuState.ACTIVE;
    }

    public List<MenuOption> getOptions() {
        return options;
    }

    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException();
        int option = Integer.parseInt(str);
        if(option > options.size() || option < 0) throw new InvalidOptionException();
        return option;
    }

    public MenuResult getLastResult() {
        return lastResult;
    }

    public void setLastResult(MenuResult lastResult) {
        this.lastResult = lastResult;
    }

    
    
}
