package com.lembranzas.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

import com.lembranzas.controller.TareaController;
import com.lembranzas.model.Tarea;

/**
 * Vista principal para la gestión de tareas usando Java Swing
 */
public class TareaView extends JFrame {
    
    /**
     * Controlador asociado a la vista
     */
    private TareaController controller;
    /**
     * Modelo de la tabla de tareas
     */
    private DefaultTableModel tableModel;
    /**
     * Tabla para mostrar las tareas
     */
    private JTable tablaTareas;
    /**
     * Sorter para filtrar las filas de la tabla
     */
    private TableRowSorter<DefaultTableModel> rowSorter;
    
    /**
     * Componentes del formulario
     */
    private JTextField txtTitulo;
    /**
     *  Descripción de la tarea
     */
    private JTextArea txtDescripcion;
    /**
     * Checkbox para indicar si la tarea está completada
     */
    private JCheckBox chkCompletada;
    /**
     * Botones del formulario
     */
    private JButton btnAgregar;
    /**
     * Botón para actualizar una tarea existente
     */
    private JButton btnActualizar;
    /**
     * Botón para eliminar una tarea existente
     */
    private JButton btnEliminar;
    /**
     * Botón para marcar una tarea como completada
     */
    private JButton btnMarcarCompletada;
    /**
     * Botón para limpiar el formulario
     */
    private JButton btnLimpiar;
    
    /**
     * Campo de texto para filtrar tareas por ID
     */
    private JTextField txtFiltroId;
    /**
     *  Botón para aplicar el filtro por ID
     */
    private JButton btnFiltrar;
    /**
     * Botón para limpiar el filtro aplicado
     */
    private JButton btnLimpiarFiltro;
    
    /**
     * ID de la tarea actualmente seleccionada en la tabla
     */
    private int tareaSeleccionadaId = -1;
    
    /**
     * Constructor de la vista principal
     */
    public TareaView() {
        this.controller = new TareaController(this);
        
        initComponents();
        setupLayout();
        setupEventListeners();
        actualizarTabla();
        
        setTitle("Gestor de Tareas - Lembranzas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }
    
    /**
     * Inicializa los componentes de la interfaz gráfica
     */
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
        
        // Componentes de filtro
        txtFiltroId = new JTextField(10);
        txtFiltroId.setToolTipText("Ingrese ID para filtrar");
        btnFiltrar = new JButton("Filtrar");
        btnLimpiarFiltro = new JButton("Mostrar Todo");
        
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
        
        // Configurar el sorter para la tabla
        rowSorter = new TableRowSorter<>(tableModel);
        tablaTareas.setRowSorter(rowSorter);
        
        // Inicialmente deshabilitar botones de actualización y eliminación
        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnMarcarCompletada.setEnabled(false);
    }
    
    /**
     *  Configura el diseño de la interfaz gráfica
     */
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
        
        // Panel de filtro
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtrar por ID"));
        panelFiltro.add(new JLabel("ID:"));
        panelFiltro.add(txtFiltroId);
        panelFiltro.add(btnFiltrar);
        panelFiltro.add(btnLimpiarFiltro);
        
        // Panel de la tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Lista de Tareas"));
        
        // Agregar panel de filtro arriba de la tabla
        panelTabla.add(panelFiltro, BorderLayout.NORTH);
        
        JScrollPane scrollTabla = new JScrollPane(tablaTareas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);
        
        // Agregar paneles al frame principal
        add(panelFormulario, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);
    }
    
    /**
     * Configura los listeners de eventos para los componentes
     */
    private void setupEventListeners() {
        // Listener para selección en la tabla
        tablaTareas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaTareas.getSelectedRow();
                if (selectedRow >= 0) {
                    // Convertir el índice de la vista al índice del modelo
                    int modelRow = tablaTareas.convertRowIndexToModel(selectedRow);
                    int id = (Integer) tableModel.getValueAt(modelRow, 0);
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
        
        // Filtro por ID
        btnFiltrar.addActionListener(e -> filtrarPorId());
        
        // Limpiar filtro
        btnLimpiarFiltro.addActionListener(e -> limpiarFiltro());
        
        // Filtro en tiempo real mientras se escribe
        txtFiltroId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrarPorId();
            }
        });
        
        // Filtro al presionar Enter
        txtFiltroId.addActionListener(e -> filtrarPorId());
    }
    
    /**
     * Filtra las filas de la tabla según el ID ingresado en el campo de texto
     */
    private void filtrarPorId() {
        String filtroTexto = txtFiltroId.getText().trim();
        
        if (filtroTexto.isEmpty()) {
            // Si no hay texto, mostrar todas las filas
            rowSorter.setRowFilter(null);
        } else {
            try {
                // Intentar parsear como número
                int idBuscado = Integer.parseInt(filtroTexto);
                // Filtrar solo por ID (columna 0)
                rowSorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, idBuscado, 0));
            } catch (NumberFormatException e) {
                // Si no es un número válido, no mostrar nada
                rowSorter.setRowFilter(RowFilter.regexFilter("^$")); // Filtro que no coincide con nada
            }
        }
    }
    
    /**
     * Limpia el filtro aplicado en la tabla
     */
    private void limpiarFiltro() {
        txtFiltroId.setText("");
        rowSorter.setRowFilter(null);
    }
    
    // Métodos públicos para que el controlador pueda interactuar con la vista
    /**
     * Carga los datos de una tarea en el formulario
     * @param tarea Tarea cuyos datos se cargarán en el formulario
     */
    public void cargarDatosEnFormulario(Tarea tarea) {
        txtTitulo.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripcion());
        chkCompletada.setSelected(tarea.isCompletada());
    }
    
    /**
     * Limpia los campos del formulario y restablece el estado inicial
     */
    public void limpiarFormulario() {
        txtTitulo.setText("");
        txtDescripcion.setText("");
        chkCompletada.setSelected(false);
        tareaSeleccionadaId = -1;
        tablaTareas.clearSelection();
        habilitarBotonesEdicion(false);
    }
    
    /**
     * Actualiza la tabla de tareas con los datos actuales del controlador
     */
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
    
    /**
     * Muestra un mensaje emergente con la información proporcionada
     * @param mensaje El mensaje a mostrar
     * @param titulo  El título del mensaje
     * @param tipo    El tipo de mensaje (por ejemplo, JOptionPane.INFORMATION_MESSAGE)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    /**
     * Muestra un cuadro de confirmación con el mensaje y título proporcionados
     * @param mensaje El mensaje a mostrar en el cuadro de confirmación
     * @param titulo El título del cuadro de confirmación
     * @return El valor seleccionado por el usuario (JOptionPane.YES_OPTION o JOptionPane.NO_OPTION)
     */
    public int mostrarConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(this, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }
    
    /**
     * Enfoca el campo de texto del título
     */
    public void enfocarTitulo() {
        txtTitulo.requestFocus();
    }
    
    /**
     * Actualiza el estado del checkbox de completada
     * @param completada Estado a establecer en el checkbox
     */
    public void actualizarEstadoCompletada(boolean completada) {
        chkCompletada.setSelected(completada);
    }
    

    /**
     * Habilita o deshabilita los botones de edición (Actualizar, Eliminar, Marcar como Completada)
     * @param habilitar Estado para habilitar o deshabilitar los botones
     */
    private void habilitarBotonesEdicion(boolean habilitar) {
        btnActualizar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
        btnMarcarCompletada.setEnabled(habilitar);
    }
    
}