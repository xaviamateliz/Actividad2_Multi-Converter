package com.example.multi_converter_actividad1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity de PRUEBA (stub). NO subir a Git.
 * Solo sirve para comprobar que el Intent desde MainActivity funciona
 * y que la categoría llega bien.
 */
public class ConversionActivity extends AppCompatActivity {

    /**
     * Se ejecuta al abrirse la pantalla.
     * Entrada: savedInstanceState (estado guardado, puede ser null).
     * Salida: ninguna. Muestra en pantalla la categoría recibida por el Intent.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recogemos la categoría que envía MainActivity con putExtra
        String categoria = getIntent().getStringExtra(MainActivity.EXTRA_CATEGORY);

        // Creamos un TextView por código para no necesitar un layout nuevo
        TextView texto = new TextView(this);
        texto.setTextSize(24);
        texto.setPadding(48, 96, 48, 48);

        if (categoria == null) {
            texto.setText("No ha llegado ninguna categoría");
        } else {
            texto.setText("Categoría recibida: " + categoria);
        }

        setContentView(texto);
    }
}
