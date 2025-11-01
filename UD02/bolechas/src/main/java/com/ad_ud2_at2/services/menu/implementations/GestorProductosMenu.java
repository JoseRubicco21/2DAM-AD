package com.ad_ud2_at2.services.menu.implementations;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.Menu;
import com.ad_ud2_at2.services.menu.actions.ExitMenuAction;
import com.ad_ud2_at2.services.menu.actions.gestor_productos.CrearProductoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_productos.ListarProductosAction;
import com.ad_ud2_at2.services.menu.actions.gestor_productos.BuscarProductoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_productos.ActualizarProductoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_productos.EliminarProductoAction;
import com.ad_ud2_at2.services.menu.components.MenuOption;
import com.ad_ud2_at2.services.menu.exceptions.InvalidInputException;
import com.ad_ud2_at2.services.menu.exceptions.InvalidOptionException;

public class GestorProductosMenu extends Menu {

    Scanner sc;
    
    public GestorProductosMenu(Scanner sc){
        this.sc = sc;
        this.addOption(new MenuOption("Crear Producto", new CrearProductoAction(sc)));
        this.addOption(new MenuOption("Listar Todos los Productos", new ListarProductosAction(sc)));
        this.addOption(new MenuOption("Buscar Producto", new BuscarProductoAction(sc)));
        this.addOption(new MenuOption("Actualizar Producto", new ActualizarProductoAction(sc)));
        this.addOption(new MenuOption("Eliminar Producto", new EliminarProductoAction(sc)));
        this.addOption(new MenuOption("Volver al Menú Principal", new ExitMenuAction()));
    }

    @Override
    public Menu display() {
        System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("[%d]: %s%n", i, this.options.get(i).getDescription());
        }
        return this;
    }

    @Override
    public int validateInput(String str) throws InvalidOptionException, InvalidInputException {
        if(!str.matches("\\b\\d+\\b")) throw new InvalidInputException("El dato no corresponde al formato estipulado.");
        int option = Integer.parseInt(str);
        if(option >= options.size() || option < 0) throw new InvalidOptionException("Opción invalida del menu.");
        return option;
    }
}