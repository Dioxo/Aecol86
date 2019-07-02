package me.dioxo.estudiante.Register;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.estudiante.Estudiante;
import me.dioxo.estudiante.MyInfo.MyInfoFragment;
import me.dioxo.estudiante.R;

public class RegisterActivity extends AppCompatActivity implements  MyInfoFragment.OnFragmentInteractionListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Estudiante estudiante;
    private boolean existPersona = true;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);



        MyInfoFragment fragment = (MyInfoFragment) getSupportFragmentManager().findFragmentById(R.id.myInfo);

        if (fragment != null) {
            toolbar.setTitle("Registro");
            fragment.afficherRegister();
        }


    }


    @Override
    public void onFragmentInteractionListener(String email) {

    }
}


