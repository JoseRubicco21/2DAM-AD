package com.postgres.services.menu.actions;

import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class DeleteAlumnoAction extends MenuAction {
    private AlumnoDao alumnoDao = new AlumnoDao();
    private Scanner scanner;

    public DeleteAlumnoAction(Scanner sc){
        this.scanner = sc;
    }
    
    @Override
    public MenuResult execute() {
        Logger.info("=== ELIMINAR ALUMNO ===");
        
        try {
            System.out.print("ID del alumno a eliminar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Alumno alumno = alumnoDao.get(id);
            if (alumno == null) {
                Logger.warning("No se encontró ningún alumno con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            System.out.println("\nAlumno a eliminar:");
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Email: " + alumno.getEmail());
            System.out.println("Edad: " + alumno.getAge());
            
            System.out.print("¿Estás seguro? (s/N): ");
            String confirmacion = scanner.nextLine().trim();
            if (confirmacion.toLowerCase().equals("s") || confirmacion.toLowerCase().equals("si")) {
                if (alumnoDao.deleteById(id)) {
                    Logger.success("Alumno eliminado exitosamente!");
                } else {
                    Logger.error("Error al eliminar el alumno.");
                }
            } else {
                Logger.info("Operación cancelada.");
            }
            
        } catch (NumberFormatException e) {
            Logger.error("Error: Debe ingresar un ID válido (número).");
        } catch (Exception e) {
            Logger.error("Error eliminando alumno: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}