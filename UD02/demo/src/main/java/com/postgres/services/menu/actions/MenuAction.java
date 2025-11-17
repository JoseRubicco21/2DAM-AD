package com.postgres.services.menu.actions;

import com.postgres.services.menu.state.MenuResult;

// It is better to use an abstract class Menu Action so every action can have
// the dependency that it needs injected. One only needs a
public abstract class MenuAction {

    abstract public MenuResult execute();
      
}
