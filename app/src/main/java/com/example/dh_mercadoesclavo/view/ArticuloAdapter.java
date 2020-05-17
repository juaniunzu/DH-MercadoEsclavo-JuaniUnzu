package com.example.dh_mercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.model.Articulo;

import java.util.List;

// entre boquitas se pone lo que retornara el onCreateViewHolder.
// tambien hace que el holder del onBindViewHolder sea de tipo ArticuloViewHolder,
// entonces nos evitaremos un casteo
public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloViewHolder> {

    private List<Articulo> listaArticulos;
    private ArticuloAdapterListener articuloAdapterListener;

    public ArticuloAdapter(List<Articulo> listaArticulos, ArticuloAdapterListener listener) {
        this.listaArticulos = listaArticulos;
        this.articuloAdapterListener = listener;
    }

    @NonNull
    @Override
    //metodo que crea la celda
    //devuelve ArticuloViewHolder en vez de ViewHolder porque se especifico en el extends de la clase
    public ArticuloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflador para pasar a View la celda xml
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //celda inflada a xml
        View view = layoutInflater.inflate(R.layout.celda_articulo, parent, false);

        return (new ArticuloViewHolder(view));
    }

    @Override
    //Le da los datos a la celda creada.
    //holder es tipo ArticuloViewHolder en vez de ViewHolder porque se especifico en el
    //extends de la clase adapter. Ya no es necesario castearlo para que conozca el onBind
    public void onBindViewHolder(@NonNull ArticuloViewHolder holder, int position) {

        //tomo un articulo determinado segun la posicion y lo guardo en variable local.
        Articulo unArticulo = this.listaArticulos.get(position);
        //el onBind esta detallado abajo, es un metodo de la clase ArticuloViewHolder.
        //uso como parametro el articulo guardado arriba
        holder.onBind(unArticulo);

    }

    @Override
    public int getItemCount() {
        return this.listaArticulos.size();
    }

    //representacion java de la celda
    protected class ArticuloViewHolder extends RecyclerView.ViewHolder {

        //atributos java de la celda
        private ImageView celdaArticuloImageView;
        private TextView celdaArticuloTextViewNombre;
        private TextView celdaArticuloTextViewPrecio;

        public ArticuloViewHolder(@NonNull View itemView) {
            super(itemView);

            //enlazo los atributos java con los elementos del xml
            celdaArticuloImageView = itemView.findViewById(R.id.celdaArticuloImageView);
            celdaArticuloTextViewNombre = itemView.findViewById(R.id.celdaArticuloTextViewNombre);
            celdaArticuloTextViewPrecio = itemView.findViewById(R.id.celdaArticuloTextViewPrecio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articulo articulo = listaArticulos.get(getAdapterPosition());

                    articuloAdapterListener.onClickArticulo(articulo);
                }
            });

        }

        //este metodo se llama arriba en el onBindViewHolder, metodo que le da los datos del articulo a la celda.
        //se hace aca abajo para modular el codigo
        public void onBind(Articulo unArticulo) {

            celdaArticuloImageView.setImageResource(unArticulo.getImagen());
            celdaArticuloTextViewNombre.setText(unArticulo.getNombre());
            celdaArticuloTextViewPrecio.setText(unArticulo.getPrecio());

        }
    }

    public interface ArticuloAdapterListener {
        void onClickArticulo(Articulo unArticulo);
    }

}
