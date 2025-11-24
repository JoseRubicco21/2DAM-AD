package com.lembranzas;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.lembranzas.services.seeders.TareaSeeder;
import com.lembranzas.view.TareaView;

/**
 * Clase principal para iniciar la aplicación Lembranzas.
 */
public class Main {
    /**
     * Método principal de la aplicación
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
      
            TareaSeeder.seed(500);
            new TareaView().setVisible(true);
        });
    }
}