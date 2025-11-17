package com.postgres.services.menu.actions;

import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class CreateAlumnoAction extends MenuAction {
    private AlumnoDao alumnoDao = new AlumnoDao();
    private Scanner scanner;

    public CreateAlumnoAction(Scanner sc){
        this.scanner = sc;
    }


    public MenuResult execute() {
        Logger.info("=== CREAR NUEVO ALUMNO ===");
        
        try {
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();
            
            System.out.print("Edad: ");
            int edad = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            
            Alumno alumno = new Alumno();
            alumno.setNombre(nombre);
            alumno.setAge(edad);
            alumno.setEmail(email);
            
            if (alumnoDao.save(alumno)) {
                Logger.success("Alumno creado exitosamente!");
            } else {
                Logger.error("Error al crear el alumno.");
            }
            
        } catch (NumberFormatException e) {
            Logger.error("Error: La edad debe ser un número válido.");
        } catch (Exception e) {
            Logger.error("Error creando alumno: " + e.getMessage());
        }
        return MenuResult.CONTINUE;
    }
}