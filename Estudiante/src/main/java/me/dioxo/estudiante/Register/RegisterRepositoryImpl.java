package me.dioxo.estudiante.Register;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.Request.ChercherEstudiante;
import me.dioxo.estudiante.Request.RegisterEstudiante;
import me.dioxo.estudiante.Request.RegisterOtro;
import me.dioxo.estudiante.Request.UpdatePersona;
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

    @Override
    public void chercherInformationEstudiante() {
        Response.Listener<String> success = response -> {

            RegisterEvent event;

            // si l'actualisation est effectuée

            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Log.i("Response", response);
            Type type = new TypeToken<Estudiante>(){}.getType();

            Estudiante estudiante = gson.fromJson(response, type);

            if(estudiante != null){
                event = new RegisterEvent(RegisterEvent.CHERCHER_SUCCESS, estudiante);
            }else{
                event = new RegisterEvent(RegisterEvent.CHERCHER_ERROR, "Error al cargar su información");
            }


            eventBus.post(event);

        };

        Response.ErrorListener error = errorResponse -> {
            RegisterEvent event = new RegisterEvent(RegisterEvent.REGISTER_ERROR, errorResponse.toString());
            eventBus.post(event);
        };
        ChercherEstudiante chercherEstudiante = new ChercherEstudiante(success, error);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(chercherEstudiante);

    }

    @Override
    public void actualizarDatos(Estudiante estudiante) {
        Response.Listener<String> success = response -> {
            try {
                RegisterEvent event;

                JSONObject jsonObject = new JSONObject(response);

                if(jsonObject.getBoolean("result")){
                    event = new RegisterEvent(RegisterEvent.UPDATE_SUCCESS, "Actualizaciones realizadas correctamente");
                }else{
                    event = new RegisterEvent(RegisterEvent.UPDATE_ERROR, "Error al momento de actualizar");
                }

                eventBus.post(event);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        Response.ErrorListener errorListener = error -> {
            RegisterEvent event = new RegisterEvent(RegisterEvent.UPDATE_ERROR, error.toString());

            eventBus.post(event);
        };

        UpdatePersona updatePersona = new UpdatePersona(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(updatePersona);

    }

    @Override
    public void registerOtro(Estudiante estudiante) {
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

        RegisterOtro registerOtro = new RegisterOtro(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(registerOtro);
    }
}
