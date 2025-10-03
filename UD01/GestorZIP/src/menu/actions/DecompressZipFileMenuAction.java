package menu.actions;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;

public class DecompressZipFileMenuAction extends MenuAction{

    private Scanner sc;

    public DecompressZipFileMenuAction(Scanner sc){
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        System.out.println("Introduzca el nombre del directorio a descomprimir");
        String zipFolderName = sc.nextLine();

        Path directoryPath = Paths.get("ficheros", zipFolderName);
        File directory = directoryPath.toFile();

        return MenuResult.CONTINUE;
    }
    
}
