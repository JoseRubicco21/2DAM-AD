package com.lembranzas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.lembranzas.controller.TareaController;
import com.lembranzas.model.Tarea;

/**
 * Vista principal para la gestión de tareas usando Java Swing
 */
public class TareaView extends JFrame {
    
    private TareaController controller;
    private DefaultTableModel tableModel;
    private JTable tablaTareas;
    
    // Componentes del formulario
    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JCheckBox chkCompletada;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnMarcarCompletada;
    private JButton btnLimpiar;
    
    private int tareaSeleccionadaId = -1;
    
    public TareaView() {
        this.controller = new TareaController(this);
        
        initComponents();
        setupLayout();
        setupEventListeners();
        actualizarTabla();
        
        setTitle("Gestor de Tareas - Lembranzas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        // Inicializar componentes del formulario
        txtTitulo = new JTextField(20);
        txtDescripcion = new JTextArea(4, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        chkCompletada = new JCheckBox("Completada");
        
        // Botones
        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnMarcarCompletada = new JButton("Marcar como Completada");
        btnLimpiar = new JButton("Limpiar");
        
        // Tabla
        String[] columnas = {"ID", "Título", "Descripción", "Completada"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaTareas = new JTable(tableModel);
        tablaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Inicialmente deshabilitar botones de actualización y eliminación
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnMarcarCompletada.setEnabled(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel del formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestión de Tareas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Título
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(new JLabel("Título:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        panelFormulario.add(txtTitulo, gbc);
        
        // Descripción
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Descripción:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelFormulario.add(scrollDescripcion, gbc);
        
        // Checkbox completada
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(chkCompletada, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMarcarCompletada);
        panelBotones.add(btnLimpiar);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFormulario.add(panelBotones, gbc);
        
        // Panel de la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Tareas"));
        JScrollPane scrollTabla = new JScrollPane(tablaTareas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        
        // Agregar paneles al frame principal
        add(panelFormulario, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
    }
    
    private void setupEventListeners() {
        // Listener para selección en la tabla
        tablaTareas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) tableModel.getValueAt(selectedRow, 0);
                    tareaSeleccionadaId = id;
                    controller.cargarTarea(id);
                    habilitarBotonesEdicion(true);
                } else {
                    limpiarFormulario();
                    habilitarBotonesEdicion(false);
                }
            }
        });
        
        // Botón Agregar
        btnAgregar.addActionListener(e -> controller.agregarTarea(
            txtTitulo.getText(),
            txtDescripcion.getText(),
            chkCompletada.isSelected()
        ));
        
        // Botón Actualizar
        btnActualizar.addActionListener(e -> {
            if (tareaSeleccionadaId >= 0) {
                controller.actualizarTarea(
                    tareaSeleccionadaId,
                    txtTitulo.getText(),
                    txtDescripcion.getText(),
                    chkCompletada.isSelected()
                );
            }
        });
        
        // Botón Eliminar
        btnEliminar.addActionListener(e -> {
            if (tareaSeleccionadaId >= 0) {
                controller.eliminarTarea(tareaSeleccionadaId);
            }
        });
        
        // Botón Marcar como Completada
        btnMarcarCompletada.addActionListener(e -> {
            if (tareaSeleccionadaId >= 0) {
                controller.marcarComoCompletada(tareaSeleccionadaId);
            }
        });
        
        // Botón Limpiar
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }
    
    // Métodos públicos para que el controlador pueda interactuar con la vista
    
    public void cargarDatosEnFormulario(Tarea tarea) {
        txtTitulo.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripcion());
        chkCompletada.setSelected(tarea.isCompletada());
    }
    
    public void limpiarFormulario() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        chkCompletada.setSelected(false);
        tareaSeleccionadaId = -1;
        tablaTareas.clearSelection();
        habilitarBotonesEdicion(false);
    }
    
    public void actualizarTabla() {
        tableModel.setRowCount(0);
        List<Tarea> tareas = controller.obtenerTodasLasTareas();
        
        if (tareas != null) {
            for (Tarea tarea : tareas) {
                Object[] fila = {
                    tarea.getId(),
                    tarea.getTitulo(),
                    tarea.getDescripcion(),
                    tarea.isCompletada() ? "Sí" : "No"
                };
                tableModel.addRow(fila);
            }
        }
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    public int mostrarConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(this, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }
    
    public void enfocarTitulo() {
        txtTitulo.requestFocus();
    }
    
    public void actualizarEstadoCompletada(boolean completada) {
        chkCompletada.setSelected(completada);
    }
    
    private void habilitarBotonesEdicion(boolean habilitar) {
        btnActualizar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
        btnMarcarCompletada.setEnabled(habilitar);
    }
    
}