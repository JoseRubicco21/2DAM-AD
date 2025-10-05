package menu.actions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;
import zip.managers.CrearZipDedirectorio;


public class CompressFolderMenuAction extends MenuAction {

    private Scanner sc;

    public CompressFolderMenuAction(Scanner sc){
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        try {
            String folderName = this.getFolderName(this.sc);
            
            // Source directory to compress
            Path folderPath = Paths.get("ficheros", folderName);
            File sourceDir = folderPath.toFile();

            if (!sourceDir.exists() || !sourceDir.isDirectory()) {
                System.out.println("Error: El directorio '" + folderName + "' no existe o no es un directorio.");
            } else {

                
                // Output ZIP file path (same name as directory + .zip)
                Path zipPath = Paths.get("ficheros", folderName + ".zip");
                File zipFile = zipPath.toFile();
                
                // Create ZIP manager with output ZIP file
                CrearZipDedirectorio cZipDir = new CrearZipDedirectorio(zipFile);
                
                // Compress the source directory
                System.out.println("Comprimiendo directorio '" + folderName + "'...");
                cZipDir.comprimirDirectorio(sourceDir);
                
                System.out.println("Directorio comprimido exitosamente en: " + zipPath.toString());
            }
            
        } catch (Exception e) {
            System.out.println("Error durante la compresión: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }

    private String getFolderName(Scanner sc){
        System.out.println("Introduce el nombre del directorio que quieres comprimir:");
        String result = sc.nextLine().trim();
        return result;
    }

}
