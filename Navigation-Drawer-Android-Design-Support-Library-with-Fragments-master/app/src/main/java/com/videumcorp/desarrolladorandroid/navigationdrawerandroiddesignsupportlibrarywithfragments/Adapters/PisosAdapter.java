package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.Piso;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;
import java.util.List;

/**
 * Created by Javier on 20/03/2017.
 */

public class PisosAdapter extends BaseAdapter {

    private Context context;
    private List<Piso> items;

    public PisosAdapter(Context context, List<Piso> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }



        TextView tvnombre = (TextView) rowView.findViewById(R.id.textNombrePiso);
        TextView tvhabitaciones = (TextView) rowView.findViewById(R.id.textHabitacionesPiso);
        TextView tvprecio = (TextView) rowView.findViewById(R.id.textPrecioPiso);
        ImageView imagen= (ImageView) rowView.findViewById(R.id.imageViewPiso);


        Piso item = this.items.get(position);
        tvnombre.setText(item.getNombre());
        tvhabitaciones.setText("Habitaciones: "+item.getHabitaciones());
        tvprecio.setText("Precio: "+item.getPrecio()+"€");

        int id=context.getResources().getIdentifier(context.getPackageName()+":drawable/" + item.getImagen(), null, null);
        imagen.setImageResource(id);
        return rowView;
    }
}
