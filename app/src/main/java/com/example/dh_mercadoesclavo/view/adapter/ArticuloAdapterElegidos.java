package com.example.dh_mercadoesclavo.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.List;

public class ArticuloAdapterElegidos extends RecyclerView.Adapter<ArticuloAdapterElegidos.ArticuloElegidosViewHolder> {

    private List<Articulo> listaArticulos;
    private ArticuloAdapterElegidosListener listener;

    public ArticuloAdapterElegidos(List<Articulo> listaArticulos, ArticuloAdapterElegidosListener listener) {
        this.listaArticulos = listaArticulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticuloElegidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_articulo_elegidos, parent, false);

        return (new ArticuloElegidosViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloElegidosViewHolder holder, int position) {
        Articulo articulo = this.listaArticulos.get(position);
        holder.onBind(articulo);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected class ArticuloElegidosViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaArticuloElegidosImageView;
        private TextView celdaArticuloElegidosTextViewNombre;
        private TextView celdaArticuloElegidosTextViewPrecio;

        public ArticuloElegidosViewHolder(@NonNull View itemView) {
            super(itemView);

            celdaArticuloElegidosImageView = itemView.findViewById(R.id.celdaArticuloElegidosImageView);
            celdaArticuloElegidosTextViewNombre = itemView.findViewById(R.id.celdaArticuloElegidosTextViewNombre);
            celdaArticuloElegidosTextViewPrecio = itemView.findViewById(R.id.celdaArticuloElegidosTextViewPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = listaArticulos.get(getAdapterPosition());
                    List<Articulo> articuloList = listaArticulos;

                    listener.onClickAdapterElegidos(articulo, articuloList);
                }
            });

        }

        public void onBind(Articulo unArticulo){

            Glide.with(itemView.getContext())
                    .load(unArticulo.getFoto())
                    .centerCrop()
                    .into(celdaArticuloElegidosImageView);
            celdaArticuloElegidosTextViewNombre.setText(unArticulo.getTitle());
            celdaArticuloElegidosTextViewPrecio.setText(String.format(itemView.getContext()
                    .getResources().getString(R.string.valor), unArticulo.getPrice().toString()));

        }
    }

    public interface ArticuloAdapterElegidosListener{
        void onClickAdapterElegidos(Articulo articulo, List<Articulo> articuloList);
    }

}
