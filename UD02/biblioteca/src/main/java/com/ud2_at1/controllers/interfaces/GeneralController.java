package com.ud2_at1.controllers.interfaces;

public interface GeneralController {
    
    // Create, Modify and Delete users
    public boolean createUser();
    public boolean deleteUser();
    public boolean modifyUser();

    // Create, modify and Delete Databases
    public boolean createDatabase();
    public boolean deleteDatabase();
    // Not sure if I should do this. Modify what of what?
    public boolean modifyDatabase();
    public boolean setCurrentDatabase();
    
}
