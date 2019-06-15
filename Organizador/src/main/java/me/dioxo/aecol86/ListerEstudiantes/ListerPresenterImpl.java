package me.dioxo.aecol86.ListerEstudiantes;


import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.libs.EventBus;
import me.dioxo.aecol86.libs.GreenRobotEventBus;

class ListerPresenterImpl implements ListerPresenter {
    EventBus eventBus;
    ListerView listerView;
    ListerRepository listerRepository;


    public ListerPresenterImpl(ListerView listerView) {
        this.listerView = listerView;
        eventBus = GreenRobotEventBus.getInstance();
        listerRepository = new ListerRepositoryImpl();
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
    public void onEventMainThread(ListEvent event) {
        switch (event.getEventType()){

            case ListEvent.CHERCHER_SUCCESS :
                    afficherPersonas(event.getEstudiantes());
                break;

            case ListEvent.CHERCHER_ERROR :
                    afficherError(event.getMessage());
                break;



        }
    }

    private void afficherError(String message) {
        listerView.afficherError(message);
    }

    private void afficherPersonas(ArrayList<Estudiante> estudiantes) {
        if(listerView != null){
            listerView.afficherPersonas(estudiantes);
        }
    }

    @Override
    public void chercherPersonas() {
        listerRepository.chercherPersonas();
    }

}
