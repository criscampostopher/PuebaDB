package cl.ipvg.puebadb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView  nombreBotilleria;
        TextView   direccionBotilleria;
         Button  verEnMapaButton;        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Inicializa las vistas
        nombreBotilleria = findViewById(R.id.nombreBoti);
        direccionBotilleria = findViewById(R.id.direccionBoti);
        verEnMapaButton = findViewById(R.id.verMapa);

        // Conecta a Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference botilleriasRef = database.getReference("botillerias");


        // Lee los datos de la base de datos
        botilleriasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Botilleria botilleria = snapshot.getValue(Botilleria.class);
                    if (botilleria != null) {
                        // Muestra los datos en las etiquetas (Labels)

                        nombreBotilleria.setText(botilleria.getNombre());
                        direccionBotilleria.setText(botilleria.getDireccion());

                        // Lógica para el botón de ver en mapa

                        verEnMapaButton.setOnClickListener(v -> {
                            // Llama a la actividad del mapa con los datos de latitud y longitud
                            Intent intent = new Intent(MainActivity2.this, MapsActivity.class);
                            intent.putExtra("LATITUD", botilleria.getCoordenadas().getLatitud());
                            intent.putExtra("LONGITUD", botilleria.getCoordenadas().getLongitud());
                            startActivity(intent);
                        });
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Maneja el error en caso de que la lectura falle
                Log.e("FirebaseError", databaseError.getMessage());
            }
        });
    }

}