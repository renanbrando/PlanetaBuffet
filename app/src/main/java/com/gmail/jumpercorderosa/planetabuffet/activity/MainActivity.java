package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.share.widget.ShareDialog;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.fragments.AboutFragment;
import com.gmail.jumpercorderosa.planetabuffet.fragments.CountdownFragment;
import com.gmail.jumpercorderosa.planetabuffet.fragments.EventsFragment;
import com.gmail.jumpercorderosa.planetabuffet.fragments.FirstStepsFragment;
import com.gmail.jumpercorderosa.planetabuffet.fragments.ProfileFragment;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ShareDialog shareDialog;
    private DBHandler db;

    private static FragmentTransaction transaction;
    private static FragmentManager fragmentManager;

    private static final String KEEP_CONNECTED = "keep_connected";
    private static final String USER_ID = "user_id";
    public static final String PREFS_NAME = "pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        /*
        shareDialog = new ShareDialog(this);

        //float icon do ladinho
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();
                    shareDialog.show(linkContent);
                }
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.bringToFront();


        //==================================================================
        //| Com base nas informaçoes da base, verifica se eh um usuario novo
        //==================================================================
        SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        db = new DBHandler(this);

        final User user = db.getUser(user_id);

        if(user != null) {

            if(user.getBuffetId() != 0) {
                trocaFragmento(R.id.main_fragment, new CountdownFragment());
            } else {
                trocaFragmento(R.id.main_fragment, new FirstStepsFragment());
            }

        }
    }

    //DIALOG de fechamento da minha main activity
    @Override
    public void onBackPressed() { // botão de voltar do android

        //sobre o navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder;

            //a partir dessa versão pode usar o material dialog
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle(R.string.exit)
                    .setMessage(R.string.exit_message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // sai do app
                            MainActivity.super.onBackPressed();

                            SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean(KEEP_CONNECTED, false);
                            editor.apply();

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //continua no app
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
            return true;
        }
        */
        item.getItemId();
        item.setEnabled(false);

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean logout = false;

        switch (id) {
            case R.id.nav_home:
                fechaNavigation();
                trocaFragmento(R.id.main_fragment, new CountdownFragment());
                break;
            case R.id.nav_profile:
                fechaNavigation();
                trocaFragmento(R.id.main_fragment, new ProfileFragment());
                break;
            case R.id.nav_favorites:
                //implementar RV com os itens que o cliente escolheu
                break;
            case R.id.nav_eventos:
                startActivity(new Intent(MainActivity.this, SuppliersSegmentActivity.class));
                break;
            case R.id.nav_settings:
                fechaNavigation();
                trocaFragmento(R.id.main_fragment, new EventsFragment());
                break;
            case R.id.nav_maps:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                break;
            case R.id.nav_about:
                fechaNavigation();
                trocaFragmento(R.id.main_fragment, new AboutFragment());
                break;
            case R.id.nav_classify:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://play.google.com/store/apps/collection/topselling_free"));
                startActivity(intent);
                break;
            case R.id.logout:
                logout = true;
                break;
            default:
                Toast.makeText(getApplicationContext(), "Oooops!", Toast.LENGTH_SHORT).show();// to do
                break;
        }

        if(logout) {

            AlertDialog.Builder builder;

            //a partir dessa versão pode usar o material dialog
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }

            builder.setTitle("Exit")
                    .setMessage(R.string.exit_message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            //Atualiza o Shared Preferences
                            SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean(KEEP_CONNECTED, false);
                            editor.apply();

                            //Fecha a aplicação
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                            finish();

                            //volta para a tela de login
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            //android.os.Process.killProcess(android.os.Process.myPid());
                            //System.exit(0);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //continua no app
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        return true;
    }

    //troca os fragmentos e desabilita incluir fragment na pilha, volta para a ultima activity
    public static void trocaFragmento(int activity, Fragment fragment) {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(activity, fragment);
        //transaction.disallowAddToBackStack();
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Generic method for swapping fragments in the activity with extra value
    private void trocaFragmentoComValor(int activity, Fragment fragment, String key, int value){
        Bundle bundle = new Bundle();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        bundle.putInt(key, value);
        fragment.setArguments(bundle);
        transaction.replace(activity, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

    public void fechaNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
}


