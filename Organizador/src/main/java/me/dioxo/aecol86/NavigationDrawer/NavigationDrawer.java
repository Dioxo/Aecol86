package me.dioxo.aecol86.NavigationDrawer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.dioxo.aecol86.Authentication.Authentication;
import me.dioxo.aecol86.Constantes;
import me.dioxo.aecol86.Information.InformationActivity;
import me.dioxo.aecol86.ListerEstudiantes.ListerEstudiantes;
import me.dioxo.aecol86.R;
import me.dioxo.aecol86.libs.ApplicationContextProvider;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ListerEstudiantes.OnFragmentInteractionListener {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        email = (TextView) headerView.findViewById(R.id.txtEmailUser);
        Intent intent = getIntent();


        String emailOrganizador = intent.getStringExtra("email");
        email.setText(emailOrganizador);

        cargarFragmentPorDefecto();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        boolean transition = false;
        Fragment fragment = null;

        if (id == R.id.nuevos_estudiantes) {
            // Handle the camera action
            transition = true;
            fragment = new ListerEstudiantes();
        }/* else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        if (transition) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.screen_area, fragment)
                    .commit();

            item.setChecked(true);

            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void cargarFragmentPorDefecto() {

        Fragment fragment = new ListerEstudiantes();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_area, fragment)
                .commit();

        getSupportActionBar().setTitle("Nuevos Estudiantes");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, InformationActivity.class));
    }
}
