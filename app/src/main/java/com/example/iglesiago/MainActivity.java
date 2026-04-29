package com.example.iglesiago;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.iglesiago.request.ApiClient;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iglesiago.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_iniciar_sesion, R.id.nav_devocional)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configuramos el listener para el menú lateral
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_salir) {
                cerrarSesion();
                return true;
            }

            // Esto permite que el resto de los botones sigan funcionando
            boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
            if (handled) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawers();
            }
            return handled;
        });

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
/*
    private void cerrarSesion() {
        // 1. Borramos el token
        ApiClient.borrarToken(this);

        // 2. Mandamos al usuario al Login
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_iniciar_sesion); // Usá el ID que tengas en tu nav_graph

        // 3. Cerramos el menú lateral
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawers();

        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }*/


    private void cerrarSesion() {
        // 1. Borramos el rastro
        ApiClient.borrarToken(this);

        // 2. Cerramos el drawer (el menú lateral)
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawers();

        // 3. MANDATORIO: Esto obliga a ejecutar onPrepareOptionsMenu de nuevo
        invalidateOptionsMenu();

        // 4. Navegamos al inicio o login
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.nav_inicio);

        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Verificamos si hay token
        String token = ApiClient.leerToken(this);

        // Buscamos el ítem en el menú lateral
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu drawerMenu = navigationView.getMenu();
        MenuItem itemSalir = drawerMenu.findItem(R.id.nav_salir);
        MenuItem itemLogin = drawerMenu.findItem(R.id.nav_iniciar_sesion);

        if (token != null) {
            // Si hay sesión: mostrar Salir, ocultar Login
            if (itemSalir != null) itemSalir.setVisible(true);
            if (itemLogin != null) itemLogin.setVisible(false);
        } else {
            // Si no hay sesión: ocultar Salir, mostrar Login
            if (itemSalir != null) itemSalir.setVisible(false);
            if (itemLogin != null) itemLogin.setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

}