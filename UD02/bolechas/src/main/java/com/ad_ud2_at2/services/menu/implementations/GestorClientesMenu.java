package com.ad_ud2_at2.services.menu.implementations;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.Menu;
import com.ad_ud2_at2.services.menu.actions.ExitMenuAction;
import com.ad_ud2_at2.services.menu.actions.gestor_clientes.CrearClienteAction;
import com.ad_ud2_at2.services.menu.actions.gestor_clientes.ListarClientesAction;
import com.ad_ud2_at2.services.menu.actions.gestor_clientes.BuscarClienteAction;
import com.ad_ud2_at2.services.menu.actions.gestor_clientes.ActualizarClienteAction;
import com.ad_ud2_at2.services.menu.actions.gestor_clientes.EliminarClienteAction;
import com.ad_ud2_at2.services.menu.components.MenuOption;
import com.ad_ud2_at2.services.menu.exceptions.InvalidInputException;
import com.ad_ud2_at2.services.menu.exceptions.InvalidOptionException;

public class GestorClientesMenu extends Menu {

    Scanner sc;
    
    public GestorClientesMenu(Scanner sc){
        this.sc = sc;
        this.addOption(new MenuOption("Crear Cliente", new CrearClienteAction(sc)));
        this.addOption(new MenuOption("Listar Todos los Clientes", new ListarClientesAction(sc)));
        this.addOption(new MenuOption("Buscar Cliente", new BuscarClienteAction(sc)));
        this.addOption(new MenuOption("Actualizar Cliente", new ActualizarClienteAction(sc)));
        this.addOption(new MenuOption("Eliminar Cliente", new EliminarClienteAction(sc)));
        this.addOption(new MenuOption("Volver al Menú Principal", new ExitMenuAction()));
    }

    @Override
    public Menu display() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
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