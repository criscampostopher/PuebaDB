package cl.ipvg.puebadb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText etNombre, etPassword;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Helper.guardarBotilleria();

        // Inicializar Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");

        // Referencias de UI
        etNombre = findViewById(R.id.etNombre);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        // Acciones de botones
        btnRegister.setOnClickListener(v -> registerUser());
        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void registerUser() {
        String nombre = etNombre.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generar un ID único para cada usuario
        String userId = databaseReference.push().getKey();

        if (userId != null) {
            User user = new User(nombre, password);
            databaseReference.child(userId).setValue(user)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void loginUser() {
        String nombre = etNombre.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Para login, necesitarás implementar una lógica adicional para buscar al usuario en la base de datos
        // Este ejemplo no incluye la verificación real de inicio de sesión

        // Buscar el usuario en la base de datos
        databaseReference.orderByChild("nombre").equalTo(nombre).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult().exists()) {
                        // Verificar si se encontró al usuario
                        task.getResult().getChildren().forEach(snapshot -> {
                            String dbPassword = snapshot.child("contraseña").getValue(String.class);
                            if (dbPassword != null && dbPassword.equals(password)) {
                                // Inicio de sesión exitoso
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);

                            } else {
                                // Contraseña incorrecta
                                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // Usuario no encontrado
                        Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    // Error al buscar en la base de datos
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    // Clase modelo para usuarios
    public static class User {
        public String nombre;
        public String contraseña;

        public User() {
            // Constructor vacío requerido para Firebase
        }

        public User(String nombre, String contraseña) {
            this.nombre = nombre;
            this.contraseña = contraseña;
        }
    }

}