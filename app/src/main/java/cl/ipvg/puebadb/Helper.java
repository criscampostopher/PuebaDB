package cl.ipvg.puebadb;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Helper {

    public static void guardarBotilleria() {
        // Obtén la referencia a la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference botilleriasRef = database.getReference("botillerias");

        // Crear una lista de botillerías a insertar

           // agregarBotilleria(botilleriasRef, "Botillería El Pipe", "Huilliches383 Esquina Mario Vergara - Población Irene Frei, Chillán, Ñuble", "https://lh5.googleusercontent.com/p/AF1QipP5g4JJnBcnIOXRsdeT1HhYxbpv5xCTTbRFEyh5=w408-h329-k-no",-36.594231265694454 ,-72.09898762160593
        //agregarBotilleria(botilleriasRef, "supermercado y botilleria carito", "Av. El Tejar 1796, Chillán, Ñuble", "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=iUVw0fkmPVITTFdYLZVrvw&cb_client=search.gws-prod.gps&w=408&h=240&yaw=311.76917&pitch=0&thumbfov=100",-36.61710469464875, -72.12189878768123);


    }


    private static void agregarBotilleria(DatabaseReference ref, String nombre, String direccion, String imagenURL, double latitud, double longitud) {
        // Crea un objeto Botilleria
        Botilleria nuevaBotilleria = new Botilleria(nombre, direccion, imagenURL, latitud, longitud);

        // Agrega la botillería a Firebase
        ref.push().setValue(nuevaBotilleria);
    }
    }

