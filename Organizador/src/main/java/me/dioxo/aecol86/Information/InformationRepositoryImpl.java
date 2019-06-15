package me.dioxo.aecol86.Information;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.Request.ChercherOrganizadores;
import me.dioxo.aecol86.Request.DeletePersona;
import me.dioxo.aecol86.Request.InsertPersona;
import me.dioxo.aecol86.Request.UpdatePersona;
import me.dioxo.aecol86.libs.ApplicationContextProvider;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;

class InformationRepositoryImpl implements InformationRepository {
    private InformationEvent event;
    private EventBus eventBus;

    public InformationRepositoryImpl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void updatePersona(Estudiante estudiante) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);

                if(jsonObject.getBoolean("result")){
                    event = new InformationEvent(InformationEvent.UPDATE_SUCCESS, "Actualizaciones realizadas correctamente");
                }else{
                    event = new InformationEvent(InformationEvent.UPDATE_ERROR, "Error al momento de actualizar");
                }

                eventBus.post(event);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        //Log.i("Estudiante", estudiante.toString());

        UpdatePersona updatePersona = new UpdatePersona(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(updatePersona);

    }

    @Override
    public void effacerPersona(Estudiante estudiante) {
        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);

                if(jsonObject.getBoolean("result")){
                    event = new InformationEvent(InformationEvent.DELETE_SUCCESS, "Actualizaciones realizadas correctamente");
                }else{
                    event = new InformationEvent(InformationEvent.DELETE_ERROR, "Error al momento de actualizar");
                }
                eventBus.post(event);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        DeletePersona deletePersona = new DeletePersona(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(deletePersona);

    }

    @Override
    public void insererPersona(Estudiante estudiante) {

        Response.Listener<String> success = response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);



                if(jsonObject.getBoolean("result")){
                    event = new InformationEvent(InformationEvent.INSERT_SUCCESS, "Actualizaciones realizadas correctamente");
                }else{
                    event = new InformationEvent(InformationEvent.INSERT_ERROR, "Error al momento de actualizar");
                }
                eventBus.post(event);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        };

        InsertPersona insertPersona = new InsertPersona(success, estudiante);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(insertPersona);

    }

    @Override
    public void chercherOrganizadores() {
        Response.Listener<String> success = response -> {

            InformationEvent event;

            GsonBuilder gsonBuilder = new GsonBuilder();

            Gson gson = gsonBuilder.create();

            Type type = new TypeToken<ArrayList<String>>(){}.getType();

            ArrayList<String> organizadores = gson.fromJson(response, type);

            if(!organizadores.isEmpty()){
                event = new InformationEvent(InformationEvent.CHERCHER_SUCCESS, organizadores);
            }else{
                event = new InformationEvent(InformationEvent.CHERCHER_ERROR, "No se encontraron resultados");
            }


            eventBus.post(event);

        };

        ChercherOrganizadores chercherOrganizadores = new ChercherOrganizadores(success);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());
        request.add(chercherOrganizadores);
    }

}
