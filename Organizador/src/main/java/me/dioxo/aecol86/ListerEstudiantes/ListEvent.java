package me.dioxo.aecol86.ListerEstudiantes;


import java.util.ArrayList;

import me.dioxo.aecol86.Estudiante;

class ListEvent {

    public static final int CHERCHER_SUCCESS = 0;
    public static final int CHERCHER_ERROR = 1;



    private int eventType;
    private String message;
    private ArrayList<Estudiante> estudiantes;

    public ListEvent(int eventType) {
        this.eventType = eventType;
    }

    public ListEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public ListEvent(int eventType, ArrayList<Estudiante> estudiantes) {
        this.eventType = eventType;
        this.estudiantes = estudiantes;
    }

    public int getEventType() {
        return this.eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}