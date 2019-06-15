package me.dioxo.aecol86.Information;


import me.dioxo.aecol86.Estudiante;

public interface InformationPresenter {
    void updatePersona(Estudiante estudiante);
    void effacerPersona(Estudiante estudiante);
    void insererPersona(Estudiante estudiante);

    void onCreate();
    void onDestroy();
    void onEventMainThread(InformationEvent event);

    void chercherOrganizadores();
}
