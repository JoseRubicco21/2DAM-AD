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

public class CrearPedidoAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;
    private ProductoDAO productoDAO;

    public CrearPedidoAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
        this.clienteDAO = new ClienteDAO();
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== CREAR NUEVO PEDIDO ===");
            
            // Get cliente
            System.out.print("Ingrese el DNI del cliente: ");
            String dniCliente = sc.nextLine().trim();
            
            Cliente cliente = clienteDAO.get(dniCliente);
            if (cliente == null) {
                Logger.error("No se encontró cliente con DNI: " + dniCliente);
                return MenuResult.CONTINUE;
            }
            Logger.info("Cliente seleccionado: " + cliente.getNombre());
            
            // Get producto
            System.out.print("Ingrese el ID del producto: ");
            String idProductoStr = sc.nextLine().trim();
            
            int idProducto;
            try {
                idProducto = Integer.parseInt(idProductoStr);
            } catch (NumberFormatException e) {
                Logger.error("ID de producto inválido. Debe ser un número entero");
                return MenuResult.CONTINUE;
            }
            
            Producto producto = productoDAO.get(idProducto);
            if (producto == null) {
                Logger.error("No se encontró producto con ID: " + idProducto);
                return MenuResult.CONTINUE;
            }
            Logger.info("Producto seleccionado: " + producto.getNombre() + " - $" + producto.getPrecio());
            
            // Get cantidad
            System.out.print("Ingrese la cantidad: ");
            String cantidadStr = sc.nextLine().trim();
            
            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    Logger.error("La cantidad debe ser mayor que 0");
                    return MenuResult.CONTINUE;
                }
            } catch (NumberFormatException e) {
                Logger.error("Cantidad inválida. Debe ser un número entero positivo");
                return MenuResult.CONTINUE;
            }
            
            // Get fecha
            System.out.print("Ingrese la fecha (yyyy-mm-dd) [Enter para fecha actual]: ");
            String fechaStr = sc.nextLine().trim();
            
            Date fecha;
            if (fechaStr.isEmpty()) {
                fecha = Date.valueOf(LocalDate.now());
            } else {
                try {
                    LocalDate localDate = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    fecha = Date.valueOf(localDate);
                } catch (DateTimeParseException e) {
                    Logger.error("Fecha inválida. Use el formato yyyy-mm-dd");
                    return MenuResult.CONTINUE;
                }
            }
            
            // Calculate total
            float total = producto.getPrecio() * cantidad;
            
            // Show summary
            System.out.println("\n=== RESUMEN DEL PEDIDO ===");
            System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getDni() + ")");
            System.out.println("Producto: " + producto.getNombre());
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Precio unitario: $" + producto.getPrecio());
            System.out.println("Total: $" + total);
            System.out.println("Fecha: " + fecha);
            
            System.out.print("\n¿Confirmar creación del pedido? (s/N): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                Pedido pedido = new Pedido(fecha, cantidad, producto, cliente);
                
                if (pedidoDAO.save(pedido)) {
                    Logger.success("Pedido creado exitosamente");
                } else {
                    Logger.error("Error al crear el pedido");
                }
            } else {
                Logger.info("Operación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al crear pedido: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}