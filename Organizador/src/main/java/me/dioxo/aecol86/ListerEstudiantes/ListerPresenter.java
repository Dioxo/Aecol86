package me.dioxo.aecol86.ListerEstudiantes;


import me.dioxo.aecol86.Estudiante;

public interface ListerPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(ListEvent event);

    void chercherPersonas();
}
