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
import android.widget.ImageView;

public class MenuDesafios extends Fragment {
    public MenuDesafios() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menudesafios, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView titulo_desafiobronce01 = view.findViewById(R.id.titulo_desafiobronce01);
        ImageView titulo_desafiobronce02 = view.findViewById(R.id.titulo_desafiobronce02);
        ImageView titulo_desafiobronce03 = view.findViewById(R.id.titulo_desafiobronce03);
        ImageView titulo_desafiobronce04 = view.findViewById(R.id.titulo_desafiobronce04);
        ImageView titulo_desafioplata01 = view.findViewById(R.id.titulo_desafioplata01);
        ImageView titulo_desafioplata02 = view.findViewById(R.id.titulo_desafioplata02);
        ImageView titulo_desafioplata03 = view.findViewById(R.id.titulo_desafioplata03);
        ImageView titulo_desafioplata04 = view.findViewById(R.id.titulo_desafioplata04);
        ImageView titulo_desafiooro01 = view.findViewById(R.id.titulo_desafiooro01);
        ImageView titulo_desafiooro02 = view.findViewById(R.id.titulo_desafiooro02);
        ImageButton btnVolver = view.findViewById(R.id.btnVolver);
        //Implementacion de boton que lleva de un fragmento a otro fragment
        titulo_desafiobronce01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bronce01);
            }
        });
        titulo_desafiobronce02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bronce02);
            }
        });
        titulo_desafiobronce03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bronce03);
            }
        });
        titulo_desafiobronce04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bronce04);
            }
        });
        titulo_desafioplata01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.plata01);
            }
        });
        titulo_desafioplata02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.plata02);
            }
        });
        titulo_desafioplata03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.plata03);
            }
        });
        titulo_desafioplata04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.plata04);
            }
        });
        titulo_desafiooro01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.oro01);
            }
        });
        titulo_desafiooro02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.oro02);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.menuInicio);
            }
        });
    }
}