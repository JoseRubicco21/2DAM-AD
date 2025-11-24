package com.lembranzas;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.lembranzas.view.TareaView;

public class Main {
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new TareaView().setVisible(true);
        });
    }
}