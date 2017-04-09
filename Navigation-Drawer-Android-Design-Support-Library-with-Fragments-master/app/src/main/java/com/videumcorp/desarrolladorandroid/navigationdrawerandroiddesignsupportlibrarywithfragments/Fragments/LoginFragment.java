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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.DTO.UsuarioDTO;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.MainActivity;
import com.videumcorp.desarrolladorandroid.navigationdrawerandroiddesignsupportlibrarywithfragments.R;


public class LoginFragment extends Fragment {

    //private ImageView imageViewLogo;
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Login");

        //imageViewLogo = (ImageView) view.findViewById(R.id.imageViewLogo);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Comprobamos el usuario
                if (editTextEmail.getText().toString().trim().length() == 0 || editTextPassword.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "Usuario y/o Password invalidos", Toast.LENGTH_SHORT).show();
                } else if(!usuarioDTO.isValid(editTextEmail.getText().toString(), editTextPassword.getText().toString())) {
                    //Toast.makeText(getActivity().getApplicationContext(), "Usuario valido: " + usuarioDTO.isValid(editTextEmail.getText().toString(), editTextPassword.getText().toString()), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity().getApplicationContext(), "Login incorrecto", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity().getApplicationContext(), "Login OK", Toast.LENGTH_SHORT).show();
                    view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    // Cambiamos el navigationDrawer
                    ((MainActivity) getActivity()).setEmailUsuarioLogin(editTextEmail.getText().toString().trim());
                    ((MainActivity) getActivity()).setNavigationDrawer();
                    ((MainActivity) getActivity()).setFragment("home");
                }
            }
        });

        editTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEND) {
                    // Comprobamos el usuario
                    if (editTextEmail.getText().toString().trim().length() == 0 || editTextPassword.getText().toString().trim().length() == 0) {
                        Toast.makeText(getActivity().getApplicationContext(), "Email y/o Password invalidos", Toast.LENGTH_SHORT).show();
                    } else if(!usuarioDTO.isValid(editTextEmail.getText().toString(), editTextPassword.getText().toString())) {
                        //Toast.makeText(getActivity().getApplicationContext(), "Usuario valido: " + usuarioDTO.isValid(editTextEmail.getText().toString(), editTextPassword.getText().toString()), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity().getApplicationContext(), "Login incorrecto", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getActivity().getApplicationContext(), "Login OK", Toast.LENGTH_SHORT).show();
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                        // Cambiamos el navigationDrawer
                        ((MainActivity) getActivity()).setEmailUsuarioLogin(editTextEmail.getText().toString().trim());
                        ((MainActivity) getActivity()).setNavigationDrawer();
                        ((MainActivity) getActivity()).setFragment("home");
                    }
                }
                return false;
            }
        });
        return view;
    }

}
