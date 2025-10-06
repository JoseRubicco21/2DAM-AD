package ejercicio_2;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    
    private Path personaFile;


    

    public PersonaController(){
        this.personaFile = Paths.get("src","ejercicio_2","personas.bin");
    }

    public void guardarPersonas(Persona ...p){
        try(FileOutputStream fo = new FileOutputStream(personaFile.toString());
            ObjectOutputStream os = new ObjectOutputStream(fo)){
            
            List<Persona> personas = new ArrayList<>(List.of(p));
            
            personas.forEach((ps) -> {
                try{
                    os.writeObject(ps);
                } catch(IOException e){
                    System.err.println(e.getMessage());
                }
            });
            
            os.flush();
            os.close();
        
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Persona> leerPersonas(Path file){
        ArrayList<Persona> personas = new ArrayList<>();
        
        try(ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file.toString()))){
            Persona p;
            while((p = (Persona) oi.readObject()) != null){
                personas.add(p);
            }       
        } catch ( ClassNotFoundException e){
            System.err.println(e.getMessage());
        } catch ( EOFException e) {
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return personas;
    }

    public Path getPersonaFile() {
        return personaFile;
    }

    public void setPersonaFile(Path personaFile) {
        this.personaFile = personaFile;
    }
}
