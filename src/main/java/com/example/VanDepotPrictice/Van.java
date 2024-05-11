package com.example.VanDepotPrictice;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Van{
    //Scanner scan = new Scanner(System.in);
    private String name;
    private int num;
    private String type;
    private int weight;
    private short year;
    private int load;
    private String station;

    public Van(String name, int num, String type, int weight, short year, int load, String station) {
        setName(name);
        setNum(num);
        setType(type);
        setWeight(weight);
        setYear(year);
        loadVan(load);
        setStation(station);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        if (num <= 0) throw new IllegalArgumentException("Номер не может отрицательным или равным 0");
        this.num = num;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (weight <= 0) throw new IllegalArgumentException("Грузоподъёмность не может быть отрицательной или равной 0");
        this.weight = weight;
    }


    public void setYear(short year) {
        if (year > Year.now().getValue()) throw new IllegalArgumentException("Вы что из будущего?");
        if (year < 1830) throw new IllegalArgumentException("Первый вагон был создан только в 1830 году");
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getLoad() {
        return load;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void loadVan(int cargo) {
        if (cargo < 0) throw new IllegalArgumentException("Неверное значение загрузки");
        if (this.getLoad() + cargo > this.getWeight()) throw new IllegalArgumentException("Грузоподъемность вагона превышена. Загруженость вагона: " + this.getLoad());
        this.load += cargo;
    }


    public void unloadVan(int cargo){
        if (cargo < 0) throw new IllegalArgumentException("Неверное значение загрузки");
        if (this.getLoad() - cargo < 0) throw new IllegalArgumentException("В вагоне нет столько груза. Загруженость вагона: " + this.getLoad());
        this.load -= cargo;
    }

    public void reStation(String station) {
        if (station.equals(this.getStation())) throw new IllegalArgumentException("Вагон уже приписан к этой станции");
        this.station = station;
    }

    public String toString(){
        return "Название вагона- " + this.name + "\nЗаводской номер вагона- " + this.num +
                "\nТип вагона- " + this.type + "\nГрузоподъемность вагона (тонны)- " + this.weight +
                "\nГод выпуска- " + this.year + "\nЗагрузка вагона (%)- " + this.load + "\nCтанция приписки- " + this.station;
    }
}

