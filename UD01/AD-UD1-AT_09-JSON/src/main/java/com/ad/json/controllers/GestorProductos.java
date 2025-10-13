package com.ad.json.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.ad.json.models.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GestorProductos {
    

    public static void saveToJson(ArrayList<Producto> productos){
        try(FileWriter fw = new FileWriter(manageCreationOfJsonFile())){
            GsonBuilder gBuilder = new GsonBuilder().setPrettyPrinting();
            Gson gson = gBuilder.create();
            gson.toJson(productos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Manage the creation of the productos.json file in the resources folder.
    private static File manageCreationOfJsonFile() throws IOException{
        Path jsonFilePath = Paths.get("resources", "productos.json");
        File jsonFile = jsonFilePath.toFile();

        if(!jsonFile.exists()){
            jsonFile.mkdirs();
            jsonFile.createNewFile();
        }

        return jsonFile;
    }
}
