package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.trocaFragmento;

public class FirstStepsFragment extends Fragment {

    private DBHandler db;

    Button btnEventSettings;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_first_steps, container, false);

        //setHasOptionsMenu(true);
        btnEventSettings = (Button) view.findViewById(R.id.btnEventSettings);

        btnEventSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Wedding", Toast.LENGTH_SHORT).show();
                trocaFragmento(R.id.main_fragment, new EventsFragment());
            }
        });

        return view;
    }
}
