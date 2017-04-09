package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.Piso;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.PisoDTO;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.BusqAvFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.DetalleFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.HomeFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.ListaFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.NuevoAnuncioFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.LoginFragment;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments.RegistroFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.DataPassListener, ListaFragment.DataPassListenerPiso , BusqAvFragment.DataPassListenerBAv {

    private DrawerLayout drawerLayout;
    private TextView textViewEmailNavigatioDrawer;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private String email = "";
    private PisoDTO pisoDTO = new PisoDTO();

    @Override
    public void passData(String[] data) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();



        ListaFragment listaFragment = new ListaFragment ();
        Bundle args = new Bundle();

        args.putStringArray("datos", data);
        listaFragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment, listaFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void passDataPiso(Piso piso) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //String[] datosDetalle=new String[10];

        DetalleFragment detalleFragment = new DetalleFragment();
        Bundle args = new Bundle();


        String[] datos=new String[7];
        datos[0]=piso.getNombre();
        datos[1]=piso.getImagen();
        datos[2]=piso.getDescripcion();
        datos[3]=String.valueOf(piso.getPrecio());
        datos[4]=String.valueOf(piso.getHabitaciones());
        datos[5]=String.valueOf(piso.getBanyos());
        datos[6]=piso.getOpcion();


        args.putStringArray("datos", datos);
        detalleFragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment, detalleFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void passDataBAv(String[] data) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();



        ListaFragment listaFragment = new ListaFragment ();
        Bundle args = new Bundle();

        args.putStringArray("datos", data);
        listaFragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment, listaFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        textViewEmailNavigatioDrawer = (TextView) findViewById(R.id.textViewEmailNavigatioDrawer);

        setNavigationDrawer();
        pisoDTO.addDefaultData();
        //First fragment
        setFragment("home");
    }

    public void setNavigationDrawer() {
        //Toast.makeText(this, "[setNavigationDrawer]: " + email, Toast.LENGTH_SHORT).show();
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        Menu menu = navigationView.getMenu();
        menu.clear();
        menu.add("Home");
        menu.add("Busqueda avanzada");

        if(email.equals("")) {
            textViewEmailNavigatioDrawer.setText("Invitado");
            menu.add("Login");
            menu.add("Registro");
        } else {
            textViewEmailNavigatioDrawer.setText(email);
            menu.add("Mi Perfil");
            menu.add("Favoritos");
            menu.add("Salir");
        }
    }

    public void setEmailUsuarioLogin(String email) {
        //Toast.makeText(this, "[setEmailUsuarioLogin]: " + email, Toast.LENGTH_SHORT).show();
        this.email = email;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //Log.i("[NavigationDrawer] ", "menuItem.getItemId(): [" + menuItem.getTitle() + "]");
                        switch (menuItem.getTitle().toString()) {
                            case "Home":
                                menuItem.setChecked(true);
                                setFragment("home");
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case "Busqueda avanzada":
                                menuItem.setChecked(true);
                                setFragment("busqueda_avanzada");
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case "Login":
                                menuItem.setChecked(true);
                                setFragment("login");
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case "Registro":
                                menuItem.setChecked(true);
                                setFragment("registro");
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case "Mi Perfil":
                                return true;
                            case "Favoritos":
                                return true;
                            case "Salir":
                                email = "";
                                setNavigationDrawer();
                                return true;
                        }
                        return true;
                    }
                });
    }

    public void setFragment(String position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case "home":
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.fragment, homeFragment);
                fragmentTransaction.commit();
                break;
            case "login":
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.fragment, loginFragment);
                fragmentTransaction.commit();
                break;
            case "registro":
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                RegistroFragment registroFragment = new RegistroFragment();
                fragmentTransaction.replace(R.id.fragment, registroFragment);
                fragmentTransaction.commit();
                break;
            case "nuevo_anuncio":
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                NuevoAnuncioFragment nuevoAnuncioFragment = new NuevoAnuncioFragment();
                fragmentTransaction.replace(R.id.fragment, nuevoAnuncioFragment);
                fragmentTransaction.commit();
                break;
            case "busqueda_avanzada":
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                BusqAvFragment busqAvFragment = new BusqAvFragment();
                fragmentTransaction.replace(R.id.fragment, busqAvFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}

