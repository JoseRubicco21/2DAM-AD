package zip.managers;

import java.io.File;
import java.util.ArrayList;

public abstract class ZipManager {

    protected File zipFile;
    protected ArrayList<File> filesToOperate;
    

    public ZipManager(File zipFile){
        this.zipFile = zipFile;
        this.filesToOperate = new ArrayList<File>();
    }


    public File getZipFile() {
        return zipFile;
    }


    public void setZipFile(File zipFile) {
        this.zipFile = zipFile;
    }


    public ArrayList<File> getFilesToOperate() {
        return filesToOperate;
    }


    public void setFilesToOperate(ArrayList<File> filesToOperate) {
        this.filesToOperate = filesToOperate;
    }

    

}
