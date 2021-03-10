package com.aplicacionps;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class ComoJugar_4 extends Fragment {
    public ComoJugar_4() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Relacionamos la clase ComoJugar_4 con su respectivo XML fragment_menuInicio
        return inflater.inflate(R.layout.fragment_comojugar_4, container, false);
    }

    //implementacion de boton que lleva de un fragmento a otro fragmento (menuInicio)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton boton_comojugar4 = view.findViewById(R.id.boton_comojugar4);
        //implementacion de boton que lleva de un fragmento a otro fragment
        boton_comojugar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.menuInicio);
            }
        });
    }
}