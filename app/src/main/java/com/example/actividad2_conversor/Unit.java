package com.example.actividad2_conversor;

/** Una unidad de medida: valor_base = valor * factor + offset. */
public class Unit {
    private final String name;
    private final double factor;   // cuánto vale 1 de esta unidad en la unidad base
    private final double offset;   // desplazamiento (solo temperatura)

    public Unit(String name, double factor) { this(name, factor, 0); }

    public Unit(String name, double factor, double offset) {
        this.name = name;
        this.factor = factor;
        this.offset = offset;
    }

    public String getName() { return name; }
    public double getFactor() { return factor; }
    public double getOffset() { return offset; }

    @Override
    public String toString() { return name; }
}
