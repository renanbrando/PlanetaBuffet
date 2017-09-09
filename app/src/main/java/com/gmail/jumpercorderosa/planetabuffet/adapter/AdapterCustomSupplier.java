//package com.gmail.jumpercorderosa.planetabuffet;
//
//import android.app.Activity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import com.gmail.jumpercorderosa.planetabuffet.fragments.SuppliersFragment;
//
//import java.util.List;
//
////o Android nos fornece a classe BaseAdapter que permite a criação de um adapter personalizado!
//
//public class AdapterCustomSupplierSeg extends BaseAdapter {
//
//    private final List<SupplierSegment> supplier_seg;
//    private final Activity act;
//
//    //Construtor
//    //Veja que estamos passando o parâmetro this que representa o objeto da própria Activity que está fazendo a chamada. Precisamos receber também essa Activity via construtor:
//    public AdapterCustomSupplierSeg(List<SupplierSegment> suppliers, Activity act) {
//        this.supplier_seg = suppliers;
//        this.act = act;
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    // Obtem um item a partir de uma posição
//    public Object getItem(int position) {
//        return supplier_seg.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        //return suppliers.get(Supplier.getId());
//        return 0;
//    }
//
//    //Precisamos, de alguma forma, pegar a View que representa o nosso layout personalizado
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//}
//
