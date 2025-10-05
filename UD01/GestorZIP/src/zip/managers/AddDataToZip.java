package zip.managers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class AddDataToZip {
    
    public static void appendToZip(File zipFile, File fileToAppend) throws IOException {
        System.out.println("Intentando añadir: " + fileToAppend.getAbsolutePath());
        System.out.println("Al ZIP: " + zipFile.getAbsolutePath());
        
        if (!fileToAppend.exists()) {
            throw new IOException("El archivo a añadir no existe: " + fileToAppend.getAbsolutePath());
        }
        
        if (!zipFile.exists()) {
            throw new IOException("El archivo ZIP no existe: " + zipFile.getAbsolutePath());
        }
        
        Map<String, String> env = new HashMap<>();
        env.put("create", "false"); 
        
        Path zipPath = Paths.get(zipFile.getAbsolutePath());
        URI uri = URI.create("jar:" + zipPath.toUri());

        try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
            Path sourceFile = fileToAppend.toPath();
            byte[] fileContent = Files.readAllBytes(sourceFile);
            
            Path targetPath = fs.getPath("/" + fileToAppend.getName());
            
            System.out.println("Escribiendo archivo al ZIP...");
            
            Files.write(targetPath, fileContent, 
                       StandardOpenOption.CREATE, 
                       StandardOpenOption.TRUNCATE_EXISTING);
                      
            System.out.println("Archivo añadido exitosamente: " + fileToAppend.getName());
            
        } catch (IOException e) {
            System.out.println("Error específico: " + e.getMessage());
            throw e;
        }
    }
}
