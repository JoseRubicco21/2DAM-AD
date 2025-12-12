package com.lembranzas.services.seeders;

import com.github.javafaker.Faker;
import com.lembranzas.model.Tarea;
import com.lembranzas.services.TareaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Clase encargada de sembrar datos iniciales para las tareas.
 */
public class TareaSeeder {
    
    /**
     * Instancia de Faker para generar datos falsos
     */
    private static final Faker faker = new Faker(new Locale("es"));
    
    /**
     *  Método para sembrar tareas de prueba
     */
    public static void seed() {
        seed(10); // Por defecto, crear 10 tareas
    }
    
    /**
     * Método para sembrar una cantidad específica de tareas de prueba
     * @param cantidadTareas Cantidad de tareas a crear
     */
    public static void seed(int cantidadTareas) {
        TareaRepository repository = TareaRepository.getInstancia();
        
        // Inicializar la lista si es null
        if (repository.getTareas() == null) {
            repository.setTareas(new ArrayList<>());
        }
        
        // Limpiar datos existentes
        repository.getTareas().clear();
        
        List<Tarea> tareas = generarTareas(cantidadTareas);
        
        // Agregar las tareas al repositorio
        for (Tarea tarea : tareas) {
            repository.agregar(tarea);
        }
        
        System.out.println("Se han creado " + cantidadTareas + " tareas de prueba.");
    }
    
    /**
     * Genera una lista de tareas falsas
     * @param cantidad Cantidad de tareas a generar
     * @return Lista de tareas generadas
     */
    private static List<Tarea> generarTareas(int cantidad) {
        List<Tarea> tareas = new ArrayList<>();
        
        // Plantillas de títulos más realistas para tareas
        String[] tiposActividades = {
            "Completar", "Revisar", "Estudiar", "Preparar", "Organizar",
            "Planificar", "Desarrollar", "Crear", "Actualizar", "Investigar"
        };
        
        String[] objetos = {
            "informe del proyecto", "presentación para el cliente", "código de la aplicación",
            "documentación técnica", "reunión de equipo", "propuesta comercial",
            "análisis de datos", "diseño de interfaz", "base de datos",
            "estrategia de marketing", "plan de ventas", "curriculum vitae"
        };
        
        for (int i = 0; i < cantidad; i++) {
            Tarea tarea = new Tarea();
            tarea.setId(i);
            
            // Generar título realista
            String actividad = tiposActividades[faker.random().nextInt(tiposActividades.length)];
            String objeto = objetos[faker.random().nextInt(objetos.length)];
            tarea.setTitulo(actividad + " " + objeto);
            
            // Generar descripción más detallada
            tarea.setDescripcion(generarDescripcion());
            
            // 70% de posibilidad de estar completada
            tarea.setCompletada(faker.random().nextDouble() < 0.7);
            
            tareas.add(tarea);
        }
        
        return tareas;
    }
    
    /**
     * Genera una descripción detallada para una tarea
     * @return  Descripción generada
     */
    private static String generarDescripcion() {
        // Generar descripciones más variadas y realistas
        String[] inicios = {
            "Esta tarea requiere",
            "Es necesario",
            "Se debe realizar",
            "El objetivo es",
            "Importante completar"
        };
        
        String[] acciones = {
            "un análisis detallado de los requisitos",
            "la coordinación con el equipo de desarrollo",
            "una revisión exhaustiva de la documentación",
            "la implementación de las mejores prácticas",
            "una evaluación de los riesgos potenciales",
            "la validación de todos los componentes",
            "una comunicación efectiva con los stakeholders"
        };
        
        String[] complementos = {
            "para asegurar la calidad del entregable.",
            "antes de la fecha límite establecida.",
            "siguiendo los estándares de la empresa.",
            "con especial atención a los detalles.",
            "garantizando la satisfacción del cliente.",
            "manteniendo la eficiencia del proceso."
        };
        
        String inicio = inicios[faker.random().nextInt(inicios.length)];
        String accion = acciones[faker.random().nextInt(acciones.length)];
        String complemento = complementos[faker.random().nextInt(complementos.length)];
        
        return inicio + " " + accion + " " + complemento;
    }
    
    /**
     * Método para sembrar tareas de prueba con categorías específicas
     */
    public static void seedWithCategories() {
        TareaRepository repository = TareaRepository.getInstancia();
        
        if (repository.getTareas() == null) {
            repository.setTareas(new ArrayList<>());
        }
        
        repository.getTareas().clear();
        
        // Tareas de trabajo
        agregarTareasCategoria("Trabajo", 5, repository);
        
        // Tareas personales
        agregarTareasCategoria("Personal", 3, repository);
        
        // Tareas de estudio
        agregarTareasCategoria("Estudio", 4, repository);
        
        System.out.println("Se han creado tareas categorizadas de prueba.");
    }
    
    /**
     * Agrega tareas de una categoría específica al repositorio
     * @param categoria  Categoría de la tarea
     * @param cantidad Cantidad de tareas a crear
     * @param repository Repositorio donde se agregarán las tareas
     */
    private static void agregarTareasCategoria(String categoria, int cantidad, TareaRepository repository) {
        for (int i = 0; i < cantidad; i++) {
            int id = repository.getTareas().size();
            
            Tarea tarea = new Tarea();
            tarea.setId(id);
            tarea.setTitulo(generarTituloCategoria(categoria));
            tarea.setDescripcion(generarDescripcionCategoria(categoria));
            tarea.setCompletada(faker.random().nextBoolean());
            
            repository.agregar(tarea);
        }
    }
    
    /**
     * Genera un título basado en la categoría
     * @param categoria Categoría de la tarea
     * @return  Título generado para la tarea
     */
    private static String generarTituloCategoria(String categoria) {
        switch (categoria) {
            case "Trabajo":
                return faker.options().option(
                    "Revisar código del módulo de usuarios",
                    "Preparar presentación para el cliente",
                    "Actualizar documentación del API",
                    "Reunión de seguimiento del proyecto",
                    "Implementar nueva funcionalidad"
                );
            case "Personal":
                return faker.options().option(
                    "Hacer ejercicio en el gimnasio",
                    "Comprar víveres para la semana",
                    "Llamar al médico para cita",
                    "Organizar el armario",
                    "Planificar vacaciones de verano"
                );
            case "Estudio":
                return faker.options().option(
                    "Estudiar para el examen de matemáticas",
                    "Completar proyecto de programación",
                    "Leer capítulo 5 del libro de historia",
                    "Practicar ejercicios de inglés",
                    "Investigar sobre inteligencia artificial"
                );
            default:
                return faker.lorem().sentence(4);
        }
    }
    

    /**
     * Genera una descripción basada en la categoría
     * @param categoria Categoría de la tarea
     * @return Descripción generada para la tarea
     */
    private static String generarDescripcionCategoria(String categoria) {
        switch (categoria) {
            case "Trabajo":
                return "Tarea relacionada con actividades laborales que requiere atención y seguimiento profesional.";
            case "Personal":
                return "Actividad personal importante para mantener el bienestar y la organización diaria.";
            case "Estudio":
                return "Tarea académica que contribuye al desarrollo educativo y profesional.";
            default:
                return faker.lorem().paragraph();
        }
    }

    /**
     * Constructor privado para evitar instanciación
     */
    private TareaSeeder() {
        // Constructor privado para evitar instanciación
    }
}
