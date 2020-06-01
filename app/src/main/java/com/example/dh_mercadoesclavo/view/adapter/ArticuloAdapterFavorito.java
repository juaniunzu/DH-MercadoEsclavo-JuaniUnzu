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

public class ArticuloAdapterFavorito extends RecyclerView.Adapter<ArticuloAdapterFavorito.ArticuloFavoritoViewHolder> {

    private List<Articulo> listaArticulos;
    private ArticuloAdapterFavoritoListener listener;

    public ArticuloAdapterFavorito(List<Articulo> listaArticulos, ArticuloAdapterFavoritoListener listener) {
        this.listaArticulos = listaArticulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticuloFavoritoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_articulo_favorito, parent, false);

        return (new ArticuloFavoritoViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloFavoritoViewHolder holder, int position) {
        Articulo articulo = this.listaArticulos.get(position);
        holder.onBind(articulo);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    protected class ArticuloFavoritoViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaArticuloFavoritoImageView;
        private TextView celdaArticuloFavoritoTextViewNombre;
        private TextView celdaArticuloFavoritoTextViewPrecio;

        public ArticuloFavoritoViewHolder(@NonNull View itemView) {
            super(itemView);

            celdaArticuloFavoritoImageView = itemView.findViewById(R.id.celdaArticuloFavoritoImageView);
            celdaArticuloFavoritoTextViewNombre = itemView.findViewById(R.id.celdaArticuloFavoritoTextViewNombre);
            celdaArticuloFavoritoTextViewPrecio = itemView.findViewById(R.id.celdaArticuloFavoritoTextViewPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = listaArticulos.get(getAdapterPosition());
                    List<Articulo> articuloList = listaArticulos;

                    listener.onClickAdapterFavorito(articulo, articuloList);
                }
            });

        }

        public void onBind(Articulo unArticulo){

            Glide.with(itemView.getContext())
                    .load(unArticulo.getFoto())
                    .centerCrop()
                    .into(celdaArticuloFavoritoImageView);
            celdaArticuloFavoritoTextViewNombre.setText(unArticulo.getTitle());
            celdaArticuloFavoritoTextViewPrecio.setText(String.format(itemView.getContext()
                    .getResources().getString(R.string.valor), unArticulo.getPrice().toString()));

        }

    }

    public interface ArticuloAdapterFavoritoListener{
        void onClickAdapterFavorito(Articulo articulo, List<Articulo> articuloList);
    }

}
