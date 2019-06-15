package me.dioxo.aecol86.Information;


import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;

public interface InformationView {
    void afficherInformation(Estudiante estudiante);
    void updatePersona();
    void effacerPersona();
    void insererPersona();

    void afficherMessage(String message);
    void changerMenu();

    void afficherOrganizadores(ArrayList<String> organizadores);

    void cerrarActivity();

}
