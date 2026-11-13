package com.example.multi_converter_actividad1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

/**
 * Activity 1: el usuario elige una categoría de conversión.
 * Muestra una imagen (con Bitmap) según la categoría y abre la Activity 2
 * pasando la categoría con un Intent.
 */
public class MainActivity extends AppCompatActivity {

    // Textos del Spinner. La posición 0 es la opción vacía.
    // IMPORTANTE: el orden de las categorías (de la 1 a la 10) debe ser
    // el MISMO que el orden de las constantes del enum Category.
    private final String[] categorias = {
            "Selecciona una categoría",
            "Distancia", "Temperatura", "Volumen", "Masa", "Tiempo",
            "Información digital", "Moneda", "Área", "Velocidad", "Presión"
    };

    // Imágenes en el MISMO orden que las categorías (posición 0 = imagen por defecto)
    private final int[] imagenes = {
            R.drawable.img_default,
            R.drawable.img_distancia, R.drawable.img_temperatura,
            R.drawable.img_volumen, R.drawable.img_masa, R.drawable.img_tiempo,
            R.drawable.img_digital, R.drawable.img_moneda, R.drawable.img_area,
            R.drawable.img_velocidad, R.drawable.img_presion
    };

    private Spinner spinnerCategoria;
    private ImageView imgCategoria;
    private Button btnContinuar;

    /**
     * Se ejecuta al abrir la pantalla.
     * Entrada: savedInstanceState (estado guardado, puede ser null).
     * Salida: ninguna. Enlaza las vistas y configura Spinner y botón.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Enlazamos las vistas del XML con las variables
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        imgCategoria = findViewById(R.id.imgCategoria);
        btnContinuar = findViewById(R.id.btnContinuar);

        configurarSpinner();
        mostrarImagen(0); // al empezar se ve la imagen por defecto

        btnContinuar.setOnClickListener(v -> abrirConversion());
    }

    /**
     * Rellena el Spinner con las categorías y cambia la imagen al elegir una.
     * Entrada: ninguna. Salida: ninguna.
     */
    private void configurarSpinner() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, categorias);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(adaptador);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostrarImagen(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacemos nada
            }
        });
    }

    /**
     * Carga la imagen de la categoría como Bitmap y la muestra en el ImageView.
     * Entrada: posicion = posición elegida en el Spinner (0 a 10).
     * Salida: ninguna. Cambia la imagen de la pantalla.
     */
    private void mostrarImagen(int posicion) {
        Bitmap original = BitmapFactory.decodeResource(getResources(), imagenes[posicion]);
        Bitmap escalado = Bitmap.createScaledBitmap(original, 400, 400, true);
        imgCategoria.setImageBitmap(escalado);
    }

    /**
     * Comprueba que hay una categoría elegida y abre la Activity 2.
     * Entrada: ninguna (lee la posición del Spinner).
     * Salida: ninguna. Si no hay categoría muestra un Snackbar; si la hay,
     * lanza un Intent con el nombre del enum Category (por ejemplo "DISTANCIA"),
     * usando la misma clave que lee ConversionActivity.
     */
    private void abrirConversion() {
        int posicion = spinnerCategoria.getSelectedItemPosition();

        if (posicion == 0) {
            Snackbar.make(findViewById(R.id.main),
                    "Elige una categoría antes de continuar",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }

        // posicion - 1 porque la posición 0 del Spinner es "Selecciona una categoría"
        Category elegida = Category.values()[posicion - 1];

        Intent intent = new Intent(this, ConversionActivity.class);
        intent.putExtra(ConversionActivity.EXTRA_CATEGORY, elegida.name());
        startActivity(intent);
    }
}