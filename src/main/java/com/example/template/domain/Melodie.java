package com.example.template.domain;

import java.util.Objects;

public class Melodie {
    private int id;
    private String formatie;
    private String titlu;
    private String gen;
    private String durata;

    public Melodie(int id, String formatie, String titlu, String gen, String durata) {
        this.id = id;
        this.formatie = formatie;
        this.titlu = titlu;
        this.gen = gen;
        this.durata = durata;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormatie() {
        return formatie;
    }

    public void setFormatie(String formatie) {
        this.formatie = formatie;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Melodie melodie = (Melodie) o;
        return id == melodie.id && Objects.equals(formatie, melodie.formatie) && Objects.equals(titlu, melodie.titlu) && Objects.equals(gen, melodie.gen) && Objects.equals(durata, melodie.durata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formatie, titlu, gen, durata);
    }

    @Override
    public String toString(){
        return id + "; " + formatie + "; "+titlu + "; " + gen + "; " +durata;
    }
}
