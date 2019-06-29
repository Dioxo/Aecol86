package me.dioxo.aecol86.ListerEstudiantes;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.Request.ChercherPersonas;
import me.dioxo.aecol86.libs.ApplicationContextProvider;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;

class ListerRepositoryImpl implements ListerRepository {

    EventBus eventBus;

    public ListerRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void chercherPersonas() {
        Response.Listener<String> success = response -> {

            ListEvent event;

            // si l'actualisation est effectuée

            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Log.i("Response", response);
            Type type = new TypeToken<List<Estudiante>>(){}.getType();

            ArrayList<Estudiante> estudiantes = gson.fromJson(response, type);

            if(estudiantes != null){
                event = new ListEvent(ListEvent.CHERCHER_SUCCESS, estudiantes);
            }else{
                event = new ListEvent(ListEvent.CHERCHER_ERROR, "No se encontraron resultados");
            }


            eventBus.post(event);

        };

        ChercherPersonas chercherPersonas = new ChercherPersonas(success);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(chercherPersonas);
    }


}
