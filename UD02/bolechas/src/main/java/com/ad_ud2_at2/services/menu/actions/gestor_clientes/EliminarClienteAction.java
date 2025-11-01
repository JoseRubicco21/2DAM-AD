package com.ad_ud2_at2.services.menu.actions.gestor_clientes;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class EliminarClienteAction extends MenuAction {

    private Scanner sc;
    private ClienteDAO clienteDAO;
    private PedidoDAO pedidoDAO;

    public EliminarClienteAction(Scanner sc) {
        this.sc = sc;
        this.clienteDAO = new ClienteDAO();
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ELIMINAR CLIENTE ===");
            
            System.out.print("Ingrese el DNI del cliente a eliminar: ");
            String dni = sc.nextLine().trim();
            
            Cliente cliente = clienteDAO.get(dni);
            if (cliente == null) {
                Logger.error("No se encontró cliente con DNI: " + dni);
                return MenuResult.CONTINUE;
            }
            
            // Check for related pedidos
            List<Pedido> pedidosRelacionados = pedidoDAO.getByClienteDni(dni);
            
            Logger.info("Cliente a eliminar: " + cliente);
            
            if (!pedidosRelacionados.isEmpty()) {
                Logger.warning("Este cliente tiene " + pedidosRelacionados.size() + " pedido(s) asociado(s):");
                for (Pedido pedido : pedidosRelacionados) {
                    System.out.println("  - Pedido ID: " + pedido.getId() + ", Fecha: " + pedido.getFecha() + 
                                     ", Producto: " + pedido.getProducto().getNombre());
                }
                System.out.println("\nOpciones:");
                System.out.println("1. Eliminar cliente y todos sus pedidos (CASCADE)");
                System.out.println("2. Cancelar operación");
                System.out.print("Seleccione una opción (1-2): ");
                
                String opcion = sc.nextLine().trim();
                
                if (!opcion.equals("1")) {
                    Logger.info("Operación cancelada");
                    return MenuResult.CONTINUE;
                }
                
                // Delete associated pedidos first
                Logger.info("Eliminando pedidos asociados...");
                for (Pedido pedido : pedidosRelacionados) {
                    if (pedidoDAO.delete(pedido)) {
                        Logger.info("Pedido eliminado: ID " + pedido.getId());
                    } else {
                        Logger.error("Error al eliminar pedido ID: " + pedido.getId());
                    }
                }
            } else {
                System.out.print("¿Está seguro de que desea eliminar este cliente? (s/N): ");
                String confirmacion = sc.nextLine().trim().toLowerCase();
                
                if (!confirmacion.equals("s") && !confirmacion.equals("si")) {
                    Logger.info("Operación cancelada");
                    return MenuResult.CONTINUE;
                }
            }
            
            // Now delete the cliente
            if (clienteDAO.deleteById(dni)) {
                Logger.success("Cliente eliminado exitosamente");
            } else {
                Logger.error("Error al eliminar el cliente");
            }
            
        } catch (Exception e) {
            Logger.error("Error al eliminar cliente: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}