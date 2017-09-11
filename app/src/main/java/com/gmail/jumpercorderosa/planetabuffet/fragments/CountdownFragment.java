package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.content.SharedPreferences;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;


public class CountdownFragment extends Fragment {

    private DBHandler db;

    TextView tvCountdownn;
    ShareDialog shareDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);

        tvCountdownn = (TextView) view.findViewById(R.id.tvCountdown);

        setHasOptionsMenu(true);
        db = new DBHandler(getContext());

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        //load user
        final User user = db.getUser(user_id);

        if(user != null) {

            SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");

            String event_date = user.getEventDate();

            Calendar c = Calendar.getInstance();
            String current_date = date_format.format(c.getTime());


            try {

                //Date conv_current_date = new Date();
                Date conv_current_date = date_format.parse(current_date);

                //Date conv_event_date = new Date();
                Date conv_event_date = date_format.parse(event_date);

                //long countdown = daysBetween(conv_event_date, conv_current_date);

                int countdown = daysBetween(conv_current_date, conv_event_date);

                tvCountdownn.setText(String.valueOf(countdown));


            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }


        }

        /*
        //float icon do ladinho
        shareDialog = new ShareDialog(this);
       final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                fab.setImageResource(R.drawable.favicon);

                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                            .build();
                    shareDialog.show(linkContent);
                }
            }
        });



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

        */

        return view;
    }

    public static Calendar getDatePart(Date date){
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millisecond in second

        return cal;                                  // return the date part
    }

    /**
     * This method also assumes endDate >= startDate
     **/
    /*
    public static long daysBetween(Date startDate, Date endDate) {
        Calendar sDate = getDatePart(startDate);
        Calendar eDate = getDatePart(endDate);

        long daysBetween = 0;
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
    */

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
