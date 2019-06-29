package me.dioxo.aecol86.ListerEstudiantes;

import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;

public interface ListerView {
    void chercherPersonas();
    void afficherPersonas(ArrayList<Estudiante> estudiantes);
    void agregarPersona();
    void listerInformation(Estudiante estudiante);

    void afficherError(String message);
}
