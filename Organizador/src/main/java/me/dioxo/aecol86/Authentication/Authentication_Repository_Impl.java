package me.dioxo.aecol86.Authentication;



import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Request.LoginRequest;
import me.dioxo.aecol86.libs.ApplicationContextProvider;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;
import me.dioxo.aecol86.libs.Seguridad.Encriptar;
import me.dioxo.aecol86.libs.Seguridad.Encriptar_Interface;


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
                String password = jsonObject.getString("password_organizador");

                Encriptar_Interface encriptar = new Encriptar();

                if(encriptar.validatePassword(mdp , password)){
                    storeUser_id(jsonObject.getString("idOrganizador"));
                    event = new Authentication_Event(Authentication_Event.AUTHENTICATION_OKAY);
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

    private void storeUser_id(String id_user) {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.ID_USER, id_user);

        // Commit the edits!
        editor.apply();
    }

}
