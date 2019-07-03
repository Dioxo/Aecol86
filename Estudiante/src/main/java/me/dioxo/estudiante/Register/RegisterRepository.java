package me.dioxo.estudiante.Register;


import me.dioxo.estudiante.Estudiante;

public interface RegisterRepository {
    void registerUser(Estudiante estudiante);

    void chercherInformationEstudiante();

    void actualizarDatos(Estudiante estudiante);
}
