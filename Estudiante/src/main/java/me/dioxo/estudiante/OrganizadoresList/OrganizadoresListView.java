package me.dioxo.estudiante.OrganizadoresList;

import java.util.ArrayList;

import me.dioxo.estudiante.Organizador;

public interface OrganizadoresListView {
    void afficherOrganizadores(ArrayList<Organizador> organizadores);

    void showMessage(String message);
}
