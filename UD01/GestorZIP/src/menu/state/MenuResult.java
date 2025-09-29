package menu.state;

// Menu Result controls the result of the action executed by the menu. Not the menu state.
// This way we can send to submenus like a "Debug" submenu if something Unexpected happen.
// It also makes the idea of halting the current menu based on result of an action possible.

// We could add another MenuResult like "Unwanted" or "Unexpected" 
public enum MenuResult {
    CONTINUE,
    EXIT,
}
