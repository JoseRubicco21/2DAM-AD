package models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Clasificacion {
    List<Equipo> equipos;

    public Clasificacion(Equipo... equipos) {
        this.equipos = Arrays.asList(equipos);
    }

    public Clasificacion() {
        this.equipos = Arrays.asList(new Equipo[18]);
    }

    public void addEquipo(Equipo e) {
        for (int i = 0; i < this.equipos.size(); i++) {
            if (this.equipos.get(i) == null) {
                this.equipos.set(i, e);
                return;
            }
        }
    }

    public void removeEquipo(Equipo e) {
        for (int i = 0; i < this.equipos.size(); i++) {
            if (this.equipos.get(i) != null  && this.equipos.get(i).equals(e)) {
                this.equipos.set(i, null);
                return;
            }
        }
    }

    public static Clasificacion loadClasificacion(){

        Clasificacion loadedClasificacion = new Clasificacion();
        File dataFile =  Paths.get("datos", "clasificacion.dat").toFile();
        
        try(FileInputStream fis = new FileInputStream(dataFile);
        ObjectInputStream objIn = new ObjectInputStream(fis)){
            
            Equipo eq;
            while ((eq = (Equipo) objIn.readObject()) !=null) {
                loadedClasificacion.addEquipo(eq);
            }

            return loadedClasificacion;
        } catch (EOFException ex){
            System.out.println("Carga finalizada");
        }  catch(IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());  
        }
        return loadedClasificacion;
    }

    public static void saveClasificacion(List<Equipo> eq) throws IOException {
        File dataFile = Paths.get("datos", "clasificacion.dat").toFile();


        try (FileOutputStream fos = new FileOutputStream(dataFile, false);
                ObjectOutputStream objOut = new ObjectOutputStream(fos)) {

            Equipo values[] = eq.stream()
                    .filter(e -> e != null)
                    .sorted()
                    .toArray(Equipo[]::new);

            for (Equipo equipo : values) {
                objOut.writeObject(equipo);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}
