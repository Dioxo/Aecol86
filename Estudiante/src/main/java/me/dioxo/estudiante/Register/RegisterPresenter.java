package me.dioxo.estudiante.Register;



import me.dioxo.estudiante.Estudiante;

public interface RegisterPresenter {
    void onCreate();

    void onDestroy();

    void onEventMainThread(RegisterEvent registerEvent);

    void registerUser(Estudiante estudiante);

    void chercherInformationEstudiante();
}
