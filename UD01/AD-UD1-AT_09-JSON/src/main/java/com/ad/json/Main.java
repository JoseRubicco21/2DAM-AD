package com.ad.json;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.ad.json.menu.implementations.MainMenu;
import com.ad.json.models.Producto;
import com.ad.json.models.Proveedor;
import com.ad.json.menu.Menu;
import com.ad.json.menu.exceptions.InvalidInputException;
import com.ad.json.menu.exceptions.InvalidOptionException;

public class Main {
    public static void main(String[] args) {
        
        Producto p1 = new Producto("Disco duro", 
                                   30, 
                                   true, 
                                   new String[]{"hardware", "almacenamiento"}, 
                                   30, 
                                   "Disco duro de 512GB",
                                   new Proveedor("Western digital",
                                   "111-333-4442"));

        Producto p2 = new Producto("Cable usb", 12, false, 0);
        
        List<Producto> prods = new ArrayList<Producto>();
        prods.add(p1);
        prods.add(p2);

        Scanner sc = new Scanner(System.in);
        Menu menu = new MainMenu(sc, prods);

        while (menu.isActive()) {
            try{
                menu.display().choose(sc).execute();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (InvalidOptionException e){
                System.out.println(e.getMessage());
            }
        }
    }
}