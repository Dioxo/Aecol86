package me.dioxo.estudiante.Authentication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.estudiante.Navigation.NavigationDrawer;
import me.dioxo.estudiante.R;
import me.dioxo.estudiante.Register.RegisterActivity;
import me.dioxo.estudiante.RegisterOtros.RegisterOtros;

public class Authentication extends AppCompatActivity implements Authentication_View {

    @BindView(R.id.edTxtUser)
    EditText edTxtUser;
    @BindView(R.id.edTxtPassword)
    EditText edTxtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private Authentication_Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ButterKnife.bind(this);

        presenter = new Authentication_Presenter_Impl(this);
        presenter.onCreate();
        presenter.checkAlreadyConnected();
        hideProgressBar();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void enableInputs() {
        edTxtPassword.setEnabled(true);
        edTxtUser.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    public void disableInputs() {
        edTxtPassword.setEnabled(false);
        edTxtUser.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    public void goToNextPage() {
        Intent intent = new Intent(this, NavigationDrawer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        Log.i("Extras", edTxtUser.getText().toString());
        intent.putExtra("email", edTxtUser.getText().toString());
        startActivity(intent);
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
    public void loginError(String error) {
        effacerText();
        edTxtPassword.setError(error);
    }

    private void effacerText() {
        edTxtPassword.setText("");
        edTxtUser.setText("");
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked() {
        presenter.confirmerMDP(edTxtUser.getText().toString(), edTxtPassword.getText().toString());
        //effacerText();
    }

    @OnClick(R.id.register)
    public void onRegisterClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.register_dialog, null);
        Button btnVoyFrancia = view.findViewById(R.id.btnVoyFrancia);
        Button btnEstoyFrancia = view.findViewById(R.id.btnEstoyFrancia);

        btnEstoyFrancia.setOnClickListener(v -> startActivity(new Intent(this, RegisterOtros.class)));

        btnVoyFrancia.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
