package cl.ipvg.puebadb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context context;
    private List<Producto> listaProductos;

    public ProductoAdapter(Context context, List<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }


    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ProductoViewHolder holder, int position) {
        Producto producto = listaProductos.get(position);
        // Configurar los datos en las vistas
        holder.tvNombreProducto.setText(producto.getNombre());
        holder.tvCantidadProducto.setText("Cantidad: " + producto.getCantidad());
        holder.tvPrecioProducto.setText("Precio: $" + producto.getPrecio());

        // Cargar imagen con Glide
        Glide.with(context).load(producto.getImagenUrl()).into(holder.imgProducto);

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreProducto, tvCantidadProducto, tvPrecioProducto;
        ImageView imgProducto;

        public ProductoViewHolder( View itemView) {
            super(itemView);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvCantidadProducto = itemView.findViewById(R.id.tvCantidadProducto);
            tvPrecioProducto = itemView.findViewById(R.id.tvPrecioProducto);
            imgProducto = itemView.findViewById(R.id.imgProducto);
        }
    }
}