package com.example.gio10.lospaisesdelmundo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pais implements Parcelable {

    private String nombre;
    private String capital;
    private int nHabitantes;
    private String continente;
    private String idiomaOficial;
    private String moneda;
    private String tipoGobierno;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getnHabitantes() {
        return nHabitantes;
    }

    public void setnHabitantes(int nHabitantes) {
        this.nHabitantes = nHabitantes;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getIdiomaOficial() {
        return idiomaOficial;
    }

    public void setIdiomaOficial(String idiomaOficial) {
        this.idiomaOficial = idiomaOficial;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipoGobierno() {
        return tipoGobierno;
    }

    public void setTipoGobierno(String tipoGobierno) {
        this.tipoGobierno = tipoGobierno;
    }

    public Pais(String nombre, String cap, int nHab, String cnt, String idioma, String moned, String tGob) {
        this.nombre = nombre;
        capital = cap;
        nHabitantes = nHab;
        continente = cnt;
        idiomaOficial = idioma;
        moneda = moned;
        tipoGobierno = tGob;
    }

    private Pais(Parcel in) {
        nombre= in.readString();
        capital = in.readString();
        nHabitantes = in.readInt();
        continente = in.readString();
        idiomaOficial = in.readString();
        moneda = in.readString();
        tipoGobierno = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(capital);
        parcel.writeInt(nHabitantes);
        parcel.writeString(continente);
        parcel.writeString(idiomaOficial);
        parcel.writeString(moneda);
        parcel.writeString(tipoGobierno);
    }

    public static final Parcelable.Creator<Pais> CREATOR
            = new Parcelable.Creator<Pais>() {
        public Pais createFromParcel(Parcel in) {
            return new Pais(in);
        }

        public Pais[] newArray(int size) {
            return new Pais[size];
        }
    };

}
