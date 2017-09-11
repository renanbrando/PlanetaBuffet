package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;
import com.squareup.picasso.Picasso;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;
import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.trocaFragmento;

public class LocationFragment extends Fragment {

    private DBHandler db;

    ImageView imgEspacoTenor;
    ImageView imgVillaBertolazzi;
    ImageView imgEspacoTenor2;
    ImageView imgPlanetaMorumbi;
    ImageView imgPlanetaAlphaville;
    ImageView imgPlanetaAltoSantana;
    ImageView imgPlanetaAnaliaFranco;
    ImageView imgPlanetaAltoLapa;
    ImageView imgPlanetaGuarulhos;
    ImageView imgPlanetaJundiai;
    ImageView imgPlanetaCasaArvore;
    ImageView imgPlanetaSantana;
    ImageView imgPlanetaPompeia;
    ImageView imgPlanetaTatuape;
    ImageView imgPlanetaTatuape2;
    ImageView imgPlanetaVilaRomana;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_locations, container, false);

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getApplicationContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        db = new DBHandler(getContext());

        int aux = db.getUsersCount();
        final User user = db.getUser(user_id);

        //ImageViews vazias
        imgEspacoTenor = (ImageView) view.findViewById(R.id.img_espaco_tenor);
        imgVillaBertolazzi = (ImageView) view.findViewById(R.id.img_villa_bertolazzi);
        imgEspacoTenor2 = (ImageView) view.findViewById(R.id.img_espaco_tenor_2);
        imgPlanetaMorumbi = (ImageView) view.findViewById(R.id.img_planeta_morumbi);
        imgPlanetaAlphaville = (ImageView) view.findViewById(R.id.img_planeta_alphaville);
        imgPlanetaAltoSantana = (ImageView) view.findViewById(R.id.img_planeta_alto_santana);
        imgPlanetaAnaliaFranco = (ImageView) view.findViewById(R.id.img_planeta_analia_franco);
        imgPlanetaAltoLapa = (ImageView) view.findViewById(R.id.img_planeta_alto_lapa);
        imgPlanetaGuarulhos = (ImageView) view.findViewById(R.id.img_planeta_guarulhos);
        imgPlanetaJundiai = (ImageView) view.findViewById(R.id.img_planeta_jundiai);
        imgPlanetaCasaArvore = (ImageView) view.findViewById(R.id.img_planeta_casa_arvore);
        imgPlanetaSantana = (ImageView) view.findViewById(R.id.img_planeta_santana);
        imgPlanetaPompeia = (ImageView) view.findViewById(R.id.img_planeta_pompeia);
        imgPlanetaTatuape = (ImageView) view.findViewById(R.id.img_planeta_tatuape);
        imgPlanetaTatuape2 = (ImageView) view.findViewById(R.id.img_planeta_tatuape_2);
        imgPlanetaVilaRomana = (ImageView) view.findViewById(R.id.img_planeta_vila_romana);

        //carrega as imagens via url para o imageView
        Picasso.with(view.getContext()).load("https://www.espacotenor.com.br/fotos/index_imagem1.jpg").into(imgEspacoTenor);
        Picasso.with(view.getContext()).load("https://www.espacotenor.com.br/fotos/index_imagem2.jpg").into(imgVillaBertolazzi);
        Picasso.with(view.getContext()).load("https://www.espacotenor.com.br/fotos/index_imagem3.jpg").into(imgEspacoTenor2);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/06/Planeta-Morumbi.jpg").into(imgPlanetaMorumbi);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/06/Buffet-Planeta-Kids-Alphaville-16.jpg").into(imgPlanetaAlphaville);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Fachada-Alto-de-santana-1-800x533-585x390.jpg").into(imgPlanetaAltoSantana);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/LEA_28101-1-585x390-1.jpg").into(imgPlanetaAnaliaFranco);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/fachada-lapa-2-2-baixa-1-800x523-585x382-1.jpg").into(imgPlanetaAltoLapa);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Guarulhos1-800x600-533x400.png").into(imgPlanetaGuarulhos);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Fachada-jundiai2-585x330.jpg").into(imgPlanetaJundiai);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/IMG_8124-2-2-2-800x600-533x400-1.jpg").into(imgPlanetaCasaArvore);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Planeta-Kids-unidade-Santana-1-800x530-585x388.jpg").into(imgPlanetaSantana);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Pompeia-1-800x539-585x394-1.jpg").into(imgPlanetaPompeia);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Tatuape%CC%81-I2-800x534-585x390-1.png").into(imgPlanetaTatuape);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/Tatuape%CC%81-II-fundo-roxo-21-800x533-585x390-1.png").into(imgPlanetaTatuape2);
        Picasso.with(view.getContext()).load("http://www.planetakids.com.br/wp-content/uploads/2017/03/VILA-ROMANA-581x400.jpg").into(imgPlanetaVilaRomana);

        //TODO==> tratar a excessão caso não não consiga carregar a img

        imgEspacoTenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), )
                Toast.makeText(v.getContext(), "Espaço Tenor", Toast.LENGTH_LONG).show();

                //o que fazer com esse hardcode??? =/
                user.setBuffetId(1);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgVillaBertolazzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Villa Bertolazzi", Toast.LENGTH_LONG).show();

                user.setBuffetId(2);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgEspacoTenor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Espaço Tenor II", Toast.LENGTH_LONG).show();

                user.setBuffetId(3);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaMorumbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Morumbi", Toast.LENGTH_LONG).show();

                user.setBuffetId(4);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaAlphaville.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Alphaville", Toast.LENGTH_LONG).show();

                user.setBuffetId(5);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaAnaliaFranco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Analia Franco", Toast.LENGTH_LONG).show();

                user.setBuffetId(6);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaAltoLapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Alto da Lapa", Toast.LENGTH_LONG).show();

                user.setBuffetId(7);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaGuarulhos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Guarulhos", Toast.LENGTH_LONG).show();

                user.setBuffetId(8);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaJundiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Jundiai", Toast.LENGTH_LONG).show();

                user.setBuffetId(9);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaCasaArvore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Casa Arvore", Toast.LENGTH_LONG).show();

                user.setBuffetId(10);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaSantana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Santana", Toast.LENGTH_LONG).show();

                user.setBuffetId(11);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaPompeia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Pompeia", Toast.LENGTH_LONG).show();

                user.setBuffetId(12);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaTatuape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tatuape", Toast.LENGTH_LONG).show();

                user.setBuffetId(13);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        imgPlanetaTatuape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tatuape II", Toast.LENGTH_LONG).show();

                user.setBuffetId(14);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());

            }
        });

        imgPlanetaVilaRomana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Vila Romana", Toast.LENGTH_LONG).show();

                user.setBuffetId(15);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new CalendarFragment());
            }
        });

        return view;
    }
}
