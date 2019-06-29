package me.dioxo.aecol86.Register;



import me.dioxo.aecol86.Organizador;

public interface RegisterPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(RegisterEvent registerEvent);

    void registerUser(Organizador organizador);

}
