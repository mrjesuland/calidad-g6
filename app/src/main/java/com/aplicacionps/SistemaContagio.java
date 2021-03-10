package com.aplicacionps;

/* El sistema de contagio se usa para inicializar y obtener el Porcentaje de contagio que va a tener
el usuario en el primer escenario de la historia jugable, con el que posteriormente se irá trasladando
este valor a los siguientes escenarios de dicha historia jugable
*/

public class SistemaContagio {
    private int Porcentaje;
    public SistemaContagio(int porcentaje) {
        this.Porcentaje = porcentaje;
    }
    public int getPorcentaje(){
        return Porcentaje;
    }

}
