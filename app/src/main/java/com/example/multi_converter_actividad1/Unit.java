package com.example.multi_converter_actividad1;

/**
 * Una unidad de medida. Se expresa respecto a la unidad base de su categoría:
 *   valorEnBase = valor * factor + offset
 * Para unidades lineales offset = 0. Para temperatura (base = Celsius) se usa también offset.
 */
public class Unit {
    public final String name;
    public final double factor;
    public final double offset;

    public Unit(String name, double factor, double offset) {
        this.name = name;
        this.factor = factor;
        this.offset = offset;
    }

    public Unit(String name, double factor) {
        this(name, factor, 0);
    }

    /** Fórmula genérica válida para TODAS las categorías (sin switch ni if-else). */
    public static double convert(double value, Unit from, Unit to) {
        double base = value * from.factor + from.offset;
        return (base - to.offset) / to.factor;
    }

    /** Permite usar ArrayAdapter<Unit> y que el Spinner muestre el nombre. */
    @Override
    public String toString() {
        return name;
    }
}

