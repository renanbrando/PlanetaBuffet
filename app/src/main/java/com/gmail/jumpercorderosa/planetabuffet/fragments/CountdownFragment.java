package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.gmail.jumpercorderosa.planetabuffet.R;
import com.squareup.picasso.Picasso;

public class CountdownFragment extends Fragment {

    ImageView imgWedding;
    ShareDialog shareDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);

        /*
        shareDialog = new ShareDialog(this);

        //float icon do ladinho

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
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

        //ImageViews vazias
        imgWedding = (ImageView) view.findViewById(R.id.img_wedding);

        imgWedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(getApplicationContext(), )
                Toast.makeText(getContext(), "Wedding", Toast.LENGTH_LONG).show();

                //Como mudar para outro Abrir outro fragmento?!
                //trocaFragmento(, new LocationFragment());

            }
        });

        return view;
    }
}
