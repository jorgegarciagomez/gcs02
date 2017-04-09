package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by koke on 18/03/2017.
 */

public class UsuarioDTO {
    private static Map<String, String> usuarios = new HashMap<String, String>();

    public UsuarioDTO() {
        usuarios.put("gcs@email.com", "gcs");
    }

    public boolean isValid(String user, String pass) {
        //Log.i("[UsuarioDTO] isValid", "Entrada: [" + user + "][" + pass + "] Map:[" + usuarios.get(user) + "]");
        if(usuarios.get(user) != null && usuarios.get(user).equals(pass)) {
            return true;
        }
        return false;
    }

    public boolean newUsuario(String user, String pass) {
        if(usuarios.get(user) == null) {
            usuarios.put(user, pass);
            return true;
        }
        return false;
    }
}
