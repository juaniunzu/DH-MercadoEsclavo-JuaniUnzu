package com.example.dh_mercadoesclavo.dao;

import androidx.annotation.NonNull;

import com.example.dh_mercadoesclavo.model.Articulo;
import com.example.dh_mercadoesclavo.model.CategoriaPadre;
import com.example.dh_mercadoesclavo.util.ResultListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArticuloFirestoreDao {

    private FirebaseFirestore db;
    public static final String ARTICULOS = "articulos";
    public static final String NOMBRE = "MercadoEsclavo";
    public static final String CATEGORIAS = "categorias";

    public ArticuloFirestoreDao() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void agregarArticuloAFirebase(final Articulo articulo, FirebaseUser usuarioLogueado, final ResultListener<Articulo> listener){
        db.collection(NOMBRE)
                .document(usuarioLogueado.getUid())
                .collection(ARTICULOS)
                .document(articulo.getId())
                .set(articulo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onFinish(articulo);
            }
        });
    }

    public void agregarCategoriaFavoritaAFirebase(final CategoriaPadre categoriaPadre, FirebaseUser usuarioLogueado, final ResultListener<CategoriaPadre> listener){
        db.collection(NOMBRE)
                .document(usuarioLogueado.getUid())
                .collection(CATEGORIAS)
                .document(categoriaPadre.getId())
                .set(categoriaPadre).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onFinish(categoriaPadre);
            }
        });
    }

    public void consultarArticulosEnFirebase(FirebaseUser usuarioLogueado, final ResultListener<List<Articulo>> listener){
        db.collection(NOMBRE)
                .document(usuarioLogueado.getUid())
                .collection(ARTICULOS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Articulo> listaArticulosFirebase = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                Articulo articulo = queryDocumentSnapshot.toObject(Articulo.class);
                                listaArticulosFirebase.add(articulo);
                            }
                            listener.onFinish(listaArticulosFirebase);
                        }
                    }
                });
    }

}
