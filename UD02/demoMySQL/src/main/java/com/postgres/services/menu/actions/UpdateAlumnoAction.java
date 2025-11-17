package com.postgres.services.menu.actions;

import java.util.Scanner;

import com.postgres.dao.implementations.AlumnoDao;
import com.postgres.models.Alumno;
import com.postgres.services.logger.Logger;
import com.postgres.services.menu.state.MenuResult;

public class UpdateAlumnoAction extends MenuAction {
    private AlumnoDao alumnoDao = new AlumnoDao();
    private Scanner scanner;

    public UpdateAlumnoAction(Scanner sc){
        this.scanner = sc;
    }
    
    @Override
    public MenuResult execute() {
        Logger.info("=== ACTUALIZAR ALUMNO ===");
        
        try {
            System.out.print("ID del alumno a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            Alumno alumno = alumnoDao.get(id);
            if (alumno == null) {
                Logger.warning("No se encontró ningún alumno con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            System.out.println("\nInformación actual del alumno:");
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Email: " + alumno.getEmail());
            System.out.println("Edad: " + alumno.getAge());
            
            System.out.println("\nIngresa los nuevos datos (presiona Enter para mantener el valor actual):");
            
            System.out.print("Nuevo nombre [" + alumno.getNombre() + "]: ");
            String nuevoNombre = scanner.nextLine().trim();
            if (!nuevoNombre.isEmpty()) {
                alumno.setNombre(nuevoNombre);
            }
            
            System.out.print("Nuevo email [" + alumno.getEmail() + "]: ");
            String nuevoEmail = scanner.nextLine().trim();
            if (!nuevoEmail.isEmpty()) {
                alumno.setEmail(nuevoEmail);
            }
            
            System.out.print("Nueva edad [" + alumno.getAge() + "]: ");
            String edadStr = scanner.nextLine().trim();
            if (!edadStr.isEmpty()) {
                alumno.setAge(Integer.parseInt(edadStr));
            }
            
            alumnoDao.update(alumno);
            Logger.success("Alumno actualizado exitosamente!");
            
        } catch (NumberFormatException e) {
            Logger.error("Error: Debe ingresar números válidos para ID y edad.");
        } catch (Exception e) {
            Logger.error("Error actualizando alumno: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}