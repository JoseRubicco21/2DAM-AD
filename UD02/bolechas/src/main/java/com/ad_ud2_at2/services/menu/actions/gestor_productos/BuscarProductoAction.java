package com.ad_ud2_at2.services.menu.actions.gestor_productos;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class BuscarProductoAction extends MenuAction {

    private Scanner sc;
    private ProductoDAO productoDAO;

    public BuscarProductoAction(Scanner sc) {
        this.sc = sc;
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== BUSCAR PRODUCTO ===");
            
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por nombre");
            System.out.print("Seleccione opción: ");
            
            String opcion = sc.nextLine().trim();
            
            switch (opcion) {
                case "1":
                    buscarPorId();
                    break;
                case "2":
                    buscarPorNombre();
                    break;
                default:
                    Logger.error("Opción inválida");
                    break;
            }
            
        } catch (Exception e) {
            Logger.error("Error al buscar producto: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private void buscarPorId() {
        System.out.print("Ingrese el ID del producto: ");
        String idStr = sc.nextLine().trim();
        
        try {
            int id = Integer.parseInt(idStr);
            
            Producto producto = productoDAO.get(id);
            if (producto != null) {
                Logger.success("Producto encontrado:");
                System.out.println(producto);
            } else {
                Logger.warning("No se encontró producto con ID: " + id);
            }
        } catch (NumberFormatException e) {
            Logger.error("ID inválido. Debe ser un número entero");
        }
    }
    
    private void buscarPorNombre() {
        System.out.print("Ingrese el nombre (o parte del nombre) del producto: ");
        String nombre = sc.nextLine().trim();
        
        List<Producto> productos = productoDAO.searchByNombre(nombre);
        if (productos.isEmpty()) {
            Logger.warning("No se encontraron productos con el nombre: " + nombre);
        } else {
            Logger.success("Productos encontrados (" + productos.size() + "):");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }
}