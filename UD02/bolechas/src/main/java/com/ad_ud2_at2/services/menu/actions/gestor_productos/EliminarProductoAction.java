package com.ad_ud2_at2.services.menu.actions.gestor_productos;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class EliminarProductoAction extends MenuAction {

    private Scanner sc;
    private ProductoDAO productoDAO;
    private PedidoDAO pedidoDAO;

    public EliminarProductoAction(Scanner sc) {
        this.sc = sc;
        this.productoDAO = new ProductoDAO();
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ELIMINAR PRODUCTO ===");
            
            System.out.print("Ingrese el ID del producto a eliminar: ");
            String idStr = sc.nextLine().trim();
            
            int id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                Logger.error("ID inválido. Debe ser un número entero");
                return MenuResult.CONTINUE;
            }
            
            Producto producto = productoDAO.get(id);
            if (producto == null) {
                Logger.error("No se encontró producto con ID: " + id);
                return MenuResult.CONTINUE;
            }
            
            // Check for related pedidos
            List<Pedido> pedidosRelacionados = pedidoDAO.getByProductoId(id);
            
            Logger.info("Producto a eliminar: " + producto);
            
            if (!pedidosRelacionados.isEmpty()) {
                Logger.warning("Este producto tiene " + pedidosRelacionados.size() + " pedido(s) asociado(s):");
                for (Pedido pedido : pedidosRelacionados) {
                    System.out.println("  - Pedido ID: " + pedido.getId() + ", Cliente: " + pedido.getCliente().getNombre() + 
                                     ", Cantidad: " + pedido.getCantidad());
                }
                System.out.println("\nOpciones:");
                System.out.println("1. Eliminar producto y todos sus pedidos (CASCADE)");
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
                System.out.print("¿Está seguro de que desea eliminar este producto? (s/N): ");
                String confirmacion = sc.nextLine().trim().toLowerCase();
                
                if (!confirmacion.equals("s") && !confirmacion.equals("si")) {
                    Logger.info("Operación cancelada");
                    return MenuResult.CONTINUE;
                }
            }
            
            // Now delete the producto
            if (productoDAO.deleteById(id)) {
                Logger.success("Producto eliminado exitosamente");
            } else {
                Logger.error("Error al eliminar el producto");
            }
            
        } catch (Exception e) {
            Logger.error("Error al eliminar producto: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}