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

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosAdapterViewHolder> {

    private List<Articulo> articuloList;
    private FavoritosAdapterListener listener;

    public FavoritosAdapter(List<Articulo> articuloList, FavoritosAdapterListener listener) {
        this.articuloList = articuloList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FavoritosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflador para pasar a View la celda xml
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //celda inflada a xml
        View inflate = layoutInflater.inflate(R.layout.celda_articulo_recomendados, parent, false);

        return new FavoritosAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapterViewHolder holder, int position) {
        Articulo unArticulo = this.articuloList.get(position);
        holder.onBind(unArticulo);
    }

    @Override
    public int getItemCount() {
        return this.articuloList.size();
    }

    protected class FavoritosAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaArticulo2ImageView;
        private TextView celdaArticulo2TextViewNombre;
        private TextView celdaArticulo2TextViewPrecio;
        private TextView celdaArticulo2TextViewEnvios;

        public FavoritosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            celdaArticulo2ImageView = itemView.findViewById(R.id.celdaArticulo2ImageView);
            celdaArticulo2TextViewNombre = itemView.findViewById(R.id.celdaArticulo2TextViewNombre);
            celdaArticulo2TextViewPrecio = itemView.findViewById(R.id.celdaArticulo2TextViewPrecio);
            celdaArticulo2TextViewEnvios = itemView.findViewById(R.id.celdaArticulo2TextViewEnvios);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = articuloList.get(getAdapterPosition());
                    listener.onClickArticuloFavoritosAdapter(articulo);
                }
            });

        }

        public void onBind(Articulo unArticulo){

            Glide.with(itemView.getContext())
                    .load(unArticulo.getFoto())
                    .centerCrop()
                    .into(celdaArticulo2ImageView);

            celdaArticulo2TextViewNombre.setText(unArticulo.getTitle());
            celdaArticulo2TextViewPrecio.setText(String.format(itemView.getContext().getResources().getString(R.string.valor), unArticulo.getPrice().toString()));
            celdaArticulo2TextViewEnvios.setText(unArticulo.disponibleParaEnviar());
            celdaArticulo2TextViewEnvios.setTextAppearance(unArticulo.campoEnvioStyle());

        }
    }

    public interface FavoritosAdapterListener{
        void onClickArticuloFavoritosAdapter(Articulo articulo);
    }

}
