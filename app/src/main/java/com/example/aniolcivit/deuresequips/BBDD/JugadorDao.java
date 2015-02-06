package com.example.aniolcivit.deuresequips.BBDD;



import android.graphics.Bitmap;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "JUGADORS")



public class JugadorDao {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    private String equip;
    @DatabaseField
    private String valoracio;
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    byte[] foto;
    @DatabaseField
    private Boolean personalitzada;
    @DatabaseField
    private String name;



    public JugadorDao(){}
    public int getId(){
        return id;

    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getValoracio() {
        return valoracio;
    }

    public void setValoracio(String valoracio) {
        this.valoracio = valoracio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
        this.personalitzada=true;
    }

    public Boolean getPersonalitzada() {
        return personalitzada;
    }

    public void setPersonalitzada(Boolean personalitzada) {
        this.personalitzada = personalitzada;
    }

    public JugadorDao(String name, String valoracio,String equip, byte[] foto, Boolean personalitzada, int id ) {
        this.name = name;
        this.valoracio = valoracio;
        this.equip=equip;
        this.foto = foto;
        this.personalitzada=personalitzada;
        this.id = id;

    }
}