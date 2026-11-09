package com.example.actividad2_conversor;

/**
 * Categorías de conversión: nombre visible y unidades disponibles.
 * (Al unir con la Activity 1 se usa el Category de tu compañero, que además trae la imagen:
 *  esta Activity solo necesita getDisplayName() y getUnits().)
 */
public enum Category {

    DISTANCIA("Distancia", new Unit[]{            // base: metro
            new Unit("Metro", 1), new Unit("Kilómetro", 1000), new Unit("Centímetro", 0.01),
            new Unit("Milla", 1609.344), new Unit("Pie", 0.3048), new Unit("Pulgada", 0.0254)}),

    TEMPERATURA("Temperatura", new Unit[]{        // base: Celsius
            new Unit("Celsius", 1, 0),
            new Unit("Kelvin", 1, -273.15),
            new Unit("Fahrenheit", 5.0 / 9, -160.0 / 9)}),

    VOLUMEN("Volumen", new Unit[]{                // base: litro
            new Unit("Litro", 1), new Unit("Mililitro", 0.001),
            new Unit("Galón (US)", 3.785411784), new Unit("Pinta (US)", 0.473176473)}),

    MASA("Masa y peso", new Unit[]{               // base: kilogramo
            new Unit("Kilogramo", 1), new Unit("Gramo", 0.001), new Unit("Tonelada", 1000),
            new Unit("Libra", 0.45359237), new Unit("Onza", 0.028349523125)}),

    TIEMPO("Tiempo", new Unit[]{                  // base: segundo
            new Unit("Segundo", 1), new Unit("Minuto", 60), new Unit("Hora", 3600),
            new Unit("Día", 86400), new Unit("Semana", 604800)}),

    DIGITAL("Información digital", new Unit[]{    // base: byte (1 KB = 1024 B)
            new Unit("Bit", 0.125), new Unit("Byte", 1), new Unit("Kilobyte", 1024),
            new Unit("Megabyte", 1024.0 * 1024), new Unit("Gigabyte", 1024.0 * 1024 * 1024)}),

    MONEDA("Moneda", new Unit[]{                  // base: euro (TASAS DE EJEMPLO)
            new Unit("Euro", 1), new Unit("Dólar US", 0.93),
            new Unit("Libra esterlina", 1.17), new Unit("Yen japonés", 0.0062)}),

    AREA("Área", new Unit[]{                      // base: metro cuadrado
            new Unit("Metro cuadrado", 1), new Unit("Kilómetro cuadrado", 1_000_000),
            new Unit("Hectárea", 10_000), new Unit("Acre", 4046.8564224), new Unit("Pie cuadrado", 0.09290304)}),

    VELOCIDAD("Velocidad", new Unit[]{            // base: m/s
            new Unit("Metro/segundo", 1), new Unit("Kilómetro/hora", 1 / 3.6),
            new Unit("Milla/hora", 0.44704), new Unit("Nudo", 0.514444)}),

    PRESION("Presión", new Unit[]{                // base: pascal
            new Unit("Pascal", 1), new Unit("Kilopascal", 1000), new Unit("Bar", 100_000),
            new Unit("Atmósfera", 101_325), new Unit("PSI", 6894.757)});

    private final String displayName;
    private final Unit[] units;

    Category(String displayName, Unit[] units) {
        this.displayName = displayName;
        this.units = units;
    }

    public String getDisplayName() { return displayName; }
    public Unit[] getUnits() { return units; }
}
