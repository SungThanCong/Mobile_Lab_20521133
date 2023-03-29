package com.example.mobile_lab_20521133.Model;

public class Person {
    private String HoTen;
    private double Luong;

    public Person(){

    }

    public Person(String hoTen, double luong){
        this.HoTen = hoTen;
        this.Luong = luong;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double luong) {
        Luong = luong;
    }
}
