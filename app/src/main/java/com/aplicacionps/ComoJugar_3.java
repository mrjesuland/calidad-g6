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

public class ComoJugar_3 extends Fragment {
    public ComoJugar_3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Relacionamos la clase ComoJugar_3 con su respectivo XML fragment_ComoJugar_4
        return inflater.inflate(R.layout.fragment_comojugar_3, container, false);
    }

    //Implementacion de boton que lleva de un fragmento a otro fragmento (ComoJugar_4)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton boton_comojugar3 = view.findViewById(R.id.boton_comojugar3);
        boton_comojugar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.comojugar3);
            }
        });
    }
}