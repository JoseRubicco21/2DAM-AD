package com.ad_ud2_at2.services.menu.actions;

import com.ad_ud2_at2.services.menu.state.MenuResult;

// It is better to use an abstract class Menu Action so every action can have
// the dependency that it needs injected. One only needs a
public abstract class MenuAction {

    abstract public MenuResult execute();
      
}
