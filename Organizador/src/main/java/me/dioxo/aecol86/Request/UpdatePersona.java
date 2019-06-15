package me.dioxo.aecol86.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Estudiante;

public class UpdatePersona extends StringRequest {

    private static final String route = Constantes.UPDATE_PERSONA;
    private Map<String, String > parametres;

    public UpdatePersona(Response.Listener<String> listener, Estudiante estudiante){
        super(Method.POST,route,listener,null);

        parametres = new HashMap<>();
        parametres.put("idPersona", estudiante.getIdEstudiante());
        parametres.put("nombre", estudiante.getNombre_estudiante());
        parametres.put("numTelefono", estudiante.getTelefono_estudiante());
        parametres.put("fecha", estudiante.getFecha_estudiante());
        parametres.put("transporte", estudiante.getTransporte_estudiante());
        parametres.put("residencia", estudiante.getResidencia_estudiante());
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}