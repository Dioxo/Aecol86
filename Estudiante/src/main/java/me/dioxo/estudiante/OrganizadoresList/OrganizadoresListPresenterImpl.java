package me.dioxo.estudiante.OrganizadoresList;

import org.greenrobot.eventbus.Subscribe;

import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;

class OrganizadoresListPresenterImpl implements OrganizadoresListPresenter {
    private OrganizadoresListView view;
    private EventBus eventBus;
    private OrganizadoresListRepository repository;

    public OrganizadoresListPresenterImpl(OrganizadoresListView view) {
        this.view = view;
        repository = new OrganizadoresListRepositoryImpl();
        eventBus = GreenRobotEventBus.getInstance();
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
    public void onEventMainThread(OrganizadoresListEvent event) {
        if(view != null){
            switch(event.getEventType()){
                case OrganizadoresListEvent.CHERCHER_SUCCESS:
                    view.afficherOrganizadores(event.getOrganizadores());
                    break;

                case OrganizadoresListEvent.CHERCHER_ERROR:
                    view.showMessage(event.getMessage());
                    break;
            }
        }
    }

    @Override
    public void chercherOrganizadores() {
        repository.ChercherOrganizadores();
    }
}
