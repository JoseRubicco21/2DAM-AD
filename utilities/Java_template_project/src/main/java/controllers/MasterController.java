package controllers;

import controllers.db.JBDCTemplateController;

public class MasterController extends JBDCTemplateController {
	
	public MasterController(String dbName) {
		this.databaseName = dbName;
	}
	
	@Override
	public void init() {
		// IMPORTANT: Use the field databaseName to connect to a specific database.
		// TODO Auto-generated method stub
	}

	
	public void loadDefaults() {
		
	};
}
