package com.postgres.services.menu.actions;

import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class ReadAlumnoByIdAction extends MenuAction {
    private AlumnoDao alumnoDao = new AlumnoDao();
    private Scanner scanner;

    public ReadAlumnoByIdAction(Scanner sc){
        this.scanner = sc;
    }


    @Override
    public MenuResult execute() {
        Logger.info("=== BUSCAR ALUMNO POR ID ===");
        
        try {
            System.out.print("ID del alumno: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Alumno alumno = alumnoDao.get(id);
            
            if (alumno != null) {
                System.out.println("\n" + "-".repeat(40));
                System.out.println("INFORMACIÓN DEL ALUMNO");
                System.out.println("-".repeat(40));
                System.out.println("ID: " + alumno.getId());
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Email: " + alumno.getEmail());
                System.out.println("Edad: " + alumno.getAge());
                System.out.println("-".repeat(40));
                Logger.success("Alumno encontrado!");
            } else {
                Logger.warning("No se encontró ningún alumno con ID: " + id);
            }
            
        } catch (NumberFormatException e) {
            Logger.error("Error: Debe ingresar un ID válido (número).");
        } catch (Exception e) {
            Logger.error("Error buscando alumno: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}