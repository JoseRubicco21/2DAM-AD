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
    
    /**
     * Repositorio de tareas y vista asociada
     */
    private TareaRepository tareaRepository;

    /**
     * Vista asociada al controlador
     */
    private TareaView view;
    
    /**
     * Constructor del controlador
     * @param view Vista asociada al controlador
     */
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
     * @param titulo Título de la tarea
     * @param descripcion Descripción de la tarea
     * @param completada Estado de completitud de la tarea
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
     * @param id  ID de la tarea a actualizar
     * @param titulo Título de la tarea
     * @param descripcion  Descripción de la tarea
     * @param completada Estado de completitud de la tarea
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
     * @param id ID de la tarea a eliminar
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
     * @param id ID de la tarea a marcar como completada
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
     * Carga una tarea en el formulario de la vista
     * @param id ID de la tarea a cargar
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
     * @return Lista de todas las tareas
     */
    public List<Tarea> obtenerTodasLasTareas() {
        return tareaRepository.obtenerTodas();
    }
    

    /**
     * Valida los datos de la tarea antes de agregar o actualizar
     * @param titulo Título de la tarea
     * @return true si los datos son válidos, false en caso contrario
     */
    private boolean validarDatosTarea(String titulo) {
        boolean condition;
        
        if (titulo == null || titulo.trim().isEmpty()) {
            view.mostrarMensaje("El título es obligatorio", "Validación", JOptionPane.WARNING_MESSAGE);
            view.enfocarTitulo();
            condition =  false;
        }
        condition = true;
        return condition;
    }
}
