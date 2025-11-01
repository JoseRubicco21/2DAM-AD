package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class EliminarPedidoAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;

    public EliminarPedidoAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ELIMINAR PEDIDO ===");
            
            System.out.print("Ingrese el ID del pedido a eliminar: ");
            String idStr = sc.nextLine().trim();
            
            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                Logger.error("ID inválido. Debe ser un número entero");
                return MenuResult.CONTINUE;
            }
            
            Pedido pedido = pedidoDAO.get(id);
            if (pedido == null) {
                Logger.error("No se encontró pedido con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            // Show pedido details
            float total = pedido.getProducto().getPrecio() * pedido.getCantidad();
            
            System.out.println("\n=== PEDIDO A ELIMINAR ===");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Cliente: " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getDni() + ")");
            System.out.println("Producto: " + pedido.getProducto().getNombre());
            System.out.println("Precio unitario: $" + pedido.getProducto().getPrecio());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.println("Total: $" + total);
            
            System.out.print("\n¿Está seguro de que desea eliminar este pedido? (s/N): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                if (pedidoDAO.deleteById(id)) {
                    Logger.success("Pedido eliminado exitosamente");
                } else {
                    Logger.error("Error al eliminar el pedido");
                }
            } else {
                Logger.info("Operación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al eliminar pedido: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}