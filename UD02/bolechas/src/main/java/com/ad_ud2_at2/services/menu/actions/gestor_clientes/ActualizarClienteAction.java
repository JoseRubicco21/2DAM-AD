package com.ad_ud2_at2.services.menu.actions.gestor_clientes;

import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ActualizarClienteAction extends MenuAction {

    private Scanner sc;
    private ClienteDAO clienteDAO;

    public ActualizarClienteAction(Scanner sc) {
        this.sc = sc;
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ACTUALIZAR CLIENTE ===");
            
            System.out.print("Ingrese el DNI del cliente a actualizar: ");
            String dni = sc.nextLine().trim();
            
            Cliente cliente = clienteDAO.get(dni);
            if (cliente == null) {
                Logger.error("No se encontró cliente con DNI: " + dni);
                return MenuResult.CONTINUE;
            }
            
            Logger.info("Cliente actual: " + cliente);
            
            System.out.print("Ingrese el nuevo nombre (actual: " + cliente.getNombre() + "): ");
            String nuevoNombre = sc.nextLine().trim();
            
            if (!nuevoNombre.isEmpty()) {
                cliente.setNombre(nuevoNombre);
                
                try {
                    clienteDAO.update(cliente);
                    Logger.success("Cliente actualizado exitosamente: " + cliente);
                } catch (Exception e) {
                    Logger.error("Error al actualizar cliente: " + e.getMessage());
                }
            } else {
                Logger.info("No se realizaron cambios");
            }
            
        } catch (Exception e) {
            Logger.error("Error al actualizar cliente: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}