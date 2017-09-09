package com.gmail.jumpercorderosa.planetabuffet.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.trocaFragmento;

/**
 * Created by danielle on 20/08/2017.
 */

public class CalendarFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private DBHandler db;
    private static final String PREFS_NAME = "pref";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_calendar, container, false);


        setHasOptionsMenu(true);
        db = new DBHandler(getContext());

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        int aux = db.getUsersCount();
        final User user = db.getUser(user_id);


        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.onCreateDialog(savedInstanceState);

        //trocaFragmento(R.id.main_fragment, new CountdowmnFragment());
        return view;
    }


    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getContext(),
                    (DatePickerDialog.OnDateSetListener)
                            getContext(), year, month, day);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) getView().findViewById(R.id.showDate)).setText(dateFormat.format(calendar.getTime()));
    }
}

/*
public class static extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //obtem dados do usuario para atualiza-lo

        SharedPreferences sharedPref = getContext().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        int user_id = sharedPref.getInt("user_id", 0);

        int aux = db.getUsersCount();
        final User user = db.getUser(user_id);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener)
                        getActivity(), year, month, day);

        //o que fazer com esse hardcode???


        String format_date = Integer.toString(day) + '/' + Integer.toString(month) + '/' + Integer.toString(year);

        user.setEventDate(format_date);
        db.updateUser(user);

        trocaFragmento(R.id.main_fragment, new CountdownFragment());

        return dialog;

    }

    //???
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //btnDate.setText(ConverterDate.ConvertDate(day, month + 1, year));
    }

}
*/


