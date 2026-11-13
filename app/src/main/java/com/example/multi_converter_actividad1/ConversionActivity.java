package com.example.multi_converter_actividad1;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Activity 2: conversión de unidades dentro de la categoría recibida por Intent. */
public class ConversionActivity extends AppCompatActivity {

    /** Clave del Intent: la Activity 1 debe enviar category.name() con esta clave. */
    public static final String EXTRA_CATEGORY = "CATEGORY";

    private Category category;
    private Unit[] units;
    private EditText etValue;
    private Spinner spFrom, spTo;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        TextView tvTitle = findViewById(R.id.tvTitle);
        etValue  = findViewById(R.id.etValue);
        spFrom   = findViewById(R.id.spFrom);
        spTo     = findViewById(R.id.spTo);
        tvResult = findViewById(R.id.tvResult);
        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnSwap    = findViewById(R.id.btnSwap);
        Button btnBack    = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());   // vuelve a la Activity 1

        // 1) Categoría recibida por Intent
        category = readCategory();
        if (category == null) {
            tvTitle.setText(R.string.app_name);
            btnConvert.setEnabled(false);
            btnSwap.setEnabled(false);
            showMessage(getString(R.string.msg_no_category));
            return;
        }
        units = category.getUnits();
        tvTitle.setText(getString(R.string.conversion_title, category.getDisplayName()));

        // 2) Spinners: primer elemento = "Select a unit…", después las unidades de la categoría
        List<String> items = new ArrayList<>();
        items.add(getString(R.string.select_unit));
        for (Unit u : units) items.add(u.getName());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrom.setAdapter(adapter);
        spTo.setAdapter(adapter);

        // 3) Botones
        btnConvert.setOnClickListener(v -> doConversion());
        btnSwap.setOnClickListener(v -> {
            int from = spFrom.getSelectedItemPosition();
            spFrom.setSelection(spTo.getSelectedItemPosition());
            spTo.setSelection(from);
        });
    }

    private Category readCategory() {
        String name = getIntent().getStringExtra(EXTRA_CATEGORY);
        if (name == null) return null;
        try {
            return Category.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void doConversion() {
        String text = etValue.getText().toString().trim().replace(',', '.');

        if (text.isEmpty()) {
            showMessage(getString(R.string.msg_empty));
            return;
        }

        double value;
        try {
            value = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            showMessage(getString(R.string.msg_invalid));
            return;
        }
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            showMessage(getString(R.string.msg_invalid));
            return;
        }

        // Solo la temperatura admite valores negativos
        if (value < 0 && category != Category.TEMPERATURA) {
            showMessage(getString(R.string.msg_negative));
            return;
        }

        int posFrom = spFrom.getSelectedItemPosition();
        int posTo   = spTo.getSelectedItemPosition();
        if (posFrom == AdapterView.INVALID_POSITION || posTo == AdapterView.INVALID_POSITION
                || posFrom == 0 || posTo == 0) {
            showMessage(getString(R.string.msg_select_units));
            return;
        }

        Unit from = units[posFrom - 1];   // -1 por el elemento "Select a unit…"
        Unit to   = units[posTo - 1];

        double result = Converter.convert(value, from, to);

        tvResult.setText(String.format(Locale.getDefault(), "%s %s = %s %s",
                format(value), from.getName(), format(result), to.getName()));
        showMessage(getString(R.string.msg_success));
    }

    private String format(double n) {
        double abs = Math.abs(n);
        String s;
        if (abs != 0 && (abs < 1e-4 || abs >= 1e12)) {
            s = String.format(Locale.getDefault(), "%.4e", n);
        } else {
            s = new DecimalFormat("#,##0.######").format(n);
        }
        return s.equals("-0") ? "0" : s;   // evita "-0" por errores de redondeo
    }

    private void showMessage(String msg) {
        Snackbar.make(findViewById(R.id.main), msg, Snackbar.LENGTH_SHORT).show();
    }
}
