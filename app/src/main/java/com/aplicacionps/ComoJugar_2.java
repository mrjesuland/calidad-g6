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

public class ComoJugar_2 extends Fragment {
    public ComoJugar_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Relacionamos la clase ComoJugar_2 con su respectivo XML fragment_ComoJugar_2
        return inflater.inflate(R.layout.fragment_comojugar_2, container, false);
    }

    //Implementacion de boton que lleva de un fragmento a otro fragmento (ComoJugar_3)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton boton_comosejuega2 = view.findViewById(R.id.boton_comosejuega2);
        boton_comosejuega2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.ComoJugar2);
            }
        });
    }
}