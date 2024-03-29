package me.dioxo.aecol86.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Estudiante;

public class InsertPersona extends StringRequest {

    private static final String route = Constantes.INSERT_PERSONA;
    private Map<String, String > parametres;

    public InsertPersona(Response.Listener<String> listener, Estudiante estudiante){
        super(Method.POST,route,listener,null);

        parametres = new HashMap<>();
        parametres.put("nombre_estudiante", estudiante.getNombre_estudiante());
        parametres.put("email_estudiante", estudiante.getEmail_estudiante());
        parametres.put("telefono_estudiante", estudiante.getTelefono_estudiante());
        parametres.put("telefono_emergencia", estudiante.getTelefono_emergencia());
        parametres.put("residencia_estudiante", estudiante.getResidencia_estudiante());
        parametres.put("carrera_estudiante", estudiante.getCarrera_estudiante());
        parametres.put("fecha_estudiante", estudiante.getFecha_estudiante());
        parametres.put("transporte_estudiante", estudiante.getTransporte_estudiante());
        parametres.put("hotel_estudiante", estudiante.getHotel_estudiante());
        parametres.put("info_estudiante", estudiante.getInfo_estudiante());
        parametres.put("nombre_organizador", estudiante.getNombre_organizador());

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}