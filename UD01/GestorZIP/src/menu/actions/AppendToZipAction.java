package menu.actions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;
import zip.managers.AddDataToZip;

public class AppendToZipAction extends MenuAction {

    private Scanner sc;

    public AppendToZipAction(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        try {
            System.out.println("Introduce el fichero ZIP al que quieres añadir:");
            File zipFile = getZipFile(sc);
            
            System.out.println("Introduce el archivo que quieres añadir:");
            File fileToAdd = getFile(sc);
            
            System.out.println("Añadiendo archivo al ZIP...");
            AddDataToZip.appendToZip(zipFile, fileToAdd);
            System.out.println("Archivo añadido exitosamente al ZIP.");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

        return MenuResult.CONTINUE;
    }

    /**
     * Gets a ZIP file with validation
     */
    private File getZipFile(Scanner sc) throws IOException {
        String fileName = sc.nextLine().trim();
        
        if (fileName.isEmpty()) {
            throw new IOException("El nombre del archivo no puede estar vacío");
        }

        Path filePath = Paths.get("ficheros", fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new IOException("El archivo ZIP '" + fileName + "' no existe en la carpeta 'ficheros'");
        }

        if (!file.isFile()) {
            throw new IOException("'" + fileName + "' no es un archivo válido");
        }

        if (!fileName.toLowerCase().endsWith(".zip")) {
            throw new IOException("'" + fileName + "' no es un archivo ZIP válido");
        }

        return file;
    }

    /**
     * Gets a regular file with validation
     */
    private File getFile(Scanner sc) throws IOException {
        String fileName = sc.nextLine().trim();
        
        if (fileName.isEmpty()) {
            throw new IOException("El nombre del archivo no puede estar vacío");
        }

        Path filePath = Paths.get("ficheros", fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            throw new IOException("El archivo '" + fileName + "' no existe en la carpeta 'ficheros'");
        }

        if (!file.isFile()) {
            throw new IOException("'" + fileName + "' no es un archivo válido");
        }

        return file;
    }
}