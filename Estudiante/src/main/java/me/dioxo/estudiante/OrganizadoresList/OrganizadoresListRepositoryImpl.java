package me.dioxo.estudiante.OrganizadoresList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.dioxo.estudiante.Organizador;
import me.dioxo.estudiante.Request.ChercherOrganizadores;
import me.dioxo.estudiante.libs.ApplicationContextProvider;
import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;

class OrganizadoresListRepositoryImpl implements OrganizadoresListRepository {
    private EventBus eventBus;
    private OrganizadoresListEvent event;

    public OrganizadoresListRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void ChercherOrganizadores() {
        Response.Listener<String> success = response -> {

            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Type type = new TypeToken<ArrayList<Organizador>>(){}.getType();

            ArrayList<Organizador> organizadores = gson.fromJson(response, type);

            if(!organizadores.isEmpty()){
                event = new OrganizadoresListEvent(OrganizadoresListEvent.CHERCHER_SUCCESS, organizadores);
            }else{
                event = new OrganizadoresListEvent(OrganizadoresListEvent.CHERCHER_ERROR, "No se encontraron resultados");
            }


            eventBus.post(event);

        };

        Response.ErrorListener errorListener = error -> {
            event = new OrganizadoresListEvent(OrganizadoresListEvent.CHERCHER_ERROR, error.toString());
            eventBus.post(event);

        };

        ChercherOrganizadores chercherOrganizadores = new ChercherOrganizadores(success, errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(chercherOrganizadores);
    }
}
