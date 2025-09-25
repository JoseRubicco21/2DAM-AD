import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

public class FileBuilder {
    
    private String basePath;
    
    public FileBuilder(String basePath){
        this.basePath = basePath;
    }


    public bool createDirectory(String dirName) throws FileAlreadyExistsException, FileNotFoundException, IOException{
        File file = new File(String.join("/", basePath, dirName));
        if(file.exists() && file.isDirectory()) throw new FileAlreadyExistsException("Directory already exists.");
        file.mkdir();
        if(!file.exists()) throw new FileNotFoundException(String.format("Directory %s was not created.", dirName));
        return true;
    }
}
