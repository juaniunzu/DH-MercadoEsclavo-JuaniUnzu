package com.example.dh_mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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

import java.util.ArrayList;
import java.util.List;

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

        List<String> urlImagenes = new ArrayList<>();
        for (int i = 0; i < articulo.getPictures().size(); i++) {
            String url = articulo.getPictures().get(i).getUrl();
            urlImagenes.add(url);
        }

        List<ImagenFragment> listaFragments = generarImageFragments(urlImagenes);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), listaFragments);
        ViewPager viewPager = view.findViewById(R.id.fragmentDetailViewPager);
        viewPager.setAdapter(viewPagerAdapter);

        return view;
    }

    public List<ImagenFragment> generarImageFragments(List<String> url){
        List<ImagenFragment> lista = new ArrayList<>();
        for (String s : url) {
            ImagenFragment imagenFragment = ImagenFragment.crearImagenFragment(s);
            lista.add(imagenFragment);
        }
        return lista;
    }

    public interface DetailIndividualFragmentListener{
        void onClickButtonFavoritosDetailIndividualFragment(Articulo articulo);
        void onClickButtonUbicacionDetailIndividualFragment(Articulo articulo);
    }
}
