package me.dioxo.estudiante.Authentication;


import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.Request.LoginRequest;
import me.dioxo.estudiante.libs.ApplicationContextProvider;
import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;
import me.dioxo.estudiante.libs.Seguridad.Encriptar;
import me.dioxo.estudiante.libs.Seguridad.Encriptar_Interface;


public class Authentication_Repository_Impl implements Authentication_Repository {
    private Authentication_Event event;
    private EventBus eventBus;

    public Authentication_Repository_Impl() {
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void confirmerMDP(String email, final String mdp) {


        Response.Listener<String> responsePositive = response -> {
            try {
                Log.i("Login",response);

                JSONObject jsonObject = new JSONObject(response);
                String password = jsonObject.getString("password_estudiante");

                Encriptar_Interface encriptar = new Encriptar();

                if(encriptar.validatePassword(mdp , password)){

                    if(jsonObject.getString("fecha_estudiante").equals("")){
                        event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OTRO_OKAY);
                        storeUser_id(jsonObject.getString("idEstudiante"), false);

                    }else{
                        event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OKAY);
                        storeUser_id(jsonObject.getString("idEstudiante"), true);

                    }
                }else{
                    event = new  Authentication_Event(Authentication_Event.AUTHENTICATION_ERROR,
                            Authentication_Event.AUTHENTICATION_ERROR_MESSAGE );
                }
                eventBus.post(event);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        };

        Response.ErrorListener errorListener = error -> {
            event = new  Authentication_Event(Authentication_Event.AUTHENTICATION_ERROR,
                    Authentication_Event.AUTHENTICATION_ERROR_MESSAGE);
            eventBus.post(event);

    };

        LoginRequest loginRequest = new LoginRequest(email,responsePositive,errorListener);
        RequestQueue request = Volley.newRequestQueue(ApplicationContextProvider.getContext());

        request.add(loginRequest);
    }

    private void storeUser_id(String id_user, boolean sesionEstudiante) {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.ID_USER, id_user);

        if(sesionEstudiante){
            editor.putString(Constantes.TIPO_SESION, Constantes.SESION_ESTUDIANTE);
        }else {
            editor.putString(Constantes.TIPO_SESION, Constantes.SESION_OTRO);
        }

        // Commit the edits!
        editor.apply();
    }

}
