package com.gmail.jumpercorderosa.planetabuffet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.jumpercorderosa.planetabuffet.adapter.IconAdapter;
import com.gmail.jumpercorderosa.planetabuffet.adapter.IconData;
import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.db.DBHandler;
import com.gmail.jumpercorderosa.planetabuffet.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SuppliersActivity extends AppCompatActivity {

    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);

        //obtem uma instancia do DB
        db = new DBHandler(this);

        //receber o seguimento da outra activity
        /*
        Intent intent = getIntent();
        if( intent != null) {
            int seguiment_id = intent.getIntExtra("seguiment_id", 0);
        }
        */

        //obtem lista de segmento de fornecedores
        List<Supplier> listSeg = db.getAllSuppliers();


        //Unfortunately android does not have Arrays.add()
        //You can't "add" a value to an array. Once you've created it, the size is fixed.
        List<IconData> myList = new ArrayList<IconData>();

        //percorre objetos da lista
        for(Supplier obj : listSeg) {
            obj.getName();

            myList.add(new IconData(obj.getName(),
                    getResources().getIdentifier(
                        "ic_rv_supplier",
                        "drawable",
                        getApplicationContext().getPackageName())));

        }

        IconData[] data = myList.toArray(new IconData[myList.size()]);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        IconAdapter adapter = new IconAdapter(data);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

}
