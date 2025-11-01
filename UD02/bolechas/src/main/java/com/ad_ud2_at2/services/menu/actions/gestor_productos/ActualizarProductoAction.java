package com.ad_ud2_at2.services.menu.actions.gestor_productos;

import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ActualizarProductoAction extends MenuAction {

    private Scanner sc;
    private ProductoDAO productoDAO;

    public ActualizarProductoAction(Scanner sc) {
        this.sc = sc;
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== ACTUALIZAR PRODUCTO ===");
            
            System.out.print("Ingrese el ID del producto a actualizar: ");
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
            
            Logger.info("Producto actual: " + producto);
            
            System.out.print("Ingrese el nuevo nombre (actual: " + producto.getNombre() + ") [Enter para mantener]: ");
            String nuevoNombre = sc.nextLine().trim();
            
            System.out.print("Ingrese la nueva descripción (actual: " + producto.getDescripcion() + ") [Enter para mantener]: ");
            String nuevaDescripcion = sc.nextLine().trim();
            
            System.out.print("Ingrese el nuevo precio (actual: " + producto.getPrecio() + ") [Enter para mantener]: ");
            String nuevoPrecioStr = sc.nextLine().trim();
            
            boolean cambiosRealizados = false;
            
            if (!nuevoNombre.isEmpty()) {
                producto.setNombre(nuevoNombre);
                cambiosRealizados = true;
            }
            
            if (!nuevaDescripcion.isEmpty()) {
                producto.setDescripcion(nuevaDescripcion);
                cambiosRealizados = true;
            }
            
            if (!nuevoPrecioStr.isEmpty()) {
                try {
                    float nuevoPrecio = Float.parseFloat(nuevoPrecioStr);
                    if (nuevoPrecio < 0) {
                        Logger.error("El precio no puede ser negativo");
                        return MenuResult.CONTINUE;
                    }
                    producto.setPrecio(nuevoPrecio);
                    cambiosRealizados = true;
                } catch (NumberFormatException e) {
                    Logger.error("Precio inválido. Debe ser un número decimal");
                    return MenuResult.CONTINUE;
                }
            }
            
            if (cambiosRealizados) {
                try {
                    productoDAO.update(producto);
                    Logger.success("Producto actualizado exitosamente: " + producto);
                } catch (Exception e) {
                    Logger.error("Error al actualizar producto: " + e.getMessage());
                }
            } else {
                Logger.info("No se realizaron cambios");
            }
            
        } catch (Exception e) {
            Logger.error("Error al actualizar producto: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}