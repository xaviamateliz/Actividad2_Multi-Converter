# Multi-Converter

Aplicación Android para convertir valores entre distintas unidades de medida, desarrollada en Java con Android Studio.

## Autores

- Xavier Amate Lizandra (rama `xavi`): Activity 1, selección de categoría y Bitmap
- Saul Martin Constantino (rama `saul`): Activity 2, conversión de unidades

## Descripción

La aplicación tiene dos pantallas:

1. **Activity 1 (`MainActivity`)**: el usuario elige una categoría de conversión en un Spinner. Según la categoría se muestra una imagen cargada con la clase `Bitmap`. Al pulsar el botón, se abre la segunda pantalla y se le pasa la categoría mediante un `Intent`.
2. **Activity 2 (`ConversionActivity`)**: muestra el título de la categoría, un campo para introducir el valor, dos Spinners (From y To) con las unidades de esa categoría, un botón para convertir, el resultado, un botón para intercambiar unidades y otro para volver a elegir categoría.

## Categorías disponibles

Distancia, Temperatura, Volumen, Masa, Tiempo, Información digital, Moneda, Área, Velocidad y Presión.

## Elementos incluidos

| Elemento | Dónde se usa |
|---|---|
| Button | Continuar, Convert, Swap units, Choose another category |
| EditText | Valor a convertir |
| TextView | Título de la categoría y resultado |
| Spinner | Categoría (Activity 1) y unidades From / To (Activity 2) |
| Snackbar | Mensajes de error y confirmación (no se usa Toast) |
| Bitmap | Imagen de la categoría en la Activity 1 (`BitmapFactory` + `Bitmap.createScaledBitmap`) |
| Segunda Activity | `ConversionActivity`, abierta con un `Intent` |

## Estructura del código

```
app/src/main/java/com/example/multi_converter_actividad1/
├── MainActivity.java        Activity 1: categoría, imagen Bitmap e Intent
├── ConversionActivity.java  Activity 2: entrada, validación y resultado
├── Category.java            Enum con las categorías y sus unidades
├── Unit.java                Unidad de medida (nombre y datos de conversión)
└── Converter.java           Lógica de conversión genérica
```

## Paso de datos entre Activities

`MainActivity` envía el nombre de la constante del enum `Category` (por ejemplo `DISTANCIA`) usando la clave `ConversionActivity.EXTRA_CATEGORY`. `ConversionActivity` lo recibe con `getStringExtra` y lo convierte de nuevo en `Category` con `Category.valueOf`. El orden de las categorías del Spinner debe coincidir con el orden de las constantes del enum.

## Lógica de conversión

La conversión no usa una cadena de `if-else` ni `switch` para cada par de unidades. Cada categoría define sus unidades con sus factores respecto a una unidad de referencia, y `Converter.convert(valor, origen, destino)` aplica una fórmula general que sirve para todas las unidades de la categoría. La temperatura se trata aparte porque sus escalas tienen distinto punto cero (factor y desplazamiento).

## Validación y mensajes (Snackbar)

La aplicación avisa con un Snackbar cuando:

- No se ha elegido categoría.
- El campo de valor está vacío.
- El valor no es un número válido.
- Se introduce un valor negativo en una categoría que no lo admite (solo la temperatura lo permite).
- No se han seleccionado las dos unidades.
- La conversión se ha completado correctamente.

## Cómo ejecutar el proyecto

1. Clonar el repositorio: `git clone <url-del-repositorio>`
2. Abrir la carpeta en Android Studio y esperar a que termine la sincronización de Gradle.
3. Ejecutar la app en un emulador o dispositivo con el botón Run.

El APK de debug se genera con **Build > Assemble Project** y queda en `app/build/outputs/apk/debug/app-debug.apk`.

## Trabajo con Git

- Rama `xavi`: parte de la Activity 1.
- Rama `saul`: parte de la Activity 2.
- Rama `main`: versión final con el merge de ambas ramas.
