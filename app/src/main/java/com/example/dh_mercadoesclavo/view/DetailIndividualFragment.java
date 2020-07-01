package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.dh_mercadoesclavo.R;
import com.example.dh_mercadoesclavo.controller.ArticuloController;
import com.example.dh_mercadoesclavo.databinding.FragmentDetailIndividualBinding;
import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.Pic;
import com.example.dh_mercadoesclavo.util.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailIndividualFragment extends Fragment {

    private FragmentDetailIndividualBinding binding;
    private DetailIndividualFragmentListener listener;
    private Button buttonUbicacion;

    public DetailIndividualFragment() {
        // Required empty public constructor
    }

    public DetailIndividualFragment(DetailIndividualFragmentListener listener) {
        this.listener = listener;
    }

    public static DetailIndividualFragment crearDetailIndividualFragment(Articulo unArticulo, DetailIndividualFragmentListener listener){
        DetailIndividualFragment fragment = new DetailIndividualFragment(listener);
        Bundle datosDeArticulo = new Bundle();
        datosDeArticulo.putSerializable("articulo", unArticulo);
        fragment.setArguments(datosDeArticulo);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailIndividualBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle bundle = getArguments();
        final Articulo articulo = (Articulo) bundle.getSerializable("articulo");
        buttonUbicacion = binding.fragmentDetailIndividualButtonUbicacion;

        ArticuloController articuloController = new ArticuloController();
        articuloController.getItemsPorId(articulo.getId(), new ResultListener<Articulo>() {
            @Override
            public void onFinish(final Articulo result) {

                Glide.with(getActivity()).load(result.getPictures().get(0).getUrl()).into(binding.fragmentDetailIndividualImageView);
                binding.fragmentDetailIndividualTextViewNombre.setText(result.getTitle());
                binding.fragmentDetailIndividualTextViewPrecio.setText(String.format(getContext().getResources().getString(R.string.valor), result.getPrice().toString()));
                binding.fragmentDetailIndividualTextViewDescripcion.setText(result.getDescripcion());

                buttonUbicacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClickButtonUbicacionDetailIndividualFragment(result);
                    }
                });
            }
        });

        binding.fragmentDetailIndividualButtonFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickButtonFavoritosDetailIndividualFragment(articulo);
            }
        });

        return view;
    }

    public interface DetailIndividualFragmentListener{
        void onClickButtonFavoritosDetailIndividualFragment(Articulo articulo);
        void onClickButtonUbicacionDetailIndividualFragment(Articulo articulo);
    }
}
