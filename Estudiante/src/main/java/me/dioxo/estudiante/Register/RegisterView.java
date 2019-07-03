package me.dioxo.estudiante.Register;

import me.dioxo.estudiante.Estudiante;

public interface RegisterView {
    void enableInputs();

    void disableInputs();

    void goToNextPage();

    void showProgressBar();
    void hideProgressBar();

    void registerError(String error);
    void afficherInformation(Estudiante estudiante);

    void afficherMessage(String message);
}
