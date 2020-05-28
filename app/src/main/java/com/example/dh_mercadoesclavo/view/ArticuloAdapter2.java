package com.example.dh_mercadoesclavo.view;

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

public class ArticuloAdapter2 extends RecyclerView.Adapter<ArticuloAdapter2.Articulo2ViewHolder> {

    private List<Articulo> listaArticulos;
    private Articulo2AdapterListener listener;

    public ArticuloAdapter2(List<Articulo> listaArticulos, Articulo2AdapterListener listener) {
        this.listaArticulos = listaArticulos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Articulo2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflador para pasar a View la celda xml
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //celda inflada a xml
        View inflate = layoutInflater.inflate(R.layout.celda_articulo_2, parent, false);

        return (new Articulo2ViewHolder(inflate));
    }

    @Override
    public void onBindViewHolder(@NonNull Articulo2ViewHolder holder, int position) {

        //tomo un articulo determinado segun la posicion y lo guardo en variable local.
        Articulo unArticulo = this.listaArticulos.get(position);

        holder.onBind2(unArticulo);

    }

    @Override
    public int getItemCount() {
        return this.listaArticulos.size();
    }

    protected class Articulo2ViewHolder extends RecyclerView.ViewHolder{

        private ImageView celdaArticulo2ImageView;
        private TextView celdaArticulo2TextViewNombre;
        private TextView celdaArticulo2TextViewPrecio;
        private TextView celdaArticulo2TextViewEnvios;


        public Articulo2ViewHolder(@NonNull View itemView) {
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

                    listener.onClickAdapter2(articulo, articuloList);
                }
            });

        }

        public void onBind2(Articulo unArticulo){

            Glide.with(itemView.getContext())
                    .load(unArticulo.getFoto())
                    .centerCrop()
                    .into(celdaArticulo2ImageView);

            celdaArticulo2TextViewNombre.setText(unArticulo.getTitle());
            celdaArticulo2TextViewPrecio.setText(unArticulo.getPrice().toString());
            celdaArticulo2TextViewEnvios.setText(unArticulo.disponibleParaEnviar());
            celdaArticulo2TextViewEnvios.setTextAppearance(unArticulo.campoEnvioStyle());

        }
    }

    public interface Articulo2AdapterListener{
        void onClickAdapter2(Articulo unArticulo, List<Articulo> articuloList);
    }


}
