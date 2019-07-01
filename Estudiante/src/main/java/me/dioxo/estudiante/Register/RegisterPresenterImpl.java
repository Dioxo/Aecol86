package me.dioxo.estudiante.Register;

import org.greenrobot.eventbus.Subscribe;

import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.libs.EventBus;
import me.dioxo.estudiante.libs.GreenRobotEventBus;

class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView view;
    private RegisterRepository repository;
    private EventBus eventBus;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        repository = new RegisterRepositoryImpl();
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

    @Override
    @Subscribe
    public void onEventMainThread(RegisterEvent registerEvent) {
        if(view != null) {
            switch (registerEvent.getEventType()) {
                case RegisterEvent.REGISTER_SUCCESS:
                    view.goToNextPage();
                    break;

                case RegisterEvent.REGISTER_ERROR:
                    view.enableInputs();
                    view.hideProgressBar();
                    view.registerError(registerEvent.getMessage());
                    break;
            }
        }
    }

    @Override
    public void registerUser(Estudiante estudiante) {
        if(view != null){
            view.showProgressBar();
            view.disableInputs();
        }
        repository.registerUser(estudiante);
    }
}
