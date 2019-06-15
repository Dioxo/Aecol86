package me.dioxo.aecol86.Information;

import java.util.ArrayList;

class InformationEvent {

    public static final int UPDATE_SUCCESS = 0;
    public static final int UPDATE_ERROR = 1;
    public static final int INSERT_SUCCESS = 2;
    public static final int INSERT_ERROR = 3;
    public static final int DELETE_SUCCESS = 4;
    public static final int DELETE_ERROR = 5;
    public static final int CHERCHER_SUCCESS = 6;
    public static final int CHERCHER_ERROR = 7;

    private int eventType;
    private String message;
    private ArrayList<String> organizadores;

    public InformationEvent(int eventType, ArrayList<String> organizadores) {
        this.eventType = eventType;
        this.organizadores = organizadores;
    }

    public InformationEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
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

    public ArrayList<String> getOrganizadores() {
        return organizadores;
    }

    public void setOrganizadores(ArrayList<String> organizadores) {
        this.organizadores = organizadores;
    }
}