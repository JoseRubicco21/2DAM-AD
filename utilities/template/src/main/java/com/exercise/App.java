package com.exercise;

import java.io.ObjectInputFilter.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;
import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;

import com.exercise.controllers.MasterController;
import com.exercise.controllers.conectors.JBDCTemplateController;
import com.exercise.services.loader.ConfigLoader;


public class App {
 
    public static void main(String[] args) {

        JBDCTemplateController tmpcon = new MasterController("testdb");
        tmpcon.init();

        try{
            Connection con = tmpcon.openConnection(ConfigLoader.get("mysql.url"), ConfigLoader.get("mysql.user"), ConfigLoader.get("mysql.password"));

            RowSet rs = tmpcon.makeRowSet(ConfigLoader.get("mysql.url"), ConfigLoader.get("mysql.user"), ConfigLoader.get("mysql.password"), null)
            
        }  catch(SQLException e) {
            System.out.println(e.getMessage());
        }
       
    }

}
