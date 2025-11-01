package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ListarPedidosAction extends MenuAction {

    private PedidoDAO pedidoDAO;

    public ListarPedidosAction(Scanner sc) {
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== LISTA DE TODOS LOS PEDIDOS ===");
            
            List<Pedido> pedidos = pedidoDAO.getAll();
            
            if (pedidos.isEmpty()) {
                Logger.info("No hay pedidos registrados");
            } else {
                Logger.info("Total de pedidos: " + pedidos.size());
                System.out.println();
                
                // Calculate total value
                float totalGeneral = 0;
                
                for (Pedido pedido : pedidos) {
                    float totalPedido = pedido.getProducto().getPrecio() * pedido.getCantidad();
                    totalGeneral += totalPedido;
                    
                    System.out.println("═══════════════════════════════════════");
                    System.out.println("ID: " + pedido.getId());
                    System.out.println("Fecha: " + pedido.getFecha());
                    System.out.println("Cliente: " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getDni() + ")");
                    System.out.println("Producto: " + pedido.getProducto().getNombre());
                    System.out.println("Precio unitario: $" + pedido.getProducto().getPrecio());
                    System.out.println("Cantidad: " + pedido.getCantidad());
                    System.out.println("Total: $" + totalPedido);
                }
                
                System.out.println("═══════════════════════════════════════");
                System.out.println("TOTAL GENERAL: $" + totalGeneral);
            }
            
        } catch (Exception e) {
            Logger.error("Error al listar pedidos: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}