package me.dioxo.estudiante.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import me.dioxo.estudiante.Constantes;


public class LoginRequest extends StringRequest {

    private static final String route = Constantes.LOGIN_ROUTE_ESTUDIANTE;
    private Map<String, String > parametres;

    public LoginRequest(String email, Response.Listener<String> listener, Response.ErrorListener error){
        super(Method.POST,route,listener,error);

        parametres = new HashMap<>();
        parametres.put("email_estudiante",email);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parametres;
    }
}
