package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.Supplier;
import com.gmail.jumpercorderosa.planetabuffet.model.User;
import com.gmail.jumpercorderosa.planetabuffet.model.UserSupplier;

import static com.gmail.jumpercorderosa.planetabuffet.activity.MainActivity.PREFS_NAME;

public class SupplierDetailActivity extends AppCompatActivity {

    private DBHandler db;

    TextView tvSupplier;
    TextView tvSupplierContact;
    Button btnCall;
    CheckBox cbFavorite;

    int supplier_id = 0;
    int user_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_detail);

        tvSupplier = (TextView) findViewById(R.id.tvSupplier);
        tvSupplierContact = (TextView) findViewById(R.id.tvSupplierContact);
        btnCall = (Button) findViewById(R.id.btnCall);
        cbFavorite = (CheckBox) findViewById(R.id.cbFavorite);

        btnCall.setBackgroundResource(R.drawable.ic_call);

        db = new DBHandler(this);


        //pega o supplier selecionado do outro fragment e carrega os dados do fornecedor
        Intent intent = getIntent();
        if( intent != null) {
            supplier_id = intent.getIntExtra("supplier_id", 0);
        }

        final Supplier supplier = db.getSupplier(supplier_id);

        //obtem dados do usuario para atualiza-lo
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", 0);

        //final User user = db.getUser(user_id);

        if(supplier != null) {

            tvSupplier.setText(supplier.getName());
            tvSupplierContact.setText(supplier.getContact());

            btnCall.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent ligacao = new Intent(Intent.ACTION_CALL);

                    String telefone = supplier.getPhoneNumber();
                    telefone = telefone.replace("-", "");

                    try {

                        //ligacao.setData(Uri.parse("tel:982874502"));
                        ligacao.setData(Uri.parse("tel:" + telefone ));

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
                    UserSupplier user_sup = new UserSupplier();
                    user_sup.setUserId(user_id);
                    user_sup.setSupplierId(supplier_id);

                    if ( ((CheckBox)v).isChecked() ) {
                        Toast.makeText(getApplicationContext(), "Adicionado aos seus favoritos", Toast.LENGTH_SHORT).show();
                        db.addUserSupplier(user_sup);
                    } else {
                        Toast.makeText(getApplicationContext(), "Removido dos seus favoritos", Toast.LENGTH_SHORT).show();
                        db.deleteUserSupplier(user_sup);
                    }
              }
            });
        }
    }
}
