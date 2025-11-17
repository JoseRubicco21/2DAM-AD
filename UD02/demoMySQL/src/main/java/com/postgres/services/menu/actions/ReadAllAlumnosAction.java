package com.postgres.services.menu.actions;

import java.util.List;
import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class ReadAllAlumnosAction extends MenuAction {
    private AlumnoDao alumnoDao = new AlumnoDao();


    @Override
    public MenuResult execute() {
        Logger.info("=== LISTA DE TODOS LOS ALUMNOS ===");
        
        try {
            List<Alumno> alumnos = alumnoDao.getAll();
            
            if (alumnos.isEmpty()) {
                Logger.warning("No se encontraron alumnos en la base de datos.");
                return MenuResult.CONTINUE;
            }
            
            System.out.println("\n" + "-".repeat(80));
            System.out.printf("%-5s %-15s %-20s %-5s %-25s%n", 
                "ID", "NOMBRE", "APELLIDOS", "EDAD", "EMAIL");
            System.out.println("-".repeat(80));
            
            for (Alumno alumno : alumnos) {
                System.out.printf("%-5d %-15s %-52d %-20s %n",
                    alumno.getId(),
                    alumno.getNombre(),
                    alumno.getAge(),
                    alumno.getEmail());
            }
            System.out.println("-".repeat(80));
            Logger.success("Total de alumnos: " + alumnos.size());
            
        } catch (Exception e) {
            Logger.error("Error obteniendo alumnos: " + e.getMessage());
        }

        return MenuResult.CONTINUE;
    }
}