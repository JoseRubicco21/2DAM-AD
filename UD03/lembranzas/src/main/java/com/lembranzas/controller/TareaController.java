package com.lembranzas.controller;

import com.lembranzas.model.Tarea;
import com.lembranzas.services.TareaRepository;
import com.lembranzas.services.repository.exceptions.TareaNotFoundException;
import com.lembranzas.view.TareaView;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para la gestión de tareas
 */
public class TareaController {
    
    private TareaRepository tareaRepository;
    private TareaView view;
    
    public TareaController(TareaView view) {
        this.view = view;
        this.tareaRepository = TareaRepository.getInstancia();
        
        // Inicializar la lista si está vacía
        if (tareaRepository.getTareas() == null) {
            tareaRepository.setTareas(new ArrayList<>());
        }
    }
    
    /**
     * Agrega una nueva tarea
     */
    public void agregarTarea(String titulo, String descripcion, boolean completada) {
        if (!validarDatosTarea(titulo)) {
            return;
        }
        
        try {
            List<Tarea> tareas = tareaRepository.obtenerTodas();
            int nuevoId = tareas.size(); // Simple ID generation
            
            Tarea nuevaTarea = new Tarea(nuevoId, titulo.trim(), descripcion.trim(), completada);
            tareaRepository.agregar(nuevaTarea);
            
            view.actualizarTabla();
            view.limpiarFormulario();
            view.mostrarMensaje("Tarea agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            view.mostrarMensaje("Error al agregar tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Actualiza una tarea existente
     */
    public void actualizarTarea(int id, String titulo, String descripcion, boolean completada) {
        if (!validarDatosTarea(titulo)) {
            return;
        }
        
        try {
            Tarea tareaActualizada = new Tarea(id, titulo.trim(), descripcion.trim(), completada);
            tareaRepository.actualizar(id, tareaActualizada);
            
            view.actualizarTabla();
            view.limpiarFormulario();
            view.mostrarMensaje("Tarea actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (TareaNotFoundException e) {
            view.mostrarMensaje("Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Elimina una tarea
     */
    public void eliminarTarea(int id) {
        int confirmacion = view.mostrarConfirmacion(
            "¿Está seguro de que desea eliminar esta tarea?",
            "Confirmar eliminación"
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                tareaRepository.eliminar(id);
                view.actualizarTabla();
                view.limpiarFormulario();
                view.mostrarMensaje("Tarea eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } catch (TareaNotFoundException e) {
                view.mostrarMensaje("Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Marca una tarea como completada
     */
    public void marcarComoCompletada(int id) {
        try {
            tareaRepository.marcarComoCompletada(id);
            view.actualizarTabla();
            view.actualizarEstadoCompletada(true);
            view.mostrarMensaje("Tarea marcada como completada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (TareaNotFoundException e) {
            view.mostrarMensaje("Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carga una tarea en el formulario
     */
    public void cargarTarea(int id) {
        try {
            Tarea tarea = tareaRepository.obtenerPorId(id);
            view.cargarDatosEnFormulario(tarea);
            
        } catch (TareaNotFoundException e) {
            view.mostrarMensaje("Error al cargar tarea: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Obtiene todas las tareas
     */
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.obtenerTodas();
    }
    
    /**
     * Valida los datos de entrada de una tarea
     */
    private boolean validarDatosTarea(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            view.mostrarMensaje("El título es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            view.enfocarTitulo();
            return false;
        }
        return true;
    }
}
