package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    //duração da activity
    private final int SPLASH_DISPLAY_LENGHT = 3500;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //obtem uma instancia do DB
        db = new DBHandler(this);

        //sync in case of changes
        SyncDatabase syncDatabase = new SyncDatabase();
        syncDatabase.execute();

        loadSplash();

    }

    private void loadSplash() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
        animation.reset();

        ImageView ivLogoBuffet = (ImageView) findViewById(R.id.logoPlanetaBuffet);

        if(ivLogoBuffet != null) {
            ivLogoBuffet.clearAnimation();
            ivLogoBuffet.startAnimation(animation);
        }

        //thread que é executada em paralelo com a animação
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //intenção do seu aplicativo para o sistema operacional
                //Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashActivity.this.finish(); //destroe esta activity, para não voltar para tela de splash
            }
        }, SPLASH_DISPLAY_LENGHT);

    }


    //banco
    //sincronização da splash screen

    /* Lembrar que se o retorno fosse uma lista...
        //só para usar o recyclewier
        //api_utils/retrofit/acessa o web service com a classe base
        //userAPI é o serviço
        //retro_fit é para pegar vários
        //retro fit client ==> cria o obj retro_fit
        //stheto ==> plugin do face para debugar a aplicação
     */

    //Neste caso não retorna uma lista, mas apenas um registro, portanto
    //tenho que usar o syncdatabase (pegar 1 registro do webservice)
    private class SyncDatabase extends AsyncTask<String, Void, String> {
        private ProgressDialog progress;

        //loading
        @Override
        protected void onPreExecute() {
            //progress = ProgressDialog.show(SplashActivity.this, "Wait", "Searching data on server...");
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                if (connection.getResponseCode() == 200) {
                    BufferedReader stream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String linha = "";
                    StringBuilder resposta = new StringBuilder();
                    while ((linha = stream.readLine()) != null) {
                        resposta.append(linha);
                    }
                    connection.disconnect();
                    return resposta.toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            //progress.dismiss();
            if (s != null) {
                try {
                    JSONObject json = new JSONObject(s);

                    String user = json.getString("usuario");
                    String password = json.getString("senha");
                    db.addUser(new User(0, user, password, "", "", "", 0, 0));

                    Log.i("User", user);
                    Log.i("Password", password);
                    //Log.i("Count", String.valueOf(db.getUsersCount()));
                    //Log.i("User",db.getUser(1).getLogin());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            SplashActivity.this.finish();
                        }
                    }, SPLASH_DISPLAY_LENGHT);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

