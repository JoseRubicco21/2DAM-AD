package menu;
import menu.MenuAction;
import menu.actions.UnimplementedMenuAction;

public class MenuOption {
    private String description;
    private MenuAction action;

    // We default to Unimplemented menu action if there's no action defined when building.
    MenuOption(String description){
        this.description = description;
        this.action = new UnimplementedMenuAction();
    }

    MenuOption(String description, MenuAction action){
        this.description = description;
        this.action = action;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
