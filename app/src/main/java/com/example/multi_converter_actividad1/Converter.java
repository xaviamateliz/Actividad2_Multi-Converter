package com.example.multi_converter_actividad1;

/** Conversión genérica entre dos unidades de la misma categoría (sin if/switch por unidad). */
public final class Converter {

    private Converter() {}

    public static double convert(double value, Unit from, Unit to) {
        double base = value * from.getFactor() + from.getOffset();   // a la unidad base
        return (base - to.getOffset()) / to.getFactor();             // a la unidad destino
    }
}
