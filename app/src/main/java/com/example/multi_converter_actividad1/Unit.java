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

    /** Devuelve el nombre de la unidad (ej: "Metro"). */
    public String getName() { return name; }

    /** Devuelve cuánto vale 1 de esta unidad en la unidad base de su categoría. */
    public double getFactor() { return factor; }

    /** Devuelve el desplazamiento respecto a la unidad base (solo se usa en temperatura). */
    public double getOffset() { return offset; }

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