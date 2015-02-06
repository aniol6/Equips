package com.example.aniolcivit.deuresequips.BotonsiLlistes;

import android.graphics.Bitmap;

/**
 * Created by Aniol Civit on 30/01/2015.
 */
public class Jugador {
    public Bitmap foto;
    public String name;
    public String val;
    public Boolean personalitzada;

    public Jugador(String name,String val) {
        this.name=name;
        this.val = val;
        this.personalitzada = false;

    }


    public void setFoto(Bitmap photo){
        this.foto=photo;
        this.personalitzada=true;
    }


}
