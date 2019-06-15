package me.dioxo.aecol86.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Estudiante;

public class DeletePersona extends StringRequest {

    private static final String route = Constantes.DELETE_PERSONA;
    private Map<String, String > parametres;

    public DeletePersona(Response.Listener<String> listener, Estudiante estudiante){
        super(Method.POST,route,listener,null);

        parametres = new HashMap<>();
        parametres.put("idEstudiante", estudiante.getIdEstudiante());
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}
