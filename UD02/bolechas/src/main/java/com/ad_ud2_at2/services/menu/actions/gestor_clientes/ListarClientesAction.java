package com.ad_ud2_at2.services.menu.actions.gestor_clientes;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ListarClientesAction extends MenuAction {

    private Scanner sc;
    private ClienteDAO clienteDAO;

    public ListarClientesAction(Scanner sc) {
        this.sc = sc;
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== LISTA DE TODOS LOS CLIENTES ===");
            
            List<Cliente> clientes = clienteDAO.getAll();
            
            if (clientes.isEmpty()) {
                Logger.info("No hay clientes registrados");
            } else {
                Logger.info("Total de clientes: " + clientes.size());
                System.out.println();
                for (Cliente cliente : clientes) {
                    System.out.println(cliente);
                }
            }
            
        } catch (Exception e) {
            Logger.error("Error al listar clientes: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}