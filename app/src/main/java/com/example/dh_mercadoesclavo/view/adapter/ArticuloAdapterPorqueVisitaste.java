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

public class ArticuloAdapterPorqueVisitaste extends RecyclerView.Adapter<ArticuloAdapterPorqueVisitaste.ArticuloPorqueVisitasteViewHolder> {

    private List<Articulo> listaArticulos;
    private ArticuloAdapterPorqueVisitasteListener listener;

    public ArticuloAdapterPorqueVisitaste(List<Articulo> listaArticulos, ArticuloAdapterPorqueVisitasteListener listener) {
        this.listaArticulos = listaArticulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticuloPorqueVisitasteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_articulo_porque_visitaste, parent, false);

        return new ArticuloPorqueVisitasteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloPorqueVisitasteViewHolder holder, int position) {

        Articulo articulo = this.listaArticulos.get(position);
        holder.onBind(articulo);

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    protected class ArticuloPorqueVisitasteViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaPorqueVisitasteImageView;
        private TextView celdaPorqueVisitasteTitulo;
        private TextView celdaPorqueVisitastePrecio;

        public ArticuloPorqueVisitasteViewHolder(@NonNull View itemView) {
            super(itemView);

            celdaPorqueVisitasteImageView = itemView.findViewById(R.id.celdaPorqueVisitasteImageView);
            celdaPorqueVisitasteTitulo = itemView.findViewById(R.id.celdaPorqueVisitasteTitulo);
            celdaPorqueVisitastePrecio = itemView.findViewById(R.id.celdaPorqueVisitastePrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = listaArticulos.get(getAdapterPosition());
                    List<Articulo> articuloList = listaArticulos;

                    listener.onClickAdapterPorqueVisitaste(articulo, articuloList);
                }
            });

        }

        public void onBind(Articulo unArticulo){

            Glide.with(itemView.getContext())
                    .load(unArticulo.getFoto())
                    .centerCrop()
                    .into(celdaPorqueVisitasteImageView);
            celdaPorqueVisitasteTitulo.setText(unArticulo.getTitle());
            celdaPorqueVisitastePrecio.setText(String.format(itemView.getContext().getResources().getString(R.string.valor), unArticulo.getPrice().toString()));

        }
    }

    public interface ArticuloAdapterPorqueVisitasteListener{
        void onClickAdapterPorqueVisitaste(Articulo articulo, List<Articulo> articuloList);
    }



}
