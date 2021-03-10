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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComoJugar_1#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ComoJugar_1 extends Fragment {
    public ComoJugar_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Relacionamos la clase ComoJugar_1 con su respectivo XML fragment_ComoJugar_1
        return inflater.inflate(R.layout.fragment_comojugar_1, container, false);
    }

    //implementacion de boton que lleva de un fragmento a otro fragmento (ComoJugar_2)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton btnsig = view.findViewById(R.id.boton_comosejuega1);
        btnsig.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.ComoJugar_1);
            }
        });
    }
}