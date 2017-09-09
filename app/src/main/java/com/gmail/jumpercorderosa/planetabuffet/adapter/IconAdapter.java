package com.gmail.jumpercorderosa.planetabuffet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.jumpercorderosa.planetabuffet.R;

/**
 * Created by danielle on 19/08/2017.
 */

//Now that we have a container (SupplierSegment) for our data, we can create the custom adapter that will show our data.

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    //instancia do db
    //private DBHandler db;
    private IconData[] data;

    public IconAdapter(IconData[] data) {
        this.data = data;
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
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    //The ViewHolder pattern is a way to make lists very efficient by recycling the list items that go off the screen.
    //This allows us to use the least amount of memory while still giving the user a smooth experience
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);

            this.imageView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    int id = v.getId();
                    Toast.makeText(v.getContext(), "Clicou no obj [" + Integer.toString(id) + "]", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

