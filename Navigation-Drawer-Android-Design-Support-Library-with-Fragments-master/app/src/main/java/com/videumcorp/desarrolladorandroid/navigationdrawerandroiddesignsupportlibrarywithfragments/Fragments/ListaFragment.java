package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Adapters.PisosAdapter;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.Piso;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.PisoDTO;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {


    DataPassListenerPiso mCallback;
    public interface DataPassListenerPiso{
        public void passDataPiso(Piso piso);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Make sure that container activity implement the callback interface
        try {
            mCallback = (DataPassListenerPiso)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DataPassListener");
        }
    }
    public ListaFragment() {
        // Required empty public constructor
    }

    ListView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         final View view = inflater.inflate(R.layout.fragment_lista, container, false);
        PisoDTO pdto = new PisoDTO();

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Resultados de búsqueda");

        Bundle args = getArguments();
        String[] datos=args.getStringArray("datos");

        if(datos.length == 3) {
            String loc = datos[0];
            String viv = datos[1];
            String op = datos[2];

            //viv = viv.toLowerCase();
            //op = op.toLowerCase();

            lista = (ListView) view.findViewById(R.id.listBusqueda);

            List<Piso> pisos = pdto.busquedaPisos(loc, op, viv);

            lista.setAdapter(new PisosAdapter(getActivity().getApplicationContext(), pisos));

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapter, View view, int position, long arg) {


                    Piso piso = (Piso) lista.getAdapter().getItem(position);
                    mCallback.passDataPiso(piso);
                    //Toast.makeText(getActivity().getApplicationContext(), piso.getNombre(), Toast.LENGTH_LONG).show();
                }
            });
        } else
        {
            String loc = datos[0];
            String op = datos[1];
            int banyos=Integer.parseInt(datos[2]);
            int hab=Integer.parseInt(datos[3]);
            int pmin=Integer.parseInt(datos[4]);
            int pmax=Integer.parseInt(datos[5]);
            int tmin=Integer.parseInt(datos[6]);
            int tmax=Integer.parseInt(datos[7]);

            op = op.toLowerCase();

            lista = (ListView) view.findViewById(R.id.listBusqueda);

            List<Piso> pisos = pdto.busquedaPisosAv(loc, op, banyos, hab, pmin, pmax, tmin,  tmax);

            lista.setAdapter(new PisosAdapter(getActivity().getApplicationContext(), pisos));

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapter, View view, int position, long arg) {


                    Piso piso = (Piso) lista.getAdapter().getItem(position);
                    mCallback.passDataPiso(piso);
                    //Toast.makeText(getActivity().getApplicationContext(), piso.getNombre(), Toast.LENGTH_LONG).show();
                }
            });
        }

        //Toast.makeText(getActivity().getApplicationContext(), viv, Toast.LENGTH_LONG).show();
        return view;
    }

}
