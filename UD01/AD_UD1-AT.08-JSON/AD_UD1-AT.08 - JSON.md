# AD_UD1-AT.08-JSON

## Normas

---

### Recursos

Se pueden  acceder a los siguientes recursos:

- [Documentación de java](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)
- [Stackoverflow](https://stackoverflow.com/questions)
- [Baeldung](https://www.baeldung.com/)

Se proveeran detalles de implementación como sugerencia.

### I/O - Entrada y Salida

Deben ser verificadas las entradas por teclado y por lo tanto asegurar la no ruptura del programa en caso de que los valores introducidos no sean los esperados.

### Flujos

Todos los flujos deben de cerrarse correctamente. Utilizar en la medida de lo posible `try-with-resources`. En caso contrario usar `finally`

### Excepciones

- El usuario debe estar informado con mensajes claros de la operación que se va a
ejecutar, si se ha ejecutado satisfactoriamente o no, etc.
- Deben ser capturadas las excepciones que puedan ocurrir en el programa

### Comentarios

- Comenta el código en la medida de lo posible.
- Utiliza los comentarios de javadoc para documentar el código en caso de ser posible.

### Entrega

Debes entregar un fichero .zip que incluya el/los proyectos con todo el código fuente implementado en cada uno de ellos.

El nombre del fichero será: AD-UD1-AT.07-Apellidos_Nombre.zip

>[!info] Nombre de fichero
> Apellidos y nombres serán tus apellidos y tus nombres.

---

## Ejercicio 1 - Worldbuilding

### Enunciado

Haga un programa que permita la gestión y creación de un mundo fantástico. Tal que pueda representar naciones ficticias con propiedades de diferentes tipos de datos.

El programa debe de tener las siguientes funcionalidades:

- Cargar datos de un mundo.
- Mostrar datos de un mundo.
- Guardar datos de un mundo.
- Crear nación.
- Añadir nación al mundo.
- Finalizar programa.

>[!danger] GSON y maven.
Utiliza la libreria `GSON` con `maven`. Cargar y Guardar datos del mundo deben tener como origen y destino un fichero `JSON`.

Asegurase de que haya una buena división de paquetes y que los modificadores de acceso sean los adecuados.

### Recomendaciones

A continuación se aconseja una representación recomendada de las clases `Mundo`, `Nacion`, `Ciudad` y `Escudo`.

> Una clase `Mundo` que representa un Mundo.

```java
public class Mundo {
    Collection<Nacion> naciones;
}
```

> Una clase `Nación` que representa a nivel general una nación dentro del mundo.
>
```java
public class Nacion {
    String Nombre;
    Ciudad Capital;
    Collection<Ciudad> ciudades;
    String clima;
    int habitantes;
    double km2;
}
```

> Una clase `Ciudad` que representa a nivel general una ciudad dentro del mundo. Las ciudades *deben* de estar ligadas a una nación.

```java
public class Ciudad {
    String nombre;
    int habitantes;
    Escudo escudo;
}
```

> Una clase `Escudo` que representa el escudo de una ciudad. Una ciudad puede no tener escudo, o tener varios. En caso de tener será el último escudo generado para esa ciudad.

```java
public class Escudo {
    List<String> colores;
}
```

Modifica las clases según consideres necesario.

Se recomienda una clase que haga de *controlador* para el guardado, carga y muestreo de un fichero JSON. 

```java
public class ControladorJSON(){
    public static boolean cargarJSON(File fichero)
    public static boolean guardarJSON(File fichero)
    public static void mostrarJSON(String datos) 
}
```

