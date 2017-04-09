package com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.UsuarioDTO;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;


public class RegistroFragment extends Fragment {

    private EditText editTextEmail, editTextNombre, editTextApellidos, editTextTelefono, editTextPassword, editTextRepitePassword;
    private Button buttonRegistro;
    private UsuarioDTO usuarioDTO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_registro, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Registro");

        usuarioDTO = new UsuarioDTO();
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextNombre = (EditText) view.findViewById(R.id.editTextNombre);
        editTextApellidos = (EditText) view.findViewById(R.id.editTextApellidos);
        editTextTelefono = (EditText) view.findViewById(R.id.editTextTelefono);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        editTextRepitePassword = (EditText) view.findViewById(R.id.editTextRepitePassword);
        buttonRegistro = (Button) view.findViewById(R.id.buttonRegistro);
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    usuarioDTO.newUsuario(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    ((MainActivity) getActivity()).setFragment("login");
                }
            }
        });

        editTextRepitePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEND) {
                    if(isValid()) {
                        usuarioDTO.newUsuario(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        ((MainActivity) getActivity()).setFragment("login");
                    }
                }
                return false;
            }
        });

        return view;
    }

    private boolean isValid() {
        if(editTextEmail.getText().toString().trim().length() == 0 ||
           editTextNombre.getText().toString().trim().length() == 0 ||
           editTextApellidos.getText().toString().trim().length() == 0 ||
           editTextPassword.getText().toString().trim().length() == 0 ||
           editTextRepitePassword.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Te has dejado campos sin completar", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(editTextTelefono.getText().toString().trim().length() != 9) {
            Toast.makeText(getActivity().getApplicationContext(), "Introduce un telefono correcto", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!editTextPassword.getText().toString().trim().equals(editTextRepitePassword.getText().toString().trim())) {
            Toast.makeText(getActivity().getApplicationContext(), "Los password no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
