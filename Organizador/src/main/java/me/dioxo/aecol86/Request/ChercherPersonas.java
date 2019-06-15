package me.dioxo.aecol86.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import me.dioxo.aecol86.Constantes;

public class ChercherPersonas extends StringRequest {

    private static final String route = Constantes.CHERCHER_PERSONAS;
    private Map<String, String > parametres;

    public ChercherPersonas(Response.Listener<String> listener){
        super(Method.POST,route,listener,null);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}