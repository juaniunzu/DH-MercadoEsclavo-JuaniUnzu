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

public class ArticuloAdapterRecomendados extends RecyclerView.Adapter<ArticuloAdapterRecomendados.ArticuloRecomendadosViewHolder> {

    private List<Articulo> listaArticulos;
    private ArticuloAdapterRecomendadosListener listener;

    public ArticuloAdapterRecomendados(List<Articulo> listaArticulos, ArticuloAdapterRecomendadosListener listener) {
        this.listaArticulos = listaArticulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticuloRecomendadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflador para pasar a View la celda xml
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //celda inflada a xml
        View inflate = layoutInflater.inflate(R.layout.celda_articulo_recomendados, parent, false);

        return (new ArticuloRecomendadosViewHolder(inflate));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticuloRecomendadosViewHolder holder, int position) {

        //tomo un articulo determinado segun la posicion y lo guardo en variable local.
        Articulo unArticulo = this.listaArticulos.get(position);

        holder.onBind(unArticulo);

    }

    @Override
    public int getItemCount() {
        return this.listaArticulos.size();
    }

    public void addArticuloList(List<Articulo> articuloList){
        listaArticulos.addAll(articuloList);
        notifyDataSetChanged();
    }

    protected class ArticuloRecomendadosViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaArticulo2ImageView;
        private TextView celdaArticulo2TextViewNombre;
        private TextView celdaArticulo2TextViewPrecio;
        private TextView celdaArticulo2TextViewEnvios;


        public ArticuloRecomendadosViewHolder(@NonNull View itemView) {
            super(itemView);

            celdaArticulo2ImageView = itemView.findViewById(R.id.celdaArticulo2ImageView);
            celdaArticulo2TextViewNombre = itemView.findViewById(R.id.celdaArticulo2TextViewNombre);
            celdaArticulo2TextViewPrecio = itemView.findViewById(R.id.celdaArticulo2TextViewPrecio);
            celdaArticulo2TextViewEnvios = itemView.findViewById(R.id.celdaArticulo2TextViewEnvios);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = listaArticulos.get(getAdapterPosition());
                    List<Articulo> articuloList = listaArticulos;

                    listener.onClickAdapterRecomendados(articulo, articuloList);
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

    public interface ArticuloAdapterRecomendadosListener {
        void onClickAdapterRecomendados(Articulo unArticulo, List<Articulo> articuloList);
    }


}
