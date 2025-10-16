package com.ad.json.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.ad.json.models.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class GestorProductos {

    public static void saveToJson(List<Producto> productos) {
        try (FileWriter fw = new FileWriter(manageCreationOfJsonFile())) {
            GsonBuilder gBuilder = new GsonBuilder().setPrettyPrinting();
            Gson gson = gBuilder.create();
            JsonElement elem = gson.toJsonTree(productos);
            gson.toJson(elem, fw);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Manage the creation of the productos.json file in the resources folder.
    private static File manageCreationOfJsonFile() throws IOException {
        File jsonFile = getProductsJson();

        if (!jsonFile.exists()) {
            jsonFile.getParentFile().mkdirs();
            jsonFile.createNewFile();
        }

        return jsonFile;
    }

    private static File getProductsJson() {
        Path jsonFilePath = Paths.get("src", "main","resources", "productos.json");
        File jsonFile = jsonFilePath.toFile();

        return jsonFile;
    }

    public static void readAndDisplayJson() {
        List<Producto> productos = readProductos();
        displayProductos(productos);
    }

    public static List<Producto> readProductos() {
        List<Producto> productos = new ArrayList<>();
        try (FileReader fr = new FileReader(getProductsJson())) {
            Gson gson = new Gson();
            // "Black magic" -- It creates a token type of type T defined by the user, initializes a base instance to get type w/ reflection
            // This only works if we know what type of value we wrote in the json.
            Type prodListType = new TypeToken<ArrayList<Producto>>() {}.getType();
            productos = gson.fromJson(fr, prodListType);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public static void displayProductos(List<Producto> prods) {
        prods.forEach(System.out::println);
    }
}
