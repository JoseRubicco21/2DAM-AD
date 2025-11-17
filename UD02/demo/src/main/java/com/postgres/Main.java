package com.postgres;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.database.DatabaseService;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.implementations.MainMenu;
import com.postgres.services.menu.state.MenuState;

public class Main {
    public static void main(String[] args) {
        Logger.info("Starting Alumnos Application...");
        
        // Initialize database
        DatabaseService.initializeDatabase();
        
        // Recreate schema with CASCADE constraints (temporary fix)
        Logger.info("Updating schema to include CASCADE constraints...");
        
        // Test database connectivity
        if (!DatabaseService.testDatabaseConnectivity()) {
            Logger.error("Database connectivity test failed. Exiting...");
            return;
        }
        
        // Create sample data
        createSampleData();

        // Start the menu system
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner);
        mainMenu.setState(MenuState.ACTIVE);
        
        Logger.success("¡Bienvenido al Sistema de Gestión de Alumnos!");
        
        while (mainMenu.isActive()) {
            try {
                mainMenu.display().choose(scanner).execute();
            } catch (Exception e) {
                Logger.error("Error en el menú: " + e.getMessage());
            }
        }
        
        scanner.close();
        Logger.success("¡Hasta luego!");
    }
    
    /**
     * Creates sample data for testing the application
     */
    private static void createSampleData() {
        Logger.info("Creating sample data...");
        
        AlumnoDao alumnoDao = new AlumnoDao();
        
        // Check if there's already data in the database
        if (!alumnoDao.getAll().isEmpty()) {
            Logger.info("Sample data already exists, skipping creation.");
            return;
        }
        
        try {
            // Sample students data
            Alumno[] sampleAlumnos = {
                new Alumno("Juan Carlos", "juan.carlos@email.com", 20),
                new Alumno("María González", "maria.gonzalez@email.com", 19),
                new Alumno("Pedro Martínez", "pedro.martinez@email.com", 21),
                new Alumno("Ana López", "ana.lopez@email.com", 18),
                new Alumno("Carlos Rodríguez", "carlos.rodriguez@email.com", 22),
                new Alumno("Laura Sánchez", "laura.sanchez@email.com", 20),
                new Alumno("Miguel Torres", "miguel.torres@email.com", 19),
                new Alumno("Carmen Ruiz", "carmen.ruiz@email.com", 21),
                new Alumno("Javier Moreno", "javier.moreno@email.com", 23),
                new Alumno("Isabel Jiménez", "isabel.jimenez@email.com", 18),
                new Alumno("Antonio Muñoz", "antonio.munoz@email.com", 20),
                new Alumno("Rosa Álvarez", "rosa.alvarez@email.com", 19),
                new Alumno("Francisco Romero", "francisco.romero@email.com", 22),
                new Alumno("Pilar Navarro", "pilar.navarro@email.com", 21),
                new Alumno("José Luis Herrera", "jose.herrera@email.com", 24)
            };
            
            int successCount = 0;
            int errorCount = 0;
            
            for (Alumno alumno : sampleAlumnos) {
                try {
                    if (alumnoDao.save(alumno)) {
                        successCount++;
                        Logger.info("✓ Created student: " + alumno.getNombre());
                    } else {
                        errorCount++;
                        Logger.warning("✗ Failed to create student: " + alumno.getNombre());
                    }
                } catch (Exception e) {
                    errorCount++;
                    Logger.error("✗ Error creating student " + alumno.getNombre() + ": " + e.getMessage());
                }
                
                // Small delay to avoid overwhelming the database
                Thread.sleep(50);
            }
            
            Logger.success("Sample data creation completed!");
            Logger.success("✓ Successfully created: " + successCount + " students");
            if (errorCount > 0) {
                Logger.warning("✗ Failed to create: " + errorCount + " students");
            }
            
        } catch (Exception e) {
            Logger.error("Failed to create sample data: " + e.getMessage());
        }
    }
}