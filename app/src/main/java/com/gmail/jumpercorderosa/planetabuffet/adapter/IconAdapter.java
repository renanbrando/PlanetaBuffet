package com.gmail.jumpercorderosa.planetabuffet.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;
import com.gmail.jumpercorderosa.planetabuffet.activity.SupplierDetailActivity;
import com.gmail.jumpercorderosa.planetabuffet.activity.SuppliersActivity;
import com.gmail.jumpercorderosa.planetabuffet.activity.SuppliersSegmentActivity;

//Now that we have a container (SupplierSegment) for our data, we can create the custom adapter that will show our data.

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private IconData[] data;
    public SuppliersSegmentActivity suppliersSegment = null;
    public SuppliersActivity supplier = null;

    public IconAdapter(IconData[] data, SuppliersSegmentActivity suppliersSegment,
                       SuppliersActivity supplier ) {
        this.data = data;
        this.suppliersSegment = suppliersSegment;
        this.supplier = supplier;
    }

    @Override
    //Here we need to “inflate” our custom list item view from our xml layout
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);

        //As the name suggests, we’ll also need to create a new ViewHolder object
        // that will be associated with that row and we can return that object
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    //is where we actually populate the views in the list item row with data
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(data[position].getDescription());
        holder.imageView.setImageResource(data[position].getImgId());
        holder.segment_id = data[position].getSupplierSegmentId();
        holder.supplier_id = data[position].getSupplierId();
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    //The ViewHolder pattern is a way to make lists very efficient by recycling the list items that go off the screen.
    //This allows us to use the least amount of memory while still giving the user a smooth experience
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        int segment_id = 0;
        int supplier_id = 0;

        public ViewHolder(final View itemView) {
            super(itemView);

            try {
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                textView = (TextView) itemView.findViewById(R.id.textView);

                this.textView.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        if(segment_id != 0)
                            Toast.makeText(v.getContext(),
                                    "Clicou no obj [" + Integer.toString(segment_id) + "]", Toast.LENGTH_SHORT).show();
                        else if(supplier_id != 0)
                            Toast.makeText(v.getContext(),
                                    "Clicou no obj [" + Integer.toString(supplier_id) + "]", Toast.LENGTH_SHORT).show();

                        if(supplier != null) {
                            Intent activity = new Intent(supplier, SupplierDetailActivity.class);
                            activity.putExtra("supplier_id", supplier_id);
                            supplier.startActivity(activity);
                        } else if (suppliersSegment != null) {
                            Intent activity = new Intent(suppliersSegment, SuppliersActivity.class);
                            activity.putExtra("seguiment_id", segment_id);
                            suppliersSegment.startActivity(activity);
                        }  else {
                            Toast.makeText(v.getContext(), "Oooops", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (Exception e) {
                Log.e("aux", "Exception", e);
            }
        }
    }
}

