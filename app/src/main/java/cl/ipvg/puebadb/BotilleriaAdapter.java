package cl.ipvg.puebadb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BotilleriaAdapter extends RecyclerView.Adapter<BotilleriaAdapter.ViewHolder> {

    private Context context;
    private List<Botilleria> botillerias;

    public BotilleriaAdapter(Context context, List<Botilleria> botillerias) {
        this.context = context;
        this.botillerias = botillerias;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_botilleria, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Botilleria botilleria = botillerias.get(position);

        // Configurar los datos en las vistas
        holder.tvNombreBotilleria.setText(botilleria.getNombre());
        holder.tvDireccionBotilleria.setText(botilleria.getDireccion());

        // Cargar la imagen con Glide
        Glide.with(context).load(botilleria.getImagenURL()).into(holder.imgBotilleria);

        // Configurar el botón "Ver Mapa"
        holder.btnVerEnMapa.setOnClickListener(v -> {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra("LATITUD", botilleria.getCoordenadas().getLatitud());
            intent.putExtra("LONGITUD", botilleria.getCoordenadas().getLongitud());
            context.startActivity(intent);
        });
        //Debo mandar los datos del usuario para que el joset los registre en la tabla compras

        holder.btnComprar.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity3.class);
            intent.putExtra("LATITUD", botilleria.getCoordenadas().getLatitud());
            intent.putExtra("LONGITUD", botilleria.getCoordenadas().getLongitud());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return botillerias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreBotilleria, tvDireccionBotilleria;
        ImageView imgBotilleria;
        Button btnVerEnMapa;
        Button btnComprar;


        public ViewHolder( View itemView) {
            super(itemView);
            tvNombreBotilleria = itemView.findViewById(R.id.tvNombreBotilleria);
            tvDireccionBotilleria = itemView.findViewById(R.id.tvDireccionBotilleria);
            imgBotilleria = itemView.findViewById(R.id.imgBotilleria);
            btnVerEnMapa = itemView.findViewById(R.id.btnVerEnMapa);
            btnComprar = itemView.findViewById(R.id.btnComprar);
        }
    }
}
