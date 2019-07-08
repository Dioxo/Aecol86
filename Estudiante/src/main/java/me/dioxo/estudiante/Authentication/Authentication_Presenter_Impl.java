package me.dioxo.estudiante.Authentication;


import android.content.SharedPreferences;

import org.greenrobot.eventbus.Subscribe;

import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.libs.ApplicationContextProvider;
import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;


public class Authentication_Presenter_Impl implements Authentication_Presenter {
    private Authentication_View view;
    private EventBus eventBus;
    private Authentication_Repository repository;

    public Authentication_Presenter_Impl(Authentication_View view) {
        this.view = view;
        repository = new Authentication_Repository_Impl();
        eventBus = GreenRobotEventBus.getInstance();
    }

    public void confirmerMDP(String email, String mdp) {
        if(view != null){
            view.showProgressBar();
            view.disableInputs();
        }

        repository.confirmerMDP(email,mdp);
    }

    public void goToNextPage() {
        view.goToNextPage();
    }

    public void onCreate() {
        eventBus.register(this);
    }

    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(Authentication_Event authenticationEvent) {
        switch (authenticationEvent.getEventType()){

            case Authentication_Event.AUTHENTICATION_OKAY:
                if(view != null) {
                    view.enableInputs();
                    view.hideProgressBar();
                    view.goToNextPage();
                }
                break;

            case Authentication_Event.AUTHENTICATION_ERROR:
                if(view != null){
                    view.loginError(authenticationEvent.getMessage());
                    view.enableInputs();
                    view.hideProgressBar();
                }
                break;

            case Authentication_Event.AUTHENTICATION_OTRO_OKAY:
                if(view != null){
                    view.goToOtroNextPage();
                }
                break;
        }
    }

    @Override
    public void checkAlreadyConnected() {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        String id_user = settings.getString(Constantes.ID_USER,null);
        String tipoSesion = settings.getString(Constantes.TIPO_SESION,null);

        if(id_user  != null) {

            switch (tipoSesion){
                case Constantes.SESION_ESTUDIANTE:
                    if (view != null) {
                        view.enableInputs();
                        view.hideProgressBar();
                        view.goToNextPage();
                    }

                    break;

                case Constantes.SESION_OTRO:
                    if (view != null) {
                        view.enableInputs();
                        view.hideProgressBar();
                        view.goToOtroNextPage();
                    }
                    break;

            }

        }

    }

}
