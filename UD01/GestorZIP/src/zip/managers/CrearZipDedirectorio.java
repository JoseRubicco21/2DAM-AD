package zip.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CrearZipDedirectorio extends ZipManager {

    private File baseDirectory;

    public CrearZipDedirectorio(File zipFile) {
        super(zipFile);
    }

    public void comprimirDirectorio(File dir){
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("El directorio especificado no existe o no es un directorio");   
        } else {
            
            this.baseDirectory = dir;
            
            try (FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                
                System.out.println("Iniciando compresión del directorio: " + dir.getName());
                comprimirDirectorioRecursivo(dir, zipOut);
                System.out.println("Compresión completada exitosamente.");
                
            } catch (IOException e) {
                System.out.println("Error durante la compresión: " + e.getMessage());
            }
        }
    }

    private void comprimirDirectorioRecursivo(File dir, ZipOutputStream zipOut) throws IOException {
        File[] files = dir.listFiles();
        
        if (files != null) {
            for (File file : files) {
                String relativePath = getRelativePath(baseDirectory, file);
                
                if (file.isDirectory()) {
                    // Add directory entry to ZIP
                    ZipEntry dirEntry = new ZipEntry(relativePath + "/");
                    zipOut.putNextEntry(dirEntry);
                    zipOut.closeEntry();
                    
                    // Recursively compress subdirectory
                    comprimirDirectorioRecursivo(file, zipOut);
                } else {
                    // Add file to ZIP
                    ZipEntry fileEntry = new ZipEntry(relativePath);
                    zipOut.putNextEntry(fileEntry);
                    
                    try (FileInputStream fis = new FileInputStream(file)) {
                        writeFileToZip(fis, zipOut);
                    }
                    
                    zipOut.closeEntry();
                    System.out.println("Comprimido: " + relativePath);
                }
            }
        }
    }

    private String getRelativePath(File baseDir, File file) {
        Path basePath = baseDir.toPath();
        Path filePath = file.toPath();
        return basePath.relativize(filePath).toString().replace('\\', '/');
    }

    public File getDirectoryToCompress(String name) throws FileNotFoundException, IOException{
        File dir = Paths.get("ficheros", name).toFile();

        if(!dir.exists()) throw new FileNotFoundException("Error. El archivo especificado no existe.");
        if(!dir.isDirectory()) throw new IOException("Error el archivo especificado no es un directorio");

        return dir;
    }

    private void writeFileToZip(FileInputStream fis, ZipOutputStream zipOut) throws IOException{
        byte[] buffer = new byte[1024];
        int readBytes;
        while((readBytes = fis.read(buffer)) >= 0){
            zipOut.write(buffer, 0, readBytes);
        }
    }
}