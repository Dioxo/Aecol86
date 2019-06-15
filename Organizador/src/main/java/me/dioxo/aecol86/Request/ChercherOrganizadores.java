package me.dioxo.aecol86.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import me.dioxo.aecol86.Constantes;

public class ChercherOrganizadores extends StringRequest {

    private static final String route = Constantes.CHERCHER_ORGANIZADORES;
    private Map<String, String > parametres;

    public ChercherOrganizadores(Response.Listener<String> listener){
        super(Method.POST,route,listener,null);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}