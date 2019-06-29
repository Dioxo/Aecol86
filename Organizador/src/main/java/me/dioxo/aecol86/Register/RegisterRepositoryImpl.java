package me.dioxo.aecol86.Register;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import me.dioxo.aecol86.Organizador;
import me.dioxo.aecol86.Request.RegisterOrganizador;
import me.dioxo.aecol86.libs.ApplicationContextProvider;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;


class RegisterRepositoryImpl implements RegisterRepository {
    private EventBus eventBus;

    public RegisterRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void registerUser(Organizador organizador) {
        Log.i("Register", "repository");
        Response.Listener<String> success = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.i("Register", response);

                    RegisterEvent registerEvent;

                    // si l'actualisation est effectuée
                    if(jsonObject.getBoolean("result")){
                        registerEvent = new RegisterEvent(RegisterEvent.REGISTER_SUCCESS);
                    }else{
                        registerEvent = new RegisterEvent(RegisterEvent.REGISTER_ERROR);
                    }

                    eventBus.post(registerEvent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterOrganizador registerOrganizador = new RegisterOrganizador(success, organizador);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(registerOrganizador);

    }
}
