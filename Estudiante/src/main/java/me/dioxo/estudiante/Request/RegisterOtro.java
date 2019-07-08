package me.dioxo.estudiante.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.Estudiante;

public class RegisterOtro extends StringRequest {
    private static final String route = Constantes.REGISTER_OTRO;
    private Map<String, String> parametres;

    public RegisterOtro(Response.Listener<String> listener, Estudiante estudiante) {
        super(Method.POST, route, listener, null);

        /*
         * Change params to send to request
         * */


        parametres = new HashMap<>();
        parametres.put("nombre_estudiante", estudiante.getNombre_estudiante());
        parametres.put("email_estudiante", estudiante.getEmail_estudiante());
        parametres.put("password_estudiante", estudiante.getPassword());
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}