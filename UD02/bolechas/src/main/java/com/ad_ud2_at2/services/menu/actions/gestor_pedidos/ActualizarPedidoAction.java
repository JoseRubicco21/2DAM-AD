package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ActualizarPedidoAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;

    public ActualizarPedidoAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
        this.clienteDAO = new ClienteDAO();
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ACTUALIZAR PEDIDO ===");
            
            System.out.print("Ingrese el ID del pedido a actualizar: ");
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
            
            // Show current pedido
            float totalActual = pedido.getProducto().getPrecio() * pedido.getCantidad();
            System.out.println("\n=== PEDIDO ACTUAL ===");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Cliente: " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getDni() + ")");
            System.out.println("Producto: " + pedido.getProducto().getNombre() + " - $" + pedido.getProducto().getPrecio());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.println("Total: $" + totalActual);
            
            boolean cambiosRealizados = false;
            
            // Update fecha
            System.out.print("\nIngrese la nueva fecha (yyyy-mm-dd) [Enter para mantener " + pedido.getFecha() + "]: ");
            String nuevaFechaStr = sc.nextLine().trim();
            
            if (!nuevaFechaStr.isEmpty()) {
                try {
                    LocalDate localDate = LocalDate.parse(nuevaFechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    pedido.setFecha(Date.valueOf(localDate));
                    cambiosRealizados = true;
                } catch (DateTimeParseException e) {
                    Logger.error("Fecha inválida. Use el formato yyyy-mm-dd");
                    return MenuResult.CONTINUE;
                }
            }
            
            // Update cantidad
            System.out.print("Ingrese la nueva cantidad [Enter para mantener " + pedido.getCantidad() + "]: ");
            String nuevaCantidadStr = sc.nextLine().trim();
            
            if (!nuevaCantidadStr.isEmpty()) {
                try {
                    int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                    if (nuevaCantidad <= 0) {
                        Logger.error("La cantidad debe ser mayor que 0");
                        return MenuResult.CONTINUE;
                    }
                    pedido.setCantidad(nuevaCantidad);
                    cambiosRealizados = true;
                } catch (NumberFormatException e) {
                    Logger.error("Cantidad inválida. Debe ser un número entero positivo");
                    return MenuResult.CONTINUE;
                }
            }
            
            // Update cliente
            System.out.print("Ingrese el nuevo DNI del cliente [Enter para mantener " + pedido.getCliente().getDni() + "]: ");
            String nuevoDni = sc.nextLine().trim();
            
            if (!nuevoDni.isEmpty()) {
                Cliente nuevoCliente = clienteDAO.get(nuevoDni);
                if (nuevoCliente == null) {
                    Logger.error("No se encontró cliente con DNI: " + nuevoDni);
                    return MenuResult.CONTINUE;
                }
                pedido.setCliente(nuevoCliente);
                cambiosRealizados = true;
                Logger.info("Nuevo cliente: " + nuevoCliente.getNombre());
            }
            
            // Update producto
            System.out.print("Ingrese el nuevo ID del producto [Enter para mantener " + pedido.getProducto().getId() + "]: ");
            String nuevoIdProductoStr = sc.nextLine().trim();
            
            if (!nuevoIdProductoStr.isEmpty()) {
                try {
                    int nuevoIdProducto = Integer.parseInt(nuevoIdProductoStr);
                    Producto nuevoProducto = productoDAO.get(nuevoIdProducto);
                    if (nuevoProducto == null) {
                        Logger.error("No se encontró producto con ID: " + nuevoIdProducto);
                        return MenuResult.CONTINUE;
                    }
                    pedido.setProducto(nuevoProducto);
                    cambiosRealizados = true;
                    Logger.info("Nuevo producto: " + nuevoProducto.getNombre() + " - $" + nuevoProducto.getPrecio());
                } catch (NumberFormatException e) {
                    Logger.error("ID de producto inválido. Debe ser un número entero");
                    return MenuResult.CONTINUE;
                }
            }
            
            if (cambiosRealizados) {
                // Show new total
                float nuevoTotal = pedido.getProducto().getPrecio() * pedido.getCantidad();
                System.out.println("\n=== NUEVO TOTAL: $" + nuevoTotal + " ===");
                
                System.out.print("¿Confirmar actualización del pedido? (s/N): ");
                String confirmacion = sc.nextLine().trim().toLowerCase();
                
                if (confirmacion.equals("s") || confirmacion.equals("si")) {
                    try {
                        pedidoDAO.update(pedido);
                        Logger.success("Pedido actualizado exitosamente");
                    } catch (Exception e) {
                        Logger.error("Error al actualizar pedido: " + e.getMessage());
                    }
                } else {
                    Logger.info("Operación cancelada");
                }
            } else {
                Logger.info("No se realizaron cambios");
            }
            
        } catch (Exception e) {
            Logger.error("Error al actualizar pedido: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}