package com.ad_ud2_at2.services.menu.implementations;

import java.util.Scanner;

import com.ad_ud2_at2.services.menu.Menu;
import com.ad_ud2_at2.services.menu.actions.ExitMenuAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.CrearPedidoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.ListarPedidosAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.BuscarPedidoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.ActualizarPedidoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.EliminarPedidoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.ExportarPedidoAction;
import com.ad_ud2_at2.services.menu.actions.gestor_pedidos.ExportarTodosPedidosAction;
import com.ad_ud2_at2.services.menu.components.MenuOption;
import com.ad_ud2_at2.services.menu.exceptions.InvalidInputException;
import com.ad_ud2_at2.services.menu.exceptions.InvalidOptionException;

public class GestorPedidosMenu extends Menu {

    Scanner sc;
    
    public GestorPedidosMenu(Scanner sc){
        this.sc = sc;
        this.addOption(new MenuOption("Crear Pedido", new CrearPedidoAction(sc)));
        this.addOption(new MenuOption("Listar Todos los Pedidos", new ListarPedidosAction(sc)));
        this.addOption(new MenuOption("Buscar Pedido", new BuscarPedidoAction(sc)));
        this.addOption(new MenuOption("Actualizar Pedido", new ActualizarPedidoAction(sc)));
        this.addOption(new MenuOption("Eliminar Pedido", new EliminarPedidoAction(sc)));
        this.addOption(new MenuOption("Exportar Pedido a JSON", new ExportarPedidoAction(sc)));
        this.addOption(new MenuOption("Exportar Todos los Pedidos a JSON", new ExportarTodosPedidosAction(sc)));
        this.addOption(new MenuOption("Volver al Menú Principal", new ExitMenuAction()));
    }

    @Override
    public Menu display() {
        System.out.println("\n=== GESTIÓN DE PEDIDOS ===");
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