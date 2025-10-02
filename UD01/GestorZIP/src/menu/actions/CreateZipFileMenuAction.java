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
    
        System.out.println("Nombre a guardar el fihcero ZIP que se creará:");
        String zipFilename = this.sc.nextLine()+".zip";
        
        Path filePath = Paths.get("ficheros", zipFilename);
        File zipFile = filePath.toFile();
        CrearZip cZip = new CrearZip(zipFile);

        System.out.println("¿Cuántos ficheros se quieren añadir?");
        int fileCounter = Integer.parseInt(this.sc.nextLine());

        cZip.askForFiles(fileCounter, this.sc);
        cZip.comprimir();

        return MenuResult.CONTINUE;
    }
}
