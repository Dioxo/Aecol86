package me.dioxo.estudiante.RegisterOtros;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.estudiante.Authentication.Authentication;
import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.R;
import me.dioxo.estudiante.Register.RegisterPresenter;
import me.dioxo.estudiante.Register.RegisterPresenterImpl;
import me.dioxo.estudiante.Register.RegisterView;

public class RegisterOtros extends AppCompatActivity implements RegisterView {

    @BindView(R.id.edtNombre)
    EditText edtNombre;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_otros);
        ButterKnife.bind(this);

        presenter = new RegisterPresenterImpl(this);
        presenter.onCreate();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        if(confirmarCampos()){
            Estudiante estudiante = remplirEstudiante();
            presenter.registerOtro(estudiante);
        } else {

            if(TextUtils.isEmpty(edTxtPassword.getText().toString())){
                edTxtPassword.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
            }

            if(TextUtils.isEmpty(edtNombre.getText().toString())){
                edtNombre.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
            }

            if(TextUtils.isEmpty(edtEmail.getText().toString())){
                edtEmail.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
            }

        }



    }

    private boolean confirmarCampos() {
        return !TextUtils.isEmpty(edTxtPassword.getText().toString()) &&
                !TextUtils.isEmpty(edtNombre.getText().toString()) &&
                !TextUtils.isEmpty(edtEmail.getText().toString());
    }

    private Estudiante remplirEstudiante() {
        Estudiante estudiante = new Estudiante();

        estudiante.setNombre_estudiante(edtNombre.getText().toString());
        estudiante.setEmail_estudiante(edtEmail.getText().toString());
        estudiante.setPassword(edTxtPassword.getText().toString());

        estudiante.setTelefono_estudiante("");
        estudiante.setTelefono_emergencia("");
        estudiante.setResidencia_estudiante("");
        estudiante.setCarrera_estudiante("");
        estudiante.setFecha_estudiante("");
        estudiante.setTransporte_estudiante("");
        estudiante.setInfo_estudiante("");
        estudiante.setHotel_estudiante("");




        return estudiante;
    }

    @Override
    public void enableInputs() {
        edtEmail.setEnabled(true);
        edtNombre.setEnabled(true);
        edTxtPassword.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        edtEmail.setEnabled(false);
        edtNombre.setEnabled(false);
        edTxtPassword.setEnabled(false);
    }

    @Override
    public void goToNextPage() {
        startActivity(new Intent(this, Authentication.class));
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void registerError(String error) {
        afficherMessage(error);
    }

    @Override
    public void afficherInformation(Estudiante estudiante) {

    }

    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
