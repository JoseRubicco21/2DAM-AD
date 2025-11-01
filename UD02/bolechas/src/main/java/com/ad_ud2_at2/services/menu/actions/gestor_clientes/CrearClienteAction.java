package com.ad_ud2_at2.services.menu.actions.gestor_clientes;

import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class CrearClienteAction extends MenuAction {

    private Scanner sc;
    private ClienteDAO clienteDAO;

    public CrearClienteAction(Scanner sc) {
        this.sc = sc;
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== CREAR NUEVO CLIENTE ===");
            
            System.out.print("Ingrese el DNI del cliente: ");
            String dni = sc.nextLine().trim();
            
            // Verificar si el cliente ya existe
            if (clienteDAO.exists(dni)) {
                Logger.warning("Ya existe un cliente con el DNI: " + dni);
                return MenuResult.CONTINUE;
            }
            
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = sc.nextLine().trim();
            
            if (nombre.isEmpty()) {
                Logger.error("El nombre no puede estar vacío");
                return MenuResult.CONTINUE;
            }
            
            Cliente cliente = new Cliente(dni, nombre);
            
            if (clienteDAO.save(cliente)) {
                Logger.success("Cliente creado exitosamente: " + cliente);
            } else {
                Logger.error("Error al crear el cliente");
            }
            
        } catch (Exception e) {
            Logger.error("Error al crear cliente: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}