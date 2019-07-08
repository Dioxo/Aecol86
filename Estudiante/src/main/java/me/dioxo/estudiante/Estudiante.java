package me.dioxo.estudiante;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import me.dioxo.estudiante.libs.Seguridad.Encriptar;

public class Estudiante implements Serializable {

    @SerializedName("idEstudiante")
    @Expose
    private String idEstudiante;

    @SerializedName("nombre_estudiante")
    @Expose
    private String nombre_estudiante;

    @SerializedName("email_estudiante")
    @Expose
    private String email_estudiante;


    @SerializedName("telefono_estudiante")
    @Expose
    private String telefono_estudiante;

    @SerializedName("telefono_emergencia")
    @Expose
    private String telefono_emergencia;


    @SerializedName("residencia_estudiante")
    @Expose
    private String residencia_estudiante;

    @SerializedName("carrera_estudiante")
    @Expose
    private String carrera_estudiante;

    @SerializedName("fecha_estudiante")
    @Expose
    private String fecha_estudiante;

    @SerializedName("transporte_estudiante")
    @Expose
    private String transporte_estudiante;


    @SerializedName("idOrganizador")
    @Expose
    private String idOrganizador;

    @SerializedName("nombre_Organizador")
    @Expose
    private String nombre_organizador;

    @SerializedName("hotel_estudiante")
    @Expose
    private String hotel_estudiante;

    @SerializedName("info_estudiante")
    @Expose
    private String info_estudiante;
    @SerializedName("activo_estudiante")
    @Expose
    private String activo_estudiante;
    private String password;




    public Estudiante() {
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre_estudiante() {
        return nombre_estudiante;
    }

    public void setNombre_estudiante(String nombre_estudiante) {
        this.nombre_estudiante = nombre_estudiante;
    }

    public String getTelefono_estudiante() {
        return telefono_estudiante;
    }

    public void setTelefono_estudiante(String telefono_estudiante) {
        this.telefono_estudiante = telefono_estudiante;
    }

    public String getFecha_estudiante() {
        return fecha_estudiante;
    }

    public void setFecha_estudiante(String fecha_estudiante) {
        this.fecha_estudiante = fecha_estudiante;
    }

    public String getTransporte_estudiante() {
        return transporte_estudiante;
    }

    public void setTransporte_estudiante(String transporte_estudiante) {
        this.transporte_estudiante = transporte_estudiante;
    }

    public String getResidencia_estudiante() {
        return residencia_estudiante;
    }

    public void setResidencia_estudiante(String residencia_estudiante) {
        this.residencia_estudiante = residencia_estudiante;
    }

    public String getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(String idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public String getNombre_organizador() {
        return nombre_organizador;
    }

    public void setNombre_organizador(String nombre_organizador) {
        this.nombre_organizador = nombre_organizador;
    }

    public String getEmail_estudiante() {
        return email_estudiante;
    }

    public void setEmail_estudiante(String email_estudiante) {
        this.email_estudiante = email_estudiante;
    }

    public String getTelefono_emergencia() {
        return telefono_emergencia;
    }

    public void setTelefono_emergencia(String telefono_emergencia) {
        this.telefono_emergencia = telefono_emergencia;
    }

    public String getCarrera_estudiante() {
        return carrera_estudiante;
    }

    public void setCarrera_estudiante(String carrera_estudiante) {
        this.carrera_estudiante = carrera_estudiante;
    }

    public String getHotel_estudiante() {
        return hotel_estudiante;
    }

    public void setHotel_estudiante(String hotel_estudiante) {
        this.hotel_estudiante = hotel_estudiante;
    }

    public String getInfo_estudiante() {
        return info_estudiante;
    }

    public void setInfo_estudiante(String info_estudiante) {
        this.info_estudiante = info_estudiante;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Encriptar encriptar = new Encriptar();
        try {
            this.password = encriptar.generateStorngPasswordHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public String getActivo_estudiante() {
        return activo_estudiante;
    }

    public void setActivo_estudiante(String activo_estudiante) {
        this.activo_estudiante = activo_estudiante;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idEstudiante='" + idEstudiante + '\'' +
                ", nombre_estudiante='" + nombre_estudiante + '\'' +
                ", email_estudiante='" + email_estudiante + '\'' +
                ", telefono_estudiante='" + telefono_estudiante + '\'' +
                ", telefono_emergencia='" + telefono_emergencia + '\'' +
                ", residencia_estudiante='" + residencia_estudiante + '\'' +
                ", carrera_estudiante='" + carrera_estudiante + '\'' +
                ", fecha_estudiante='" + fecha_estudiante + '\'' +
                ", transporte_estudiante='" + transporte_estudiante + '\'' +
                ", idOrganizador='" + idOrganizador + '\'' +
                ", nombre_organizador='" + nombre_organizador + '\'' +
                ", hotel_estudiante='" + hotel_estudiante + '\'' +
                ", info_estudiante='" + info_estudiante + '\'' +
                '}';
    }
}