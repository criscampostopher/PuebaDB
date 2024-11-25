package cl.ipvg.puebadb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Helper {

    public static void guardarBotilleria() {
        // Obtén la referencia a la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference botilleriasRef = database.getReference("botillerias");

        // Crear una lista de botillerías a insertar
        agregarBotilleria(botilleriasRef, "Botillería A", "Calle Ficticia 123", "https://example.com/imagen.jpg",-36.590073779504976 ,-72.082143295078 );

    }


    private static void agregarBotilleria(DatabaseReference ref, String nombre, String direccion, String imagenURL, double latitud, double longitud) {
        // Crea un objeto Botilleria
        Botilleria nuevaBotilleria = new Botilleria(nombre, direccion, imagenURL, latitud, longitud);

        // Agrega la botillería a Firebase
        ref.push().setValue(nuevaBotilleria);
    }
    }

