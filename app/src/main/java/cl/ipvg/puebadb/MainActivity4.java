package cl.ipvg.puebadb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvTotalCarrito;
    private Button btnFinalizarCompra;
    private CarritoAdapter carritoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        recyclerView = findViewById(R.id.recyclerViewCarrito);
        tvTotalCarrito = findViewById(R.id.tvTotalCarrito);
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        carritoAdapter = new CarritoAdapter(this, CarritoManager.getInstance().getCarrito());
        recyclerView.setAdapter(carritoAdapter);

        actualizarTotal();

        btnFinalizarCompra.setOnClickListener(v -> finalizarCompra());
    }

    private void actualizarTotal() {
        double total = 0;
        for (ProductoCarrito producto : CarritoManager.getInstance().getCarrito()) {
            total += producto.calcularTotal();
        }
        tvTotalCarrito.setText("Total: $" + total);
    }

    private void finalizarCompra() {
        if (CarritoManager.getInstance().getCarrito().isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear registro de la venta
        String idVenta = FirebaseDatabase.getInstance().getReference("ventas").push().getKey();
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        Venta venta = new Venta(idVenta, fecha, CarritoManager.getInstance().getCarrito(), calcularTotal());

        DatabaseReference ventasRef = FirebaseDatabase.getInstance().getReference("ventas");
        ventasRef.child(idVenta).setValue(venta).addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
            CarritoManager.getInstance().vaciarCarrito();
            finish();
        }).addOnFailureListener(e -> Toast.makeText(this, "Error al finalizar la compra", Toast.LENGTH_SHORT).show());
    }

    private double calcularTotal() {
        double total = 0;
        for (ProductoCarrito producto : CarritoManager.getInstance().getCarrito()) {
            total += producto.calcularTotal();
        }
        return total;
    }
}