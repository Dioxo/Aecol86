package me.dioxo.estudiante.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.dioxo.estudiante.Authentication.Authentication;
import me.dioxo.estudiante.CheckList.CheckListFragment;
import me.dioxo.estudiante.Constantes;
import me.dioxo.estudiante.MyInfo.MyInfoFragment;
import me.dioxo.estudiante.R;
import me.dioxo.estudiante.libs.ApplicationContextProvider;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MyInfoFragment.OnFragmentInteractionListener ,
        CheckListFragment.OnFragmentInteractionListener{
    @BindView(R.id.fab)
    FloatingActionButton fab;

    TextView email;

    private Boolean[] fragmentDisplayed = new Boolean[Constantes.FRAGMENTS];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        email = (TextView) headerView.findViewById(R.id.txtEmailUser);
        Intent intent = getIntent();


        String emailOrganizador = intent.getStringExtra("email");
        email.setText(emailOrganizador);

        cargarFragmentPorDefecto();
    }

    private void cargarFragmentPorDefecto() {
        //decir que la pantalla que se muestra es el fragment[0]
        // para no recargar la pantalla cada vez que se clickea
        mostrarUnFragment(0);
        Fragment fragment = new MyInfoFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, fragment)
                .commit();

        getSupportActionBar().setTitle("Mis datos");
        ((MyInfoFragment) fragment).isRegister = false;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            cerrarSesion();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cerrarSesion() {
        SharedPreferences settings = ApplicationContextProvider.getContext().getSharedPreferences(Constantes.ID_USER, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constantes.ID_USER, null);
        // Commit the edits!
        editor.apply();

        Intent intent = new Intent(this, Authentication.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean notShow = false;
        Fragment fragment = null;

        //si se clickea fragment[0] y todavía no se muestra en pantalla
        if (id == R.id.menu_myInfo && !fragmentDisplayed[0]) {

            // Handle the camera action
            fragment = new MyInfoFragment();
            ((MyInfoFragment) fragment).isRegister = false;
            //decir que la pantalla que se muestra es el fragment[0]
            // para no recargar la pantalla cada vez que se clickea
            mostrarUnFragment(0);
            item.setChecked(true);
            notShow = true;

        } else if (id == R.id.menu_checklist  && !fragmentDisplayed[2]) {
            mostrarUnFragment(2);
            notShow = true;
            fragment = new CheckListFragment();
        } /*else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/


        if (notShow){
            Log.i("Navigation", "cambio de fragment");
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.screen_area, fragment)
                        .commit();
            }

            item.setChecked(true);

            getSupportActionBar().setTitle(item.getTitle());

        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void mostrarUnFragment(int fragmentParaMostrar) {
        //rellenar el arreglo de false
        Arrays.fill(fragmentDisplayed, false);

        //dejar una unica posicion true
        fragmentDisplayed[fragmentParaMostrar] =  true;
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        Log.i("MyInfo", "FragmentAttached");
        if (fragment instanceof MyInfoFragment) {
            MyInfoFragment headlinesFragment = (MyInfoFragment) fragment;
            headlinesFragment.setFragmentListener(this);
        }
    }

    @Override
    public void onFragmentInteractionListener(String email){
        this.email.setText(email);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
