package cl.ipvg.puebadb;


import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BotilleriaAdapter adapter;
    private List<Botilleria> botilleriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerViewBotillerias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        botilleriaList = new ArrayList<>();
        adapter = new BotilleriaAdapter(this, botilleriaList);
        recyclerView.setAdapter(adapter);

        // Obtener referencia a Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("botillerias");

        // Leer datos de Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                botilleriaList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Botilleria botilleria = dataSnapshot.getValue(Botilleria.class);
                    botilleriaList.add(botilleria);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled( DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());
            }
        });
    }

}