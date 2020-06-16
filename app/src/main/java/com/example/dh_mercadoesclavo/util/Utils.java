package com.example.dh_mercadoesclavo.util;

import com.google.firebase.auth.FirebaseAuth;

public class Utils {

    public static Boolean haySesionIniciada(){
        return (FirebaseAuth.getInstance().getCurrentUser() != null);
    }

}
