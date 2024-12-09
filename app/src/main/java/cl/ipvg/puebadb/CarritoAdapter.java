package cl.ipvg.puebadb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder> {

    private Context context;
    private List<ProductoCarrito> productos;

    public CarritoAdapter(Context context, List<ProductoCarrito> productos) {
        this.context = context;
        this.productos = productos;
    }


    @Override
    public CarritoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false);
        return new CarritoViewHolder(view);
    }

    @Override
    public void onBindViewHolder( CarritoViewHolder holder, int position) {
        ProductoCarrito producto = productos.get(position);

        holder.tvNombre.setText(producto.getNombre());
        holder.tvCantidad.setText("Cantidad: " + producto.getCantidad());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
        holder.tvTotal.setText("Total: $" + producto.calcularTotal());

        // Eliminar producto del carrito al mantener presionado
        holder.itemView.setOnLongClickListener(v -> {
            productos.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Producto eliminado del carrito", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    static class CarritoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad, tvPrecio, tvTotal;

        public CarritoViewHolder( View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreProducto);
            tvCantidad = itemView.findViewById(R.id.tvCantidadProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecioProducto);
            tvTotal = itemView.findViewById(R.id.tvTotalProducto);
        }
    }
}
