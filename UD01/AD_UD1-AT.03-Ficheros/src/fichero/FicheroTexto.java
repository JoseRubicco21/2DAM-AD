package fichero;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

public class FicheroTexto {
    private File file;

    public FicheroTexto(File file) {
        this.file = file;
    }

    public FicheroTexto(){

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void escribir(String texto){
        // Using constrctor file + Append.
        try(FileWriter fw = new FileWriter(this.file, true)){
            fw.write(texto);
            fw.flush();
        } catch (IOException e){
            System.out.println(e.getMessage());
        };
    }

    public void leer(){
        try(FileReader fr = new FileReader(file)){
            int i = fr.read();
            String resultStr = "";
            while(i != -1){
                resultStr+= (char)i;
                i = fr.read();
            }
            System.out.println(resultStr);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
