package me.dioxo.estudiante.MyInfo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.estudiante.Authentication.Authentication;
import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.NavigationOtro.NavigationOtro;
import me.dioxo.estudiante.R;
import me.dioxo.estudiante.Register.RegisterPresenter;
import me.dioxo.estudiante.Register.RegisterPresenterImpl;
import me.dioxo.estudiante.Register.RegisterView;
import me.dioxo.estudiante.libs.ApplicationContextProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyInfoFragment extends Fragment implements RegisterView, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.organizadorEncargado)
    TextView organizadorEncargado;
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
    @BindView(R.id.ofii)
    CheckBox ofii;
    @BindView(R.id.btnActualizar)
    Button btnActualizar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Estudiante estudiante;
    private boolean existPersona = true;
    private RegisterPresenter presenter;

    private Calendar calendar;
    private int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    private OnFragmentInteractionListener mListener;

    public MyInfoFragment() {
        // Required empty public constructor
    }

    public boolean isRegister = true;

    public void setFragmentListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyInfoFragment newInstance(String param1, String param2) {
        MyInfoFragment fragment = new MyInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        ButterKnife.bind(this, view);

        presenter = new RegisterPresenterImpl(this);
        presenter.onCreate();
        if (isRegister) {
            afficherRegister();
        } else {
            afficherEstudiante();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isRegister) {
            afficherRegister();
        } else {
            afficherEstudiante();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void afficherRegister() {
        btnActualizar.setVisibility(View.GONE);
        organizadorEncargado.setVisibility(View.GONE);
    }

    public void afficherEstudiante() {
        buscarInformacionEstudiante();
        btnLogin.setVisibility(View.GONE);
        edTxtPassword.setVisibility(View.GONE);
    }

    @OnClick(R.id.edtFecha)
    public void onEdtFechaClicked() {
        //Instanciar calendario
        calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        estudiante = new Estudiante();
        confirmarCamposVacion();
        boolean rempli = confirmarCamposObligatorios();

        Log.i("Info", estudiante.toString());
        if (rempli) {
            presenter.registerUser(estudiante);
        }
    }

    private boolean confirmarCamposObligatorios() {

        //Si los campos obligatorios están llenos
        if (!TextUtils.isEmpty(edtNombre.getText().toString()) &&
                !TextUtils.isEmpty(edtEmail.getText().toString()) &&
                !TextUtils.isEmpty(edtTelefono.getText().toString()) &&
                edtTelefono.getText().toString().length() == 10 &&
                ((edTxtPassword.getVisibility() == View.VISIBLE && !TextUtils.isEmpty(edTxtPassword.getText().toString())) || edTxtPassword.getVisibility() == View.GONE) &
                !TextUtils.isEmpty(edtTelefonoEmergencia.getText().toString()) &&
                edtTelefonoEmergencia.getText().toString().length() == 10 &&
                !edtTelefono.getText().toString().equals(edtTelefonoEmergencia.getText().toString()) &&
                !TextUtils.isEmpty(edtResidencia.getText().toString()) &&
                !TextUtils.isEmpty(edtCarrera.getText().toString()) &&
                !TextUtils.isEmpty(edtFecha.getText().toString()) &&
                !TextUtils.isEmpty(edtTransporte.getText().toString()) &&
                ofii.isChecked()) {

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

        if (!TextUtils.isEmpty(edTxtPassword.getText().toString())) {
            estudiante.setPassword(edTxtPassword.getText().toString());
        } else {
            edTxtPassword.setError(getString(R.string.Ac_register_error_campo_vacio));
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

        if(edtTelefono.getText().toString().equals(edtTelefonoEmergencia.getText().toString())){
            edtTelefonoEmergencia.setError(getString(R.string.telefonos_iguales));
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

        if (!ofii.isChecked()) {
            ofii.setError("Este campo es obligatorio");
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
        startActivity(new Intent(getContext(), Authentication.class));
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
        this.estudiante = estudiante;
        Log.i("MyInfo", estudiante.toString());
        if(estudiante.getActivo_estudiante().equals("1")){
            Log.i("MyInfo", "mostrar estudiante");

            mListener.onFragmentInteractionListener(estudiante.getEmail_estudiante());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(organizadorEncargado.getText()).append(" ").append(estudiante.getNombre_organizador());

            organizadorEncargado.setText(stringBuilder);
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
        }else{
            Log.i("MyInfo", "irse al otro navigation");
            SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(Constantes.TIPO_SESION, Constantes.SESION_OTRO);
            editor.apply();

            Intent intent = new Intent(getContext(), NavigationOtro.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            intent.putExtra("email", estudiante.getEmail_estudiante());
            startActivity(intent);
        }

    }

    @Override
    public void afficherMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), this, hour, minute, true);

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

    public void buscarInformacionEstudiante() {
        presenter.chercherInformationEstudiante();
    }

    @OnClick(R.id.btnActualizar)
    public void onViewClicked() {
        confirmarCamposVacion();
        boolean rempli = confirmarCamposObligatorios();

        Log.i("Info", estudiante.toString());
        Log.i("Info", rempli + "");
        if (rempli) {
            presenter.actualizarDatos(estudiante);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionListener(String email);
    }
}
