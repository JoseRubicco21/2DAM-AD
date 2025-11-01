package com.ad_ud2_at2;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.ClienteDAO;
import com.ad_ud2_at2.dao.implementations.ProductoDAO;
import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Cliente;
import com.ad_ud2_at2.models.Producto;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.database.DatabaseService;
import com.ad_ud2_at2.services.logger.Logger;
import com.ad_ud2_at2.services.menu.implementations.MainMenu;
import com.ad_ud2_at2.services.menu.state.MenuState;

public class Main {
    public static void main(String[] args) {
        Logger.info("Starting Bolechas Application...");
        
        // Initialize database
        DatabaseService.initializeDatabase();
        
        // Recreate schema with CASCADE constraints (temporary fix)
        Logger.info("Updating schema to include CASCADE constraints...");
        DatabaseService.recreateSchema();
        
        // Test database connectivity
        if (!DatabaseService.testDatabaseConnectivity()) {
            Logger.error("Database connectivity test failed. Exiting...");
            return;
        }
        
        // Create some sample data if needed
        createSampleDataIfNeeded();
        
        // Start the menu system
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu(scanner);
        mainMenu.setState(MenuState.ACTIVE);
        
        Logger.success("¡Bienvenido al Sistema de Gestión Bolechas!");
        
        while (mainMenu.isActive()) {
            try {
                mainMenu.display().choose(scanner).execute();
            } catch (Exception e) {
                Logger.error("Error en el menú: " + e.getMessage());
            }
        }
        
        scanner.close();
        Logger.success("¡Hasta luego!");
    }
    
    private static void createSampleDataIfNeeded() {
        try {
            // Create DAOs
            ClienteDAO clienteDAO = new ClienteDAO();
            
            // Check if we already have data
            if (clienteDAO.count() > 0) {
                Logger.info("La base de datos ya contiene datos");
                return;
            }
            
            Logger.info("Creando datos de muestra...");
            
            ProductoDAO productoDAO = new ProductoDAO();
            PedidoDAO pedidoDAO = new PedidoDAO();
            
            // Create clientes
            Cliente cliente1 = new Cliente("12345678A", "Juan Pérez");
            Cliente cliente2 = new Cliente("87654321B", "María García");
            
            clienteDAO.save(cliente1);
            clienteDAO.save(cliente2);
            Logger.success("Clientes creados");
            
            // Create productos
            Producto producto1 = new Producto("Laptop", "High-performance laptop", 999.99f);
            Producto producto2 = new Producto("Mouse", "Wireless optical mouse", 25.50f);
            
            productoDAO.save(producto1);
            productoDAO.save(producto2);
            Logger.success("Productos creados");
            
            // Load productos to get their IDs (since they are auto-generated)
            producto1 = productoDAO.searchByNombre("Laptop").get(0);
            producto2 = productoDAO.searchByNombre("Mouse").get(0);
            
            // Create pedidos
            Date today = Date.valueOf(LocalDate.now());
            Pedido pedido1 = new Pedido(today, 2, producto1, cliente1);
            Pedido pedido2 = new Pedido(today, 5, producto2, cliente2);
            
            pedidoDAO.save(pedido1);
            pedidoDAO.save(pedido2);
            Logger.success("Pedidos creados");
            
            Logger.success("Datos de muestra creados exitosamente");
            
        } catch (Exception e) {
            Logger.error("Error al crear datos de muestra: " + e.getMessage());
        }
    }
}