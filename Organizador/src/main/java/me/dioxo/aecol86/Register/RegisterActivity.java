package me.dioxo.aecol86.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.aecol86.Authentication.Authentication;
import me.dioxo.aecol86.Organizador;
import me.dioxo.aecol86.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    @BindView(R.id.edTxtUser)
    EditText edTxtUser;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.coordinator)
    ConstraintLayout coordinator;
    @BindView(R.id.edtTelefono)
    EditText edtTelefono;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = new RegisterPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void enableInputs() {
        edtEmail.setEnabled(true);
        edTxtPassword.setEnabled(true);
        edTxtUser.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        edtEmail.setEnabled(false);
        edTxtPassword.setEnabled(false);
        edTxtUser.setEnabled(false);
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
        Toast.makeText(this,"Error al registrar usuario",Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        //Si aucun EditText est vide
        if (TextUtils.isEmpty(edtEmail.getText())) {
            edtEmail.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
        }

        if (TextUtils.isEmpty(edTxtPassword.getText())) {
            edTxtPassword.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
        }

        if (TextUtils.isEmpty(edTxtUser.getText())) {
            edTxtUser.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
        }

        if (TextUtils.isEmpty(edtTelefono.getText())) {
            edTxtUser.setError(getResources().getString(R.string.Ac_register_error_campo_vacio));
        }

        if (!TextUtils.isEmpty(edtEmail.getText()) ||
                !TextUtils.isEmpty(edTxtUser.getText()) ||
                !TextUtils.isEmpty(edtTelefono.getText()) ||
                !TextUtils.isEmpty(edTxtPassword.getText())) {
            Log.i("Register", "enviar info");
            presenter.registerUser(new Organizador(edtEmail.getText().toString(),
                    edTxtUser.getText().toString(),
                    edTxtPassword.getText().toString(),
                    edtTelefono.getText().toString()));
        }
    }
}
