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

import com.ad.json.models.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GestorProductos {

    public static void saveToJson(ArrayList<Producto> productos) {
        try (FileWriter fw = new FileWriter(manageCreationOfJsonFile())) {
            GsonBuilder gBuilder = new GsonBuilder().setPrettyPrinting();
            Gson gson = gBuilder.create();
            gson.toJsonTree(productos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Manage the creation of the productos.json file in the resources folder.
    private static File manageCreationOfJsonFile() throws IOException {
        File jsonFile = getProductsJson();

        if (!jsonFile.exists()) {
            jsonFile.mkdirs();
            jsonFile.createNewFile();
        }

        return jsonFile;
    }

    private static File getProductsJson() {
        Path jsonFilePath = Paths.get("resources", "productos.json");
        File jsonFile = jsonFilePath.toFile();

        return jsonFile;
    }

    public static void readAndDisplayJson() {
        ArrayList<Producto> productos = readProductos();
        displayProductos(productos);
    }

    public static ArrayList<Producto> readProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try (FileReader fr = new FileReader(getProductsJson())) {
            Gson gson = new Gson();
            Type prodListType = new TypeToken<ArrayList<Producto>>() {
            }.getType();
            productos = gson.fromJson(fr, prodListType);
            return productos;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public static void displayProductos(ArrayList<Producto> prods) {
        prods.forEach(System.out::println);
    }
}
