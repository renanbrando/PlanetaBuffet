//package com.gmail.jumpercorderosa.planetabuffet.fragments;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
////tenho que importar pq estou dentro do fragments
//import com.gmail.jumpercorderosa.planetabuffet.AdapterCustomSupplier;
//import com.gmail.jumpercorderosa.planetabuffet.R;
//import com.gmail.jumpercorderosa.planetabuffet.model.SupplierSegment;
//
//import java.util.List;
//
////menu de suppliers/list_view
//public class SuppliersFragment extends Fragment {
//
//    ListView lvSuppliersSeg;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//
//        //load SQLlite
//        List<SupplierSegment> suppliersSeg = loadSuppliers();
//
//        //associei a minha lista a um fragment (activity)
//        View view = inflater.inflate(R.layout.fragment_suppliers_segment, container, false);
//
//        //acessei uma view via classe R
//        lvSuppliersSeg = (ListView) view.findViewById(R.id);
//
//        //ArrayAdapter é um especialista em lidar com listas
//        //todos os construtores dele exigem pelo menos um Context que será a nossa activity e um int que será o recurso que representará cada item da lista.
//        AdapterCustomSupplier adapter = new AdapterCustomSupplier(suppliersSeg, this.getActivity());
//
//        lvSuppliersSeg.setAdapter(adapter);
//
//        return view;
//    }
//
//
//}
