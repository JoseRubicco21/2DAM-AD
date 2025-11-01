package com.ad_ud2_at2.services.menu.actions.gestor_pedidos;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.ad_ud2_at2.dao.implementations.PedidoDAO;
import com.ad_ud2_at2.models.Pedido;
import com.ad_ud2_at2.services.menu.actions.MenuAction;
import com.ad_ud2_at2.services.menu.state.MenuResult;
import com.ad_ud2_at2.services.logger.Logger;

public class ExportarTodosPedidosAction extends MenuAction {

    private Scanner sc;
    private PedidoDAO pedidoDAO;

    public ExportarTodosPedidosAction(Scanner sc) {
        this.sc = sc;
        this.pedidoDAO = new PedidoDAO();
    }

    @Override
    public MenuResult execute() {
        try {
            Logger.info("=== EXPORTAR TODOS LOS PEDIDOS A JSON ===");
            
            List<Pedido> pedidos = pedidoDAO.getAll();
            
            if (pedidos.isEmpty()) {
                Logger.warning("No hay pedidos para exportar");
                return MenuResult.CONTINUE;
            }
            
            Logger.info("Se exportarán " + pedidos.size() + " pedidos");
            
            // Calculate total value
            float totalGeneral = 0;
            for (Pedido pedido : pedidos) {
                totalGeneral += pedido.getProducto().getPrecio() * pedido.getCantidad();
            }
            
            System.out.println("Valor total de todos los pedidos: $" + totalGeneral);
            
            System.out.print("\n¿Confirmar exportación de todos los pedidos a JSON? (s/N): ");
            String confirmacion = sc.nextLine().trim().toLowerCase();
            
            if (confirmacion.equals("s") || confirmacion.equals("si")) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String fileName = "data/todos_pedidos_" + timestamp + ".json";
                
                if (exportarTodosPedidosAJSON(pedidos, fileName, totalGeneral)) {
                    Logger.success("Todos los pedidos exportados exitosamente a: " + fileName);
                } else {
                    Logger.error("Error al exportar los pedidos");
                }
            } else {
                Logger.info("Operación cancelada");
            }
            
        } catch (Exception e) {
            Logger.error("Error al exportar pedidos: " + e.getMessage());
        }
        
        return MenuResult.CONTINUE;
    }
    
    private boolean exportarTodosPedidosAJSON(List<Pedido> pedidos, String fileName, float totalGeneral) {
        try {
            // Create data directory if it doesn't exist
            java.io.File dataDir = new java.io.File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
                Logger.info("Directorio 'data' creado");
            }
            
            StringBuilder json = new StringBuilder();
            json.append("{\n");
            json.append("  \"exportData\": {\n");
            json.append("    \"totalPedidos\": ").append(pedidos.size()).append(",\n");
            json.append("    \"totalGeneral\": ").append(totalGeneral).append(",\n");
            json.append("    \"pedidos\": [\n");
            
            for (int i = 0; i < pedidos.size(); i++) {
                Pedido pedido = pedidos.get(i);
                float total = pedido.getProducto().getPrecio() * pedido.getCantidad();
                
                json.append("      {\n");
                json.append("        \"id\": ").append(pedido.getId()).append(",\n");
                json.append("        \"fecha\": \"").append(pedido.getFecha()).append("\",\n");
                json.append("        \"cantidad\": ").append(pedido.getCantidad()).append(",\n");
                json.append("        \"total\": ").append(total).append(",\n");
                
                // Cliente information
                json.append("        \"cliente\": {\n");
                json.append("          \"dni\": \"").append(escapeJson(pedido.getCliente().getDni())).append("\",\n");
                json.append("          \"nombre\": \"").append(escapeJson(pedido.getCliente().getNombre())).append("\"\n");
                json.append("        },\n");
                
                // Producto information
                json.append("        \"producto\": {\n");
                json.append("          \"id\": ").append(pedido.getProducto().getId()).append(",\n");
                json.append("          \"nombre\": \"").append(escapeJson(pedido.getProducto().getNombre())).append("\",\n");
                json.append("          \"descripcion\": \"").append(escapeJson(pedido.getProducto().getDescripcion())).append("\",\n");
                json.append("          \"precio\": ").append(pedido.getProducto().getPrecio()).append("\n");
                json.append("        }\n");
                
                if (i < pedidos.size() - 1) {
                    json.append("      },\n");
                } else {
                    json.append("      }\n");
                }
            }
            
            json.append("    ]\n");
            json.append("  },\n");
            json.append("  \"metadata\": {\n");
            json.append("    \"exportedAt\": \"").append(LocalDateTime.now()).append("\",\n");
            json.append("    \"exportedBy\": \"Bolechas System\",\n");
            json.append("    \"version\": \"1.0\",\n");
            json.append("    \"exportType\": \"bulk\"\n");
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