package com.example.dam.lego;

/**
 * Created by dam on 31/1/17.
 */

public class Prueba {
    private String last_modified_dt;
    private String set_num;

    public Prueba() {
    }

    public String getLast_modified_dt() {
        return last_modified_dt;
    }

    public void setLast_modified_dt(String last_modified_dt) {
        this.last_modified_dt = last_modified_dt;
    }

    public String getSet_num() {
        return set_num;
    }

    public void setSet_num(String set_num) {
        this.set_num = set_num;
    }

    @Override
    public String toString() {
        return "Prueba{" +
                "last_modified_dt='" + last_modified_dt + '\'' +
                ", set_num='" + set_num + '\'' +
                '}';
    }
}
