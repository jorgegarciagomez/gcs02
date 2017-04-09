package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusqAvFragment extends Fragment {


    DataPassListenerBAv mCallback;
    public interface DataPassListenerBAv{
        public void passDataBAv(String[] data);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Make sure that container activity implement the callback interface
        try {
            mCallback = (DataPassListenerBAv)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DataPassListenerBAv");
        }
    }

    public BusqAvFragment() {
        // Required empty public constructor
    }



    private AutoCompleteTextView autoc;
    //private  CheckBox checkLocOp1;
    //private  CheckBox checkLocOp2;
    private Button buscarButton;
    private Spinner spinnerOpcion;
    private EditText editTextPrecioMin;
    private EditText editTextPrecioMax;
    private EditText editTextTamMin;
    private EditText editTextTamMax;
    private EditText editTextHabitaciones;
    private EditText editTextBanyos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_busq_av, container, false);


        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Búsqueda Avanzada");

        autoc=(AutoCompleteTextView) view.findViewById(R.id.autoCompleteLocBAv);
        String[] ciudades={"San Vicente","Benidorm","La vila","Calpe","Alicante","Madrid"};
        ArrayAdapter adapter=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,ciudades);
        autoc.setAdapter(adapter);

        //checkLocOp1=(CheckBox) view.findViewById(R.id.checkBoxLocActual);
        //checkLocOp2=(CheckBox) view.findViewById(R.id.checkBoxLugar);


        /*
        checkLocOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLocOp1.isChecked())
                {
                    checkLocOp1.setChecked(false);
                }
            }
        });

        checkLocOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLocOp2.isChecked())
                {
                    checkLocOp2.setChecked(false);
                }
            }
        });
        */


        editTextBanyos =(EditText) view.findViewById(R.id.editTextBanyos) ;
        editTextHabitaciones =(EditText) view.findViewById(R.id.editTextHabitaciones) ;
        editTextPrecioMax =(EditText) view.findViewById(R.id.editTextPrecioMax) ;
        editTextPrecioMin =(EditText) view.findViewById(R.id.editTextPrecioMin) ;
        editTextTamMax =(EditText) view.findViewById(R.id.editTextTamMax) ;
        editTextTamMin =(EditText) view.findViewById(R.id.editTextTamMin) ;

        spinnerOpcion=(Spinner) view.findViewById(R.id.spinnerBusqAvTipo);
        buscarButton= (Button) view.findViewById(R.id.buttonBusqAvBuscar);

        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Aquí se introducirá un if cuando se implemente la funcionalidad de pedir la localización al dispositivo
                String localizacion=autoc.getText().toString();

                String opcion=spinnerOpcion.getSelectedItem().toString();

                if(validaCampos(editTextBanyos,editTextHabitaciones,editTextPrecioMin, editTextPrecioMax,editTextTamMin, editTextTamMax))
                {
                String banyos=editTextBanyos.getText().toString();
                String habitaciones=editTextHabitaciones.getText().toString();
                String pmin=editTextPrecioMin.getText().toString();
                String pmax=editTextPrecioMax.getText().toString();
                String tmin=editTextTamMin.getText().toString();
                String tmax=editTextTamMax.getText().toString();

                    String[] datos = new String[8];
                    datos[0] = localizacion;
                    datos[1] = opcion;
                    datos[2] = banyos;
                    datos[3] = habitaciones;
                    datos[4] = pmin;
                    datos[5] = pmax;
                    datos[6] = tmin;
                    datos[7] = tmax;


                    mCallback.passDataBAv(datos);
                } else
                {
                    Toast.makeText(getActivity(), "Faltan campos por rellenar", Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    public boolean validaCampos(EditText b, EditText h, EditText pmin, EditText pmax, EditText tmin, EditText tmax)
    {
        boolean rellenados=true;


        if(b.getText().toString().equals("") || h.getText().toString().equals("") || pmin.getText().toString().equals("")
                || pmax.getText().toString().equals("") || tmin.getText().toString().equals("") || tmax.getText().toString().equals(""))
        {
            rellenados=false;
        }



        return rellenados;

    }

}
