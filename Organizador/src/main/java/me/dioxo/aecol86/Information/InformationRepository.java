package me.dioxo.aecol86.Information;


import me.dioxo.aecol86.Estudiante;

public interface InformationRepository {
    void updatePersona(Estudiante estudiante);
    void effacerPersona(Estudiante estudiante);
    void insererPersona(Estudiante estudiante);

    void chercherOrganizadores();
}
