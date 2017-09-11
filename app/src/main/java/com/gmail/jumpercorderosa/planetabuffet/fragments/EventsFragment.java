package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

//tenho que importar pq estou dentro do fragments
import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;
import com.squareup.picasso.Picasso;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;
import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.trocaFragmento;

public class EventsFragment extends Fragment {

    private DBHandler db;

    ImageView imgWedding;
    ImageView imgAnniversary;
    ImageView imgSweetFifteen;
    ImageView imgCompanies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_events, container, false);

        setHasOptionsMenu(true);
        db = new DBHandler(getContext());

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        final User user = db.getUser(user_id);

        //ImageViews vazias
        imgWedding = (ImageView) view.findViewById(R.id.img_wedding);
        imgAnniversary = (ImageView) view.findViewById(R.id.img_aniversary);
        imgSweetFifteen = (ImageView) view.findViewById(R.id.img_sweet_fifteen);
        imgCompanies = (ImageView) view.findViewById(R.id.img_corporate_parties);


        //carrega as imagens via url para o imageView
        Picasso.with(view.getContext()).load("http://wallsdesk.com/wp-content/uploads/2016/02/wonderful-wedding-wallpaper.jpg").into(imgWedding);
        Picasso.with(view.getContext()).load("http://www.drodd.com/images16/happy-birthday-wallpaper5.jpg").into(imgAnniversary);
        Picasso.with(view.getContext()).load("http://cdn.renewcanceltv.com/wp-content/uploads/2015/11/sweet15.jpg").into(imgSweetFifteen);
        Picasso.with(view.getContext()).load("http://listtoday.org/wallpaper/2015/12/christmas-venue-18-cool-wallpaper.jpg").into(imgCompanies);

        //TODO==> tratar a excessão caso não consiga carregar a img
        imgWedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Wedding", Toast.LENGTH_SHORT).show();

                //o que fazer com esse hardcode???
                user.setEventTypeId(1);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new LocationFragment());

            }
        });

        imgAnniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(getApplicationContext(), )
                Toast.makeText(getContext(), "Anniversary", Toast.LENGTH_SHORT).show();

                user.setEventTypeId(2);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new LocationFragment());
            }
        });

        imgSweetFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(getApplicationContext(), )
                Toast.makeText(getContext(), "Sweet Fifteen", Toast.LENGTH_SHORT).show();

                user.setEventTypeId(3);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new LocationFragment());
            }
        });

        imgCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent i = new Intent(getApplicationContext(), )
                Toast.makeText(getContext(), "Companies", Toast.LENGTH_SHORT).show();

                user.setEventTypeId(4);
                db.updateUser(user);

                trocaFragmento(R.id.main_fragment, new LocationFragment());
            }
        });

        return view;
    }
}
