package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ExportarPedidoAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;

    public ExportarPedidoAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== EXPORTAR PEDIDO A JSON ===");
            
            System.out.print("Ingrese el ID del pedido a exportar: ");
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
            
            // Show pedido details
            float total = pedido.getProducto().getPrecio() * pedido.getCantidad();
            
            System.out.println("\n=== PEDIDO A EXPORTAR ===");
            System.out.println("ID: " + pedido.getId());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Cliente: " + pedido.getCliente().getNombre() + " (" + pedido.getCliente().getDni() + ")");
            System.out.println("Producto: " + pedido.getProducto().getNombre());
            System.out.println("Precio unitario: $" + pedido.getProducto().getPrecio());
            System.out.println("Cantidad: " + pedido.getCantidad());
            System.out.println("Total: $" + total);
            
            System.out.print("\n¿Confirmar exportación a JSON? (s/N): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                String fileName = "data/pedido_" + id + ".json";
                
                if (exportarPedidoAJSON(pedido, fileName)) {
                    Logger.success("Pedido exportado exitosamente a: " + fileName);
                } else {
                    Logger.error("Error al exportar el pedido");
                }
            } else {
                Logger.info("Operación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al exportar pedido: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private boolean exportarPedidoAJSON(Pedido pedido, String fileName) {
        try {
            // Create data directory if it doesn't exist
            java.io.File dataDir = new java.io.File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
                Logger.info("Directorio 'data' creado");
            }
            
            // Calculate total
            float total = pedido.getProducto().getPrecio() * pedido.getCantidad();
            
            // Build JSON manually (simple approach without external libraries)
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"pedido\": {\n");
            json.append("    \"id\": ").append(pedido.getId()).append(",\n");
            json.append("    \"fecha\": \"").append(pedido.getFecha()).append("\",\n");
            json.append("    \"cantidad\": ").append(pedido.getCantidad()).append(",\n");
            json.append("    \"total\": ").append(total).append(",\n");
            
            // Cliente information
            json.append("    \"cliente\": {\n");
            json.append("      \"dni\": \"").append(escapeJson(pedido.getCliente().getDni())).append("\",\n");
            json.append("      \"nombre\": \"").append(escapeJson(pedido.getCliente().getNombre())).append("\"\n");
            json.append("    },\n");
            
            // Producto information
            json.append("    \"producto\": {\n");
            json.append("      \"id\": ").append(pedido.getProducto().getId()).append(",\n");
            json.append("      \"nombre\": \"").append(escapeJson(pedido.getProducto().getNombre())).append("\",\n");
            json.append("      \"descripcion\": \"").append(escapeJson(pedido.getProducto().getDescripcion())).append("\",\n");
            json.append("      \"precio\": ").append(pedido.getProducto().getPrecio()).append("\n");
            json.append("    }\n");
            
            json.append("  },\n");
            json.append("  \"metadata\": {\n");
            json.append("    \"exportedAt\": \"").append(java.time.LocalDateTime.now()).append("\",\n");
            json.append("    \"exportedBy\": \"Bolechas System\",\n");
            json.append("    \"version\": \"1.0\"\n");
            json.append("  }\n");
            json.append("}\n");
            
            // Write to file
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(json.toString());
                writer.flush();
            }
            
            return true;
            
        } catch (IOException e) {
            Logger.error("Error al escribir archivo JSON: " + e.getMessage());
            return false;
        }
    }
    
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}