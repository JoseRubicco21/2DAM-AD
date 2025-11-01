package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class BuscarPedidoAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;

    public BuscarPedidoAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== BUSCAR PEDIDO ===");
            
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por DNI de cliente");
            System.out.println("3. Buscar por ID de producto");
            System.out.print("Seleccione opción: ");
            
            String opcion = sc.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    buscarPorId();
                    break;
                case "2":
                    buscarPorDniCliente();
                    break;
                case "3":
                    buscarPorIdProducto();
                    break;
                default:
                    Logger.error("Opción inválida");
                    break;
            }
            
        } catch (Exception e) {
            Logger.error("Error al buscar pedido: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private void buscarPorId() {
        System.out.print("Ingrese el ID del pedido: ");
        String idStr = sc.nextLine().trim();
        
        try {
            int id = Integer.parseInt(idStr);
            
            Pedido pedido = pedidoDAO.get(id);
            if (pedido != null) {
                Logger.success("Pedido encontrado:");
                mostrarPedidoDetallado(pedido);
            } else {
                Logger.warning("No se encontró pedido con ID: " + id);
            }
        } catch (NumberFormatException e) {
            Logger.error("ID inválido. Debe ser un número entero");
        }
    }
    
    private void buscarPorDniCliente() {
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = sc.nextLine().trim();
        
        List<Pedido> pedidos = pedidoDAO.getByClienteDni(dni);
        if (pedidos.isEmpty()) {
            Logger.warning("No se encontraron pedidos para el cliente con DNI: " + dni);
        } else {
            Logger.success("Pedidos encontrados para cliente (" + pedidos.size() + "):");
            for (Pedido pedido : pedidos) {
                mostrarPedidoDetallado(pedido);
                System.out.println("───────────────────────────────────────");
            }
        }
    }
    
    private void buscarPorIdProducto() {
        System.out.print("Ingrese el ID del producto: ");
        String idStr = sc.nextLine().trim();
        
        try {
            int id = Integer.parseInt(idStr);
            
            List<Pedido> pedidos = pedidoDAO.getByProductoId(id);
            if (pedidos.isEmpty()) {
                Logger.warning("No se encontraron pedidos para el producto con ID: " + id);
            } else {
                Logger.success("Pedidos encontrados para producto (" + pedidos.size() + "):");
                for (Pedido pedido : pedidos) {
                    mostrarPedidoDetallado(pedido);
                    System.out.println("───────────────────────────────────────");
                }
            }
        } catch (NumberFormatException e) {
            Logger.error("ID inválido. Debe ser un número entero");
        }
    }
    
    private void mostrarPedidoDetallado(Pedido pedido) {
        float total = pedido.getProducto().getPrecio() * pedido.getCantidad();
        
        System.out.println("ID: " + pedido.getId());
        System.out.println("Fecha: " + pedido.getFecha());
        System.out.println("Cliente: " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getDni() + ")");
        System.out.println("Producto: " + pedido.getProducto().getNombre());
        System.out.println("Precio unitario: $" + pedido.getProducto().getPrecio());
        System.out.println("Cantidad: " + pedido.getCantidad());
        System.out.println("Total: $" + total);
    }
}