package me.dioxo.estudiante.Register;

import me.dioxo.estudiante.Estudiante;

class RegisterEvent {

    public static final int REGISTER_SUCCESS = 0;
    public static final int REGISTER_ERROR = 1;
    public static final int CHERCHER_SUCCESS = 2;
    public static final int CHERCHER_ERROR = 3;


    private int eventType;
    private String message;
    private Estudiante estudiante;

    public RegisterEvent(int eventType) {
        this.eventType = eventType;
    }

    public RegisterEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public RegisterEvent(int chercherSuccess, Estudiante estudiante) {
        this.eventType = chercherSuccess;
        this.estudiante = estudiante;
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

    public Estudiante getEstudiante() {
        return estudiante;
    }
}
