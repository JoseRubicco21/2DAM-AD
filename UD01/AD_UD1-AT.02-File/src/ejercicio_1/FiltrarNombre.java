package ejercicio_1;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarNombre implements FilenameFilter {

    private String name;

    public FiltrarNombre(String name){
        this.name = name;
    }

    @Override
    public boolean accept(File file, String name) {
        return name.startsWith(this.name);
    }    


}
