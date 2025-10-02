package zip.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import zip.exceptions.isDirectoryException;


public class CrearZip{

    private File zipFile;
    private ArrayList<File> filesToZip;
    
    public CrearZip(){

    }

    public CrearZip(File zipFile){
        this.zipFile = zipFile;
        this.filesToZip = new ArrayList<File>();
    }


    public void comprimir(){
        try(
            FileOutputStream fos = new FileOutputStream(zipFile.getName());
            ZipOutputStream zipOut = new ZipOutputStream(fos);)
        {
            this.putEntries(zipOut, this.createZipEntries());
                
            for(int i = 0; i < filesToZip.size(); i++){ 
                    
                try(FileInputStream fis = new FileInputStream(filesToZip.get(i).getName())){
                   this.writeFileToZip(fis, zipOut);
                } catch (IOException e){
                   System.out.println(e.getMessage());
                }                
            }
        
        } catch (IOException e){
            e.getMessage();
        }
    }



    private ArrayList<ZipEntry> createZipEntries(){
        return this.filesToZip.stream().map((file) -> new ZipEntry(file.getName())).collect(Collectors.toCollection(ArrayList::new));
    }

    private void putEntries(ZipOutputStream zipOut, ArrayList<ZipEntry> zipEntries) throws IOException{
        for(int i = 0; i < zipEntries.size(); i++){
            zipOut.putNextEntry(zipEntries.get(i));
        }
    }    
 
    private void writeFileToZip(FileInputStream fis, ZipOutputStream zipOut) throws IOException{
        byte[] buffer = new byte[1024];
        int readBytes;
        while((readBytes = fis.read(buffer)) >= 0){
            zipOut.write(buffer, 0, readBytes);
        }
    }

    public void askForFiles(int fileCounter, Scanner input){
        for(int i = 0; i < fileCounter; i++){
            try{
                System.out.println("Nombre del archivo a añadir");
                this.filesToZip.add(this.askForFile(input.nextLine()));
            } catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
    }

    public File askForFile(String filename) throws FileNotFoundException, isDirectoryException {
        Path path = Paths.get("ficheros", filename);
        File file = path.toFile();

        if (!file.exists()) throw new FileNotFoundException();
        if (!file.isFile()) throw new isDirectoryException();

        return file;
    };

}