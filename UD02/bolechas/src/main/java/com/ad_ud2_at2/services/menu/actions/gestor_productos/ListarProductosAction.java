package com.ad_ud2_at2.services.menu.actions.gestor_productos;

import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ListarProductosAction extends MenuAction {

    private ProductoDAO productoDAO;

    public ListarProductosAction(Scanner sc) {
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== LISTA DE TODOS LOS PRODUCTOS ===");
            
            List<Producto> productos = productoDAO.getAll();
            
            if (productos.isEmpty()) {
                Logger.info("No hay productos registrados");
            } else {
                Logger.info("Total de productos: " + productos.size());
                System.out.println();
                for (Producto producto : productos) {
                    System.out.println(producto);
                }
            }
            
        } catch (Exception e) {
            Logger.error("Error al listar productos: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
}