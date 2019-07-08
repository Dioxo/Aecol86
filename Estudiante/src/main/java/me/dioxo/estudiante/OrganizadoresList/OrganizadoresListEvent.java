package me.dioxo.estudiante.OrganizadoresList;

import java.util.ArrayList;

import me.dioxo.estudiante.Organizador;

public class OrganizadoresListEvent {

    public static final int CHERCHER_SUCCESS = 0;
    public static final int CHERCHER_ERROR = 1;

    private int eventType;
    private String message;
    private ArrayList<Organizador> organizadores;

    public OrganizadoresListEvent(int eventType, ArrayList<Organizador> organizadores) {
        this.eventType = eventType;
        this.organizadores = organizadores;
    }

    public OrganizadoresListEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public ArrayList<Organizador> getOrganizadores() {
        return organizadores;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
