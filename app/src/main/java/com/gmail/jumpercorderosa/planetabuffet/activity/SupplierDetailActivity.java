package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.Supplier;
import com.gmail.jumpercorderosa.planetabuffet.model.User;
import com.gmail.jumpercorderosa.planetabuffet.model.UserSuppliers;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;

public class SupplierDetailActivity extends AppCompatActivity {

    private DBHandler db;

    Button btnCall;
    CheckBox cbFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);

        //obtem dados do usuario para atualiza-lo
        //SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //int user_id = sharedPref.getInt("user_id", 0);

        db = new DBHandler(this);

        //TODO
        //pegar o supplier selecionado do outro fragment e carrega os dados do fornecedor
        //Supplier supplier = new Supplier();
        //supplier = db.getSupplier(2);

        btnCall = (Button) findViewById(R.id.btnCall);
        cbFavorite = (CheckBox) findViewById(R.id.cbFavorite);

        btnCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent ligacao = new Intent(Intent.ACTION_CALL);

                //supplier.getPhoneNumber();

                try {
                    ligacao.setData(Uri.parse("tel:982874502"));

                    /*
                    if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    */

                    startActivity(ligacao);

                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error in your phone call" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save as favorite to load the rv

                /*
                UserSuppliers user_sup = new UserSuppliers();
                user_sup.setUserId();
                user_sup.setUserSupplierId();

                db.addUserSupplier(user_sup);
                */
            }
        });

    }
}
