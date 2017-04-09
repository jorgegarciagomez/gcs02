package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.PisoDTO;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;

public class NuevoAnuncioFragment extends Fragment {

    private Spinner spinnerTipoVivienda;
    private AutoCompleteTextView autoCompleteTextViewLugar;
    private RadioGroup radioGroupDisponibilidad;
    private EditText editTextPrecio, editTextDescripcion, editTextMetros;
    private SeekBar seekBarBaños;
    private TextView textViewBaños;
    private String opcion;
    private Button buttonNuevoAnuncio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_nuevo_anuncio, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Nuevo anuncio");

        spinnerTipoVivienda = (Spinner) view.findViewById(R.id.spinnerTipoVivienda);
        //ArrayAdapter<CharSequence> adapterTipoVivienda = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.tipo_vivienda, android.R.layout.simple_spinner_dropdown_item);
        //adapterTipoVivienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerTipoVivienda.setAdapter(adapterTipoVivienda);

        autoCompleteTextViewLugar = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextViewLugar);
        String[] countries = getResources().getStringArray(R.array.lugar_vivienda);
        ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, countries);
        autoCompleteTextViewLugar.setAdapter(adapterAutoComplete);

        radioGroupDisponibilidad = (RadioGroup) view.findViewById(R.id.radioGroupDisponibilidad);
        opcion = "alquilar";
        radioGroupDisponibilidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.radioAlquilar) {
                    opcion = "alquilar";
                } else if (i == R.id.radioCompartir) {
                    opcion = "compartir";
                } else if (i == R.id.radioComprar) {
                    opcion = "comprar";
                }
            }
        });

        textViewBaños = (TextView) view.findViewById(R.id.textViewBaños);
        editTextPrecio = (EditText) view.findViewById(R.id.editTextPrecio);
        editTextDescripcion = (EditText) view.findViewById(R.id.editTextDescripcion);
        editTextMetros = (EditText) view.findViewById(R.id.editTextMetros);
        editTextMetros.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEND) {
                    if(isValid()) {
                        PisoDTO pisoDTO = new PisoDTO();
                        int metros = Integer.parseInt(editTextMetros.getText().toString());
                        int precio = Integer.parseInt(editTextPrecio.getText().toString());
                        pisoDTO.addPiso(autoCompleteTextViewLugar.getText().toString(), opcion, spinnerTipoVivienda.getSelectedItem().toString(), precio, "",  3, seekBarBaños.getProgress(), metros, "piso1", editTextDescripcion.getText().toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Piso añadido correctamente", Toast.LENGTH_SHORT).show();
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        ((MainActivity) getActivity()).setFragment("home");
                    }
                }
                return false;
            }
        });
        seekBarBaños = (SeekBar) view.findViewById(R.id.seekBarBaños);
        seekBarBaños.setProgress(1);
        seekBarBaños.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewBaños.setText("Numero de Baños: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonNuevoAnuncio = (Button) view.findViewById(R.id.buttonNuevoAnuncio);
        buttonNuevoAnuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    PisoDTO pisoDTO = new PisoDTO();
                    int metros = Integer.parseInt(editTextMetros.getText().toString());
                    int precio = Integer.parseInt(editTextPrecio.getText().toString());
                    pisoDTO.addPiso(autoCompleteTextViewLugar.getText().toString(), opcion, spinnerTipoVivienda.getSelectedItem().toString(), precio, "",  3, seekBarBaños.getProgress(), metros, "piso1", editTextDescripcion.getText().toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Piso añadido correctamente", Toast.LENGTH_SHORT).show();
                    view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    ((MainActivity) getActivity()).setFragment("home");
                }
            }
        });

        return view;
    }

    private boolean isValid() {
        if(autoCompleteTextViewLugar.getText().toString().trim().length() == 0 ||
                editTextPrecio.getText().toString().trim().length() == 0 ||
                editTextDescripcion.getText().toString().trim().length() == 0 ||
                editTextMetros.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Te has dejado campos sin completar", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
