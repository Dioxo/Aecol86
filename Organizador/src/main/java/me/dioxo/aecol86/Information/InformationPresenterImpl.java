package me.dioxo.aecol86.Information;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;

class InformationPresenterImpl implements InformationPresenter {
    private InformationRepository repository;
    private InformationView view;
    private EventBus eventBus;

    public InformationPresenterImpl(InformationView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        repository = new InformationRepositoryImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }


    @Subscribe
    @Override
    public void onEventMainThread(InformationEvent event) {
        switch (event.getEventType()){

            case InformationEvent.UPDATE_SUCCESS:
                    afficherMessage(event.getMessage());
                break;

            case InformationEvent.UPDATE_ERROR:
                    afficherMessage(event.getMessage());
                break;


            case InformationEvent.INSERT_SUCCESS:
                afficherMessage(event.getMessage());
                changerMenu();
                break;

            case InformationEvent.INSERT_ERROR:
                afficherMessage(event.getMessage());
                break;

            case InformationEvent.DELETE_SUCCESS:
                afficherMessage(event.getMessage());
                break;

            case InformationEvent.DELETE_ERROR:
                afficherMessage(event.getMessage());
                break;

            case InformationEvent.CHERCHER_SUCCESS:
                afficherOrganizadores(event.getOrganizadores());
                break;

            case InformationEvent.CHERCHER_ERROR:
                afficherMessage(event.getMessage());
                break;
        }
    }

    private void changerMenu() {
        if(view != null){
            view.changerMenu();
        }
    }

    private void afficherOrganizadores(ArrayList<String> organizadores) {
        if(view != null){
            view.afficherOrganizadores(organizadores);
        }
    }

    @Override
    public void chercherOrganizadores() {
        repository.chercherOrganizadores();
    }

    private void afficherMessage(String message) {
        if(view != null){
            view.afficherMessage(message);
        }
    }

    @Override
    public void updatePersona(Estudiante estudiante) {
        repository.updatePersona(estudiante);
    }

    @Override
    public void effacerPersona(Estudiante estudiante) {
        repository.effacerPersona(estudiante);
    }

    @Override
    public void insererPersona(Estudiante estudiante) {
        repository.insererPersona(estudiante);
    }
}
