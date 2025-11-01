package com.ad_ud2_at2.services.menu.actions.gestor_productos;

import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class CrearProductoAction extends MenuAction {

    private Scanner sc;
    private ProductoDAO productoDAO;

    public CrearProductoAction(Scanner sc) {
        this.sc = sc;
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== CREAR NUEVO PRODUCTO ===");
            
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = sc.nextLine().trim();
            
            if (nombre.isEmpty()) {
                Logger.error("El nombre no puede estar vacío");
                return MenuResult.CONTINUE;
            }
            
            System.out.print("Ingrese la descripción del producto: ");
            String descripcion = sc.nextLine().trim();
            
            System.out.print("Ingrese el precio del producto: ");
            String precioStr = sc.nextLine().trim();
            
            float precio;
            try {
                precio = Float.parseFloat(precioStr);
                if (precio < 0) {
                    Logger.error("El precio no puede ser negativo");
                    return MenuResult.CONTINUE;
                }
            } catch (NumberFormatException e) {
                Logger.error("Precio inválido. Debe ser un número decimal");
                return MenuResult.CONTINUE;
            }
            
            Producto producto = new Producto(nombre, descripcion, precio);
            
            if (productoDAO.save(producto)) {
                Logger.success("Producto creado exitosamente: " + producto);
            } else {
                Logger.error("Error al crear el producto");
            }
            
        } catch (Exception e) {
            Logger.error("Error al crear producto: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}