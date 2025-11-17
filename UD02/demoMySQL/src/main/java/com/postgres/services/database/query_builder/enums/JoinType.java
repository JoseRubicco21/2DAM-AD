package com.postgres.services.database.query_builder.enums;

/**
 * Enumeración que define los tipos de JOIN disponibles en SQL.
 * 
 * Esta enum proporciona una abstracción tipo-segura para los diferentes tipos
 * de unión (JOIN) utilizados en consultas SQL para combinar datos de múltiples
 * tablas. Cada tipo de JOIN tiene un comportamiento específico en cuanto a
 * qué registros se incluyen en el resultado final.
 * 
 * Los JOINs son fundamentales para consultas relacionales que necesitan
 * combinar información de diferentes tablas basándose en relaciones comunes.
 * 
 * @author [Tu nombre]
 * @version 1.0
 * @since 2025-11-07
 * @see com.ad_ud2_at2.services.database.query_builder.dialects.SQLDialect
 */
public enum JoinType {
    
    /**
     * INNER JOIN - Unión interna.
     * Retorna únicamente los registros que tienen coincidencias en ambas tablas.
     * Es el tipo de JOIN más restrictivo y comúnmente utilizado.
     * 
     * @example
     * <pre>
     * SELECT u.nombre, p.titulo 
     * FROM usuarios u 
     * INNER JOIN pedidos p ON u.id = p.usuario_id;
     * 
     * Resultado: Solo usuarios que han hecho pedidos
     * </pre>
     */
    INNER("INNER JOIN"),
    
    /**
     * LEFT JOIN - Unión izquierda (LEFT OUTER JOIN).
     * Retorna todos los registros de la tabla izquierda (primera tabla) y los
     * registros coincidentes de la tabla derecha. Si no hay coincidencias,
     * se retornan valores NULL para las columnas de la tabla derecha.
     * 
     * @example
     * <pre>
     * SELECT u.nombre, p.titulo 
     * FROM usuarios u 
     * LEFT JOIN pedidos p ON u.id = p.usuario_id;
     * 
     * Resultado: Todos los usuarios, incluso los que no han hecho pedidos
     * </pre>
     */
    LEFT("LEFT JOIN"),
    
    /**
     * RIGHT JOIN - Unión derecha (RIGHT OUTER JOIN).
     * Retorna todos los registros de la tabla derecha (segunda tabla) y los
     * registros coincidentes de la tabla izquierda. Si no hay coincidencias,
     * se retornan valores NULL para las columnas de la tabla izquierda.
     * 
     * @example
     * <pre>
     * SELECT u.nombre, p.titulo 
     * FROM usuarios u 
     * RIGHT JOIN pedidos p ON u.id = p.usuario_id;
     * 
     * Resultado: Todos los pedidos, incluso aquellos sin usuario válido
     * </pre>
     */
    RIGHT("RIGHT JOIN"),
    
    /**
     * FULL JOIN - Unión completa (FULL OUTER JOIN).
     * Retorna todos los registros cuando hay una coincidencia en cualquiera
     * de las tablas. Combina el comportamiento de LEFT y RIGHT JOIN.
     * Si no hay coincidencias, se retornan valores NULL para las columnas
     * de la tabla que no tiene datos.
     * 
     * @example
     * <pre>
     * SELECT u.nombre, p.titulo 
     * FROM usuarios u 
     * FULL JOIN pedidos p ON u.id = p.usuario_id;
     * 
     * Resultado: Todos los usuarios Y todos los pedidos, independientemente de coincidencias
     * </pre>
     */
    FULL("FULL JOIN");

    /**
     * Representación en cadena del tipo de JOIN SQL.
     * Contiene la sintaxis exacta como aparece en las consultas SQL estándar.
     */
    private final String joinType;

    /**
     * Constructor privado para inicializar cada constante de la enumeración.
     * 
     * @param joinType La representación en cadena del tipo de JOIN SQL
     */
    JoinType(String joinType) {
        this.joinType = joinType;
    }

    /**
     * Obtiene la representación en cadena del tipo de JOIN.
     * 
     * @return String con la sintaxis SQL del tipo de JOIN
     * 
     * @example
     * <pre>
     * JoinType.INNER.getJoinType() → "INNER JOIN"
     * JoinType.LEFT.getJoinType() → "LEFT JOIN"
     * JoinType.FULL.getJoinType() → "FULL JOIN"
     * </pre>
     */
    public String getJoinType() {
        return joinType;
    }

    /**
     * Representación en cadena del tipo de JOIN.
     * Equivalente a getJoinType() para facilitar el uso en concatenaciones.
     * 
     * @return String con la sintaxis SQL del tipo de JOIN
     */
    @Override
    public String toString() {
        return joinType;
    }
}