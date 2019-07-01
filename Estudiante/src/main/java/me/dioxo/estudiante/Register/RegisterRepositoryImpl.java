package me.dioxo.estudiante.Register;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.Request.RegisterEstudiante;
import me.dioxo.estudiante.libs.ApplicationContextProvider;
import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;


class RegisterRepositoryImpl implements RegisterRepository {
    private EventBus eventBus;

    public RegisterRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void registerUser(Estudiante estudiante) {
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

        RegisterEstudiante registerEstudiante = new RegisterEstudiante(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(registerEstudiante);

    }
}
