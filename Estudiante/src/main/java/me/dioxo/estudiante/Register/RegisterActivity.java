package me.dioxo.estudiante.Register;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.estudiante.Authentication.Authentication;
import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.R;

public class RegisterActivity extends AppCompatActivity implements RegisterView, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    @BindView(R.id.edtNombre)
    EditText edtNombre;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.edtTelefono)
    EditText edtTelefono;
    @BindView(R.id.edtTelefono_Emergencia)
    EditText edtTelefonoEmergencia;
    @BindView(R.id.edtResidencia)
    EditText edtResidencia;
    @BindView(R.id.edtCarrera)
    EditText edtCarrera;
    @BindView(R.id.edtTransporte)
    EditText edtTransporte;
    @BindView(R.id.edtFecha)
    Button edtFecha;
    @BindView(R.id.edtHotel)
    TextInputEditText edtHotel;
    @BindView(R.id.edtInfo_Adicional)
    TextInputEditText edtInfoAdicional;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private Estudiante estudiante;
    private boolean existPersona = true;
    private RegisterPresenter presenter;

    private Calendar calendar;
    private int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


        if (getIntent().getExtras() != null) {
            estudiante = (Estudiante) getIntent().getExtras().getSerializable("Estudiante");
        }

        if (estudiante != null) {
            afficherInformation(estudiante);
            existPersona = true;
        } else {
            existPersona = false;
        }


        presenter = new RegisterPresenterImpl(this);
        presenter.onCreate();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void afficherInformation(Estudiante estudiante) {
        edtNombre.setText(estudiante.getNombre_estudiante());
        edtEmail.setText(estudiante.getEmail_estudiante());
        edtTelefono.setText(estudiante.getTelefono_estudiante());
        edtTelefonoEmergencia.setText(estudiante.getTelefono_emergencia());
        edtResidencia.setText(estudiante.getResidencia_estudiante());
        edtCarrera.setText(estudiante.getCarrera_estudiante());
        edtFecha.setText(estudiante.getFecha_estudiante().substring(0, estudiante.getFecha_estudiante().length() - 3));
        edtTransporte.setText(estudiante.getTransporte_estudiante());
        edtHotel.setText(estudiante.getHotel_estudiante());
        edtInfoAdicional.setText(estudiante.getInfo_estudiante());
    }



    private boolean confirmarCamposObligatorios() {

        //Si los campos obligatorios están llenos
        if (!TextUtils.isEmpty(edtNombre.getText().toString()) &&
                !TextUtils.isEmpty(edtEmail.getText().toString()) &&
                !TextUtils.isEmpty(edTxtPassword.getText().toString()) &&
                !TextUtils.isEmpty(edtTelefono.getText().toString()) &&
                edtTelefono.getText().toString().length() == 10 &&
                !TextUtils.isEmpty(edtTelefonoEmergencia.getText().toString()) &&
                edtTelefonoEmergencia.getText().toString().length() == 10 &&
                !TextUtils.isEmpty(edtResidencia.getText().toString()) &&
                !TextUtils.isEmpty(edtCarrera.getText().toString()) &&
                !TextUtils.isEmpty(edtFecha.getText().toString()) &&
                !TextUtils.isEmpty(edtTransporte.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }

    private void confirmarCamposVacion() {

        //CONFIRMAR QUE LOS CAMPOS NO ESTÁN VACÍOS

        if (!TextUtils.isEmpty(edtNombre.getText().toString())) {
            estudiante.setNombre_estudiante(edtNombre.getText().toString());
        } else {
            edtNombre.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        if (!TextUtils.isEmpty(edtEmail.getText().toString())) {
            estudiante.setEmail_estudiante(edtEmail.getText().toString());
        } else {
            edtEmail.setError(getString(R.string.Ac_register_error_campo_vacio));
        }

        if (!TextUtils.isEmpty(edtTelefono.getText().toString())) {

            //La talla del telefono debe ser 10 caracteres
            if (edtTelefono.getText().toString().length() == 10) {
                estudiante.setTelefono_estudiante(edtTelefono.getText().toString());
            } else {
                edtTelefono.setError(getString(R.string.Ac_register_error_telefono_incompleto));
            }


        } else {
            edtTelefono.setError(getString(R.string.Ac_register_error_campo_vacio));
        }

        if (!TextUtils.isEmpty(edtTelefonoEmergencia.getText().toString())) {

            //La talla del telefono debe ser 10 caracteres
            if (edtTelefonoEmergencia.getText().toString().length() == 10) {
                estudiante.setTelefono_emergencia(edtTelefonoEmergencia.getText().toString());
            } else {
                edtTelefonoEmergencia.setError(getString(R.string.Ac_register_error_telefono_incompleto));
            }


        } else {
            edtTelefonoEmergencia.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        if (!TextUtils.isEmpty(edtResidencia.getText().toString())) {
            estudiante.setResidencia_estudiante(edtResidencia.getText().toString());
        } else {
            edtResidencia.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        if (!TextUtils.isEmpty(edtCarrera.getText().toString())) {
            estudiante.setCarrera_estudiante(edtCarrera.getText().toString());
        } else {
            edtCarrera.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        if (!TextUtils.isEmpty(edtFecha.getText().toString()) &&
                !edtFecha.getText().toString().equals(getResources().getString(R.string.Ac_information_txt_fecha))) {
            estudiante.setFecha_estudiante(edtFecha.getText().toString().concat(":00"));
        } else {
            edtFecha.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        if (!TextUtils.isEmpty(edtTransporte.getText().toString())) {
            estudiante.setTransporte_estudiante(edtTransporte.getText().toString());
        } else {
            edtTransporte.setError(getString(R.string.Ac_register_error_campo_vacio));
        }


        //Confirmar si el campo de hotel está vacío o no, para no enviar NULL en las Requets
        //Parametro de la Request InsertPersona no pueden ser NULL
        if (TextUtils.isEmpty(edtHotel.getText().toString())) {
            estudiante.setHotel_estudiante("");
        } else {
            estudiante.setHotel_estudiante(edtHotel.getText().toString());
        }

        //Confirmar si el campo de hotel está vacío o no, para no enviar NULL en las Requets
        //Parametro de la Request InsertPersona no pueden ser NULL
        if (TextUtils.isEmpty(edtInfoAdicional.getText().toString())) {
            estudiante.setInfo_estudiante("");
        } else {
            estudiante.setInfo_estudiante(edtInfoAdicional.getText().toString());
        }

    }

    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, hour, minute, true);

        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;


        //Formato AAAA-MM-DD HH:MM:SS
        StringBuilder fecha = formatoFecha();

        edtFecha.setText(fecha);
    }

    private StringBuilder formatoFecha() {
        //Formato AAAA-MM-DD HH:MM:SS

        StringBuilder fecha = new StringBuilder();

        //first concat year
        fecha.append(yearFinal).append("-");

        if (monthFinal >= 10) {
            fecha.append(monthFinal).append("-");
        } else {
            fecha.append(0).append(monthFinal).append("-");
        }

        if (dayFinal >= 10) {
            fecha.append(dayFinal).append(" ");
        } else {
            fecha.append(0).append(dayFinal).append(" ");
        }

        if (hourFinal >= 10) {
            fecha.append(hourFinal).append(":");
        } else {
            fecha.append(0).append(hourFinal).append(":");
        }

        if (minuteFinal >= 10) {
            fecha.append(minuteFinal);
        } else {
            fecha.append(0).append(minuteFinal);
        }

        return fecha;
    }

    @OnClick(R.id.edtFecha)
    public void onViewClicked() {

        //Instanciar calendario
        calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        if (!existPersona) {
            estudiante = new Estudiante();

            confirmarCamposVacion();
            boolean rempli = confirmarCamposObligatorios();

            Log.i("Info", estudiante.toString());
            if (rempli) {
                presenter.registerUser(estudiante);
            }


        }
    }

    @Override
    public void enableInputs() {
        edtFecha.setEnabled(true);
        edTxtPassword.setEnabled(true);
        edtEmail.setEnabled(true);
        edtTransporte.setEnabled(true);
        edtTelefonoEmergencia.setEnabled(true);
        edtTelefono.setEnabled(true);
        edtResidencia.setEnabled(true);
        edtNombre.setEnabled(true);
        edtInfoAdicional.setEnabled(true);
        edtHotel.setEnabled(true);
        edtCarrera.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        edtFecha.setEnabled(false);
        edTxtPassword.setEnabled(false);
        edtEmail.setEnabled(false);
        edtTransporte.setEnabled(false);
        edtTelefonoEmergencia.setEnabled(false);
        edtTelefono.setEnabled(false);
        edtResidencia.setEnabled(false);
        edtNombre.setEnabled(false);
        edtInfoAdicional.setEnabled(false);
        edtHotel.setEnabled(false);
        edtCarrera.setEnabled(false);
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


}


