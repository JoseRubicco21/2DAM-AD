package com.exercise.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import com.exercise.controllers.conectors.JBDCTemplateController;
import com.exercise.services.loader.ConfigLoader;

public class MasterController extends JBDCTemplateController {
	
	public MasterController(String dbName) {
		this.databaseName = dbName;
	}
	
	@Override
	public void init() {
		try{
			Connection con = openConnection(ConfigLoader.get("mysql.url"),ConfigLoader.get("mysql.user"), ConfigLoader.get("mysql.password"));
			if(con == null){System.out.println("problems");} else {System.out.println("Connected");}
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}

	}

	
	public void loadDefaults() {
		
	};
}
