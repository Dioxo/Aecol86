package me.dioxo.aecol86.Information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.aecol86.Estudiante;
import me.dioxo.aecol86.R;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InformationActivity extends AppCompatActivity implements InformationView {

    @BindView(R.id.edtNombre)
    EditText edtNombre;
    @BindView(R.id.edtTelefono)
    EditText edtTelefono;
    @BindView(R.id.edtFecha)
    EditText edtFecha;
    @BindView(R.id.edtTransporte)
    EditText edtTransporte;
    @BindView(R.id.edtResidencia)
    EditText edtResidencia;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.spinnerOrganizador)
    Spinner spinnerOrganizador;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtTelefono_Emergencia)
    EditText edtTelefonoEmergencia;
    @BindView(R.id.edtCarrera)
    EditText edtCarrera;
    @BindView(R.id.edtHotel)
    EditText edtHotel;
    @BindView(R.id.edtInfo_Adicional)
    EditText edtInfoAdicional;

    private Estudiante estudiante;
    private boolean existPersona = true;
    private InformationPresenter presenter;

    private ArrayAdapter<String> arrayAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estudiante, menu);

        if (existPersona) {
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(false);
        } else {
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(true);
        }


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_insert:
                insererPersona();
                break;
            case R.id.menu_delete:
                effacerPersona();
                break;
            case R.id.menu_update:
                updatePersona();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        if (getIntent().getExtras() != null) {
            estudiante = (Estudiante) getIntent().getExtras().getSerializable("Estudiante");
        }

        if (estudiante != null) {
            afficherInformation(estudiante);
            existPersona = true;
        } else {
            existPersona = false;
        }


        presenter = new InformationPresenterImpl(this);
        presenter.onCreate();

        chercherOrganizadores();

    }

    private void chercherOrganizadores() {
        presenter.chercherOrganizadores();
    }


    @Override
    public void afficherOrganizadores(ArrayList<String> organizadores) {

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                organizadores
        );

        Log.i("Organizador",organizadores.toString());


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        /*if(!estudiante.getNombre_organizador().equals("")){
            organizadores.add(0, "El encargado es : " + estudiante.getNombre_organizador());
        }*/

        if(!existPersona || (existPersona && estudiante.getNombre_organizador().equals(""))){
            organizadores.add(0, "Agregue un encargado");
            spinnerOrganizador.setAdapter(arrayAdapter);
            spinnerOrganizador.setSelection(Adapter.NO_SELECTION, false);
        }else{
            spinnerOrganizador.setAdapter(arrayAdapter);
            spinnerOrganizador.setSelection(arrayAdapter.getPosition(estudiante.getNombre_organizador()));
        }





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
        edtFecha.setText(estudiante.getFecha_estudiante());
        edtTransporte.setText(estudiante.getTransporte_estudiante());
        edtHotel.setText(estudiante.getHotel_estudiante());
        edtInfoAdicional.setText(estudiante.getInfo_estudiante());
    }

    @Override
    public void updatePersona() {
        if (existPersona) {

            estudiante.setNombre_estudiante(edtNombre.getText().toString());
            estudiante.setEmail_estudiante(edtEmail.getText().toString());
            estudiante.setTelefono_estudiante(edtTelefono.getText().toString());
            estudiante.setTelefono_emergencia(edtTelefonoEmergencia.getText().toString());
            estudiante.setResidencia_estudiante(edtResidencia.getText().toString());
            estudiante.setCarrera_estudiante(edtCarrera.getText().toString());
            estudiante.setFecha_estudiante(edtFecha.getText().toString());
            estudiante.setTransporte_estudiante(edtTransporte.getText().toString());
            estudiante.setHotel_estudiante(edtHotel.getText().toString());
            estudiante.setInfo_estudiante(edtInfoAdicional.getText().toString());

            if(!spinnerOrganizador.getSelectedItem().equals("Agregue un encargado")){
                estudiante.setNombre_organizador(spinnerOrganizador.getSelectedItem().toString());
            }


            presenter.updatePersona(estudiante);
        }
    }

    @Override
    public void effacerPersona() {
        if (existPersona) {
            presenter.effacerPersona(estudiante);
        }
    }

    @Override
    public void insererPersona() {
        if (!existPersona) {
            estudiante = new Estudiante();

            estudiante.setNombre_estudiante(edtNombre.getText().toString());
            estudiante.setEmail_estudiante(edtEmail.getText().toString());
            estudiante.setTelefono_estudiante(edtTelefono.getText().toString());
            estudiante.setTelefono_emergencia(edtTelefonoEmergencia.getText().toString());
            estudiante.setResidencia_estudiante(edtResidencia.getText().toString());
            estudiante.setCarrera_estudiante(edtCarrera.getText().toString());
            estudiante.setFecha_estudiante(edtFecha.getText().toString());
            estudiante.setTransporte_estudiante(edtTransporte.getText().toString());
            estudiante.setHotel_estudiante(edtHotel.getText().toString());
            estudiante.setInfo_estudiante(edtInfoAdicional.getText().toString());

            if(!spinnerOrganizador.getSelectedItem().equals("Agregue un encargado")){
                estudiante.setNombre_organizador(spinnerOrganizador.getSelectedItem().toString());
            }

            presenter.insererPersona(estudiante);

        }
    }

    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
