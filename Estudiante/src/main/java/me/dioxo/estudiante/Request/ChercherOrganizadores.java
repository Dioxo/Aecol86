package me.dioxo.estudiante.Request;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import me.dioxo.estudiante.Constantes;

public class ChercherOrganizadores extends StringRequest {
    private static final String CHERCHER_ORGNIZADORES = Constantes.CHERCHER_ORGNIZADORES;

    public ChercherOrganizadores(Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(CHERCHER_ORGNIZADORES, listener, errorListener);

    }
}
