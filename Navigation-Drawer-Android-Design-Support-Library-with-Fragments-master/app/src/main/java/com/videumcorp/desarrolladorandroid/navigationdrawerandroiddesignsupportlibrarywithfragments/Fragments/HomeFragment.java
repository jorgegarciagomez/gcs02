package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    DataPassListener mCallback;
    public interface DataPassListener{
        public void passData(String[] data);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Make sure that container activity implement the callback interface
        try {
            mCallback = (DataPassListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DataPassListener");
        }
    }

    private AutoCompleteTextView autoc;
    private RadioGroup opciones;
    private  Spinner spinnerVivienda;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("ViviendasAPP");



        opciones = (RadioGroup) view.findViewById(R.id.radiogroup_opciones);



        spinnerVivienda=(Spinner) view.findViewById(R.id.spinnerVivienda);


        autoc=(AutoCompleteTextView) view.findViewById(R.id.autoCompleteLocalizacion);
        String[] ciudades={"San Vicente","Benidorm","La vila","Calpe","Alicante","Madrid"};
        ArrayAdapter adapter=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,ciudades);
        autoc.setAdapter(adapter);

         Button buscarBoton=(Button) view.findViewById(R.id.buttonBuscar);
        buscarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String localizacion=autoc.getText().toString();
                int chequeado = opciones.getCheckedRadioButtonId();
                String vivienda=spinnerVivienda.getSelectedItem().toString();
                String opcion="";
                switch(chequeado) {
                    case R.id.radio_alquilar: opcion="alquilar";
                        break;
                    case R.id.radio_comprar: opcion="comprar";
                        break;
                    case R.id.radio_compartir: opcion="compartir";
                        break;
                    default: opcion="No seleccionada";
                        break;
                }

                String[] datos=new String[3];
                datos[0]=localizacion;
                datos[1]=vivienda;
                datos[2]=opcion;
                mCallback.passData(datos);



            }
        });

        Button busqAvanzadaBot=(Button) view.findViewById(R.id.buttonBusAv);

        busqAvanzadaBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager;
                FragmentTransaction fragmentTransaction;
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                BusqAvFragment busqAvFragment = new BusqAvFragment();
                fragmentTransaction.replace(R.id.fragment, busqAvFragment);
                fragmentTransaction.commit();
            }
        });

        Button nuevoAnuncionButton=(Button) view.findViewById(R.id.buttonNuevoAnuncio);
        nuevoAnuncionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment("nuevo_anuncio");
            }
        });

        return view;
    }



}
