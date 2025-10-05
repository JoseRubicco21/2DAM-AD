package menu.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import menu.components.MenuAction;
import menu.state.MenuResult;
import zip.managers.ExtraerZip;

public class DecompressZipFileMenuAction extends MenuAction{

    private Scanner sc;

    public DecompressZipFileMenuAction(Scanner sc){
        this.sc = sc;
    }

    @Override
    public MenuResult execute() {
        
        try{
            File zipFile = this.getZipFile();
            ExtraerZip eZip = new ExtraerZip(zipFile);
            eZip.descomprimir();

        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        return MenuResult.CONTINUE;
    }
    

    private File getZipFile() throws FileNotFoundException{
        System.out.println("Introduzca el nombre del directorio a descomprimir");
        String zipFileName = sc.nextLine();

        Path zipFilePath = Paths.get("ficheros", zipFileName);
        File zipFile = zipFilePath.toFile();

        if(!zipFile.exists()) throw new FileNotFoundException("El fichero Zip a descomprimir no existe.");

        return zipFile;
    }
}
