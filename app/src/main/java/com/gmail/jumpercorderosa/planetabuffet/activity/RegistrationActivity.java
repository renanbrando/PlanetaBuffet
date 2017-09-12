package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etPhoneNumber;
    private EditText etLogin;
    private EditText etPassword;
    //private EditText etEventDate;
    private Button btnOK;

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DBHandler(this);

        etName = (EditText) findViewById(R.id.etName);
        //etEmail = (EditText) findViewById(R.id.etEmail);
        //etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnOK = (Button) findViewById(R.id.btnOK);

        //textv.setShadowLayer(1, 0, 0, Color.BLACK);

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                //String email = etEmail.getText().toString();
                //String phone = etPhoneNumber.getText().toString();
                String login = etLogin.getText().toString();
                String password = etPassword.getText().toString();

                if(name.equals("") || login.equals("") || password.equals("")) {
                    Toast.makeText(v.getContext(), "All fields are mandatory! Please try again!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (db.checkUser(login, password) ) {
                    Toast.makeText(v.getContext(), "Login already exist! Please try another one!", Toast.LENGTH_LONG).show();
                    return;
                }

                //adiciona novo usuario
                User user = new User();
                user.setEmail(name);
                user.setLogin(login);
                user.setPassword(password);
                //user.setEmail(email);
                //user.setPhoneNumber(phone);
                user.setActiveFlag(true);
                db.addUser(user);

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                RegistrationActivity.this.finish(); //destroe esta activity, para não voltar para tela de splash
            }
        });

        /*
        etEventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Show your dialog on the click event listener of your button
                DialogFragment newFragment = new CalendarFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        */


    }
}
