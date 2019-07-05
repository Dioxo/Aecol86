package me.dioxo.estudiante.OrganizadoresList;

public interface OrganizadoresListPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(OrganizadoresListEvent event);
    void chercherOrganizadores();
}
