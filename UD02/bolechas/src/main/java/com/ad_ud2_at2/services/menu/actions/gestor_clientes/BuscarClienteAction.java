package com.ad_ud2_at2.services.menu.actions.gestor_clientes;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class BuscarClienteAction extends MenuAction {

    private Scanner sc;
    private ClienteDAO clienteDAO;

    public BuscarClienteAction(Scanner sc) {
        this.sc = sc;
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== BUSCAR CLIENTE ===");
            
            System.out.println("1. Buscar por DNI");
            System.out.println("2. Buscar por nombre");
            System.out.print("Seleccione opción: ");
            
            String opcion = sc.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    buscarPorDni();
                    break;
                case "2":
                    buscarPorNombre();
                    break;
                default:
                    Logger.error("Opción inválida");
                    break;
            }
            
        } catch (Exception e) {
            Logger.error("Error al buscar cliente: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private void buscarPorDni() {
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = sc.nextLine().trim();
        
        Cliente cliente = clienteDAO.get(dni);
        if (cliente != null) {
            Logger.success("Cliente encontrado:");
            System.out.println(cliente);
        } else {
            Logger.warning("No se encontró cliente con DNI: " + dni);
        }
    }
    
    private void buscarPorNombre() {
        System.out.print("Ingrese el nombre (o parte del nombre) del cliente: ");
        String nombre = sc.nextLine().trim();
        
        List<Cliente> clientes = clienteDAO.searchByNombre(nombre);
        if (clientes.isEmpty()) {
            Logger.warning("No se encontraron clientes con el nombre: " + nombre);
        } else {
            Logger.success("Clientes encontrados (" + clientes.size() + "):");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
}