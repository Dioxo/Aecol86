package me.dioxo.aecol86.Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Organizador;

public class RegisterOrganizador extends StringRequest {
    private static final String route = Constantes.REGISTER_USER;
    private Map<String, String> parametres;

    public RegisterOrganizador(Response.Listener<String> listener, Organizador organizador) {
        super(Method.POST, route, listener, null);

        /*
         * Change params to send to request
         * */
        parametres = new HashMap<>();
        parametres.put("email_organizador", organizador.getEmail());
        parametres.put("nombre_organizador", organizador.getNombre());
        parametres.put("password_organizador", organizador.getPassword());
        parametres.put("telefono_organizador", organizador.getTelefono());
    }

    @Override
    protected Map<String, String> getParams() {
        return parametres;
    }
}