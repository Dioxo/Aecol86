package me.dioxo.aecol86;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import me.dioxo.aecol86.libs.Seguridad.Encriptar;
import me.dioxo.aecol86.libs.Seguridad.Encriptar_Interface;

public class Organizador {
    @SerializedName("email_organizador")
    @Expose
    private String email;
    @SerializedName("nombre_organizador")
    @Expose
    private String nombre;
    private String password;
    private String telefono;

    public Organizador(String email, String nombre, String password, String telefono) {
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;

        Encriptar_Interface encriptar = new Encriptar();
        try {
            this.password = encriptar.generateStorngPasswordHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public Organizador(String email, String nombre) {
        super();
        this.email = email;
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }
}
