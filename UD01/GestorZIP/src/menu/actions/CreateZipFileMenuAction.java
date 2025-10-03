package menu.actions;

import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;
import menu.components.MenuAction;
import menu.state.MenuResult;
import zip.managers.CrearZip;

public class CreateZipFileMenuAction extends MenuAction{

    private Scanner sc;

    public CreateZipFileMenuAction(Scanner sc){
        this.sc = sc;
    }

    @Override    
    public MenuResult execute(){
    
        String zipFilename = this.getZipFileName(sc);
        File zipFile = this.pathToFile(zipFilename, "ficheros");
        int fileCounter = this.getFileNumbers(sc);
        
        CrearZip cZip = new CrearZip(zipFile);
        cZip.askForFiles(fileCounter, this.sc);
        cZip.comprimir();

        return MenuResult.CONTINUE;
    }

    private  String getZipFileName(Scanner sc){
        System.out.println("Nombre a guardar el fihcero ZIP que se creará:");
        String result = this.sc.nextLine()+".zip";
        return result;
    }

    private File pathToFile(String fileName, String... directoryNames){
        Path filePath = Paths.get(fileName, directoryNames);
        File result = filePath.toFile();
        return result;
    }

    private int getFileNumbers(Scanner sc){
         System.out.println("¿Cuántos ficheros se quieren añadir?");
         int result = Integer.parseInt(this.sc.nextLine());
         return result;
    }
}
