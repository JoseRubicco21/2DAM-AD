package zip.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExtraerZip extends ZipManager {
    
    public ExtraerZip(File zipFile){
        super(zipFile);
    }

    public void descomprimir(){
        try{
            File outputDir = this.createTargetDirectory();
            descomprimirDirectorio(outputDir);
        } catch ( FileAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void descomprimirDirectorio(File output){
        try(FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zipIn = new ZipInputStream(fis)){

            ZipEntry entry;

            while((entry = zipIn.getNextEntry()) != null){
                System.out.println("Extrayendo: " + entry.getName());

                File file = new File(output, entry.getName());
                
                if(entry.isDirectory()){
                    file.mkdirs();
                } else {
                    new File(file.getParent()).mkdirs();
                    
                    
                    try(FileOutputStream fos = new FileOutputStream(file)){
                        byte[] buffer = new byte[1024];
                        int readBytes;
                        while ((readBytes = zipIn.read(buffer)) > 0) {
                            fos.write(buffer, 0, readBytes);
                        }
                    }
                    
                }
                zipIn.closeEntry();
            }

            } catch (Exception e ){
                System.out.println(e.getMessage());
            }
    }

    private File createTargetDirectory() throws FileAlreadyExistsException, IOException{

        String zipFileName = zipFile.getName();
        String baseName = zipFileName.substring(0, zipFileName.lastIndexOf('.'));
        File parentDirectory = zipFile.getParentFile();
        File target = new File(parentDirectory, baseName);

        if (target.exists()) throw new FileAlreadyExistsException("El directorio de destino ya existe.");

        if(target.mkdirs()){
            System.out.println("Descomprimiendo en: " + target.getPath());
        } else {
            throw new IOException("No se pudo crear el directorio de destino");
        }

        return target;
    }
}
