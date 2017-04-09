package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleFragment extends Fragment {


    public DetalleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detalle, container, false);


        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Detalle de piso");

        Bundle args = getArguments();
        String[] datos=args.getStringArray("datos");

        String nombre=datos[0];
        String imagen=datos[1];
        String descripcion=datos[2];
        String precio="Precio: "+datos[3]+"€";
        String habbanyo="Habitaciones: "+datos[4]+" Baños: "+datos[5];
        String opcion="Tipo: "+datos[6];


        TextView tvdetNombre = (TextView) view.findViewById(R.id.textViewDetalleNombre);
        ImageView imvImagen=(ImageView) view.findViewById(R.id.imageViewDetalle);
        TextView tvDescripcion=(TextView) view.findViewById(R.id.textViewDetalleDesc);
        TextView tvprecio=(TextView) view.findViewById(R.id.textViewDetPrecio);
        TextView tvhabbanyo=(TextView) view.findViewById(R.id.textViewDetHabBanyo);
        TextView tvopcion=(TextView) view.findViewById(R.id.textViewDetTipo);
        int id=getActivity().getApplicationContext().getResources().getIdentifier(getActivity().getApplicationContext().getPackageName()+":drawable/" + imagen, null, null);


        tvdetNombre.setText(nombre);
        imvImagen.setImageResource(id);
        tvDescripcion.setText(descripcion);
        tvprecio.setText(precio);
        tvhabbanyo.setText(habbanyo);
        tvopcion.setText(opcion);
        return view;
    }



}
