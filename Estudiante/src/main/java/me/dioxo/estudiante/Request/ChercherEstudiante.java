package me.dioxo.estudiante.Request;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.libs.ApplicationContextProvider;

public class ChercherEstudiante extends StringRequest {

    private static final String route = Constantes.CHERCHER_ESTUDIANTE;
    private Map<String, String > parametres;

    public ChercherEstudiante(Response.Listener<String> listener, Response.ErrorListener error){
        super(Method.POST,route,listener,error);
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);

        parametres = new HashMap<>();
        parametres.put("idEstudiante", id_user);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}
