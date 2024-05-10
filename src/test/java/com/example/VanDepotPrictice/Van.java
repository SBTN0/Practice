package com.example.VanDepotPrictice;
import java.time.LocalDate;
import java.util.Scanner;

public class Van extends VanArray{
    Scanner scan = new Scanner(System.in);
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
        while (num < 0 || findVanByNum(num) != null) {
            try {
                if (num < 0) {
                    throw new IllegalArgumentException("\nЗаводской номер вагона не может быть отрицательным!");
                }
                if (findVanByNum(num) != null) {
                    throw new IllegalArgumentException("\nВагон с таким номером уже существует!");
                }
            } catch (IllegalArgumentException e) {
                System.out.print("\u001B[31m" + e.getMessage());
                System.out.print("\u001B[0m\nВведите значение повторно: ");
                num = scan.nextInt();
            }
        }
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
        while (weight < 0) {
            try {
                throw new IllegalArgumentException("\nГрузоподъемность вагона не может быть отрицательной!");
            } catch (IllegalArgumentException e) {
                System.out.print("\u001B[31m" + e.getMessage());
                System.out.print("\u001B[0m\nВведите значение повторно: ");
                weight = scan.nextInt();
            }
        }
        this.weight = weight;
    }


    public void setYear(short year) {
        while (year < 1801 || year > LocalDate.now().getYear()) {
            try {
                throw new IllegalArgumentException("\nНекорректное значение года выпуска!");
            } catch (IllegalArgumentException e) {
                System.out.print("\u001B[31m" + e.getMessage()); System.out.flush();
                System.out.print("\u001B[0m\nВведите значение повторно: ");
                year = scan.nextShort();
            }
        }
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
        while (cargo <= 0 || this.load + cargo > this.weight) {
            try {
                if (cargo <= 0) {
                    throw new IllegalArgumentException("\nГруз не может быть отрицательным или равным 0!");
                }
                if (this.load + cargo > this.weight) {
                    throw new IllegalArgumentException("\nПереполнение вагона! Максимальный вес: " + this.weight);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage());
                System.out.print("\u001B[0m\nВведите значение повторно: ");
                cargo = scan.nextShort();
            }
        }
        this.load += cargo;
    }


    public void unloadVan(int cargo){
        while (cargo <= 0 || this.load - cargo < 0) {
            try {
                if (cargo <= 0) {
                    throw new IllegalArgumentException("\nГруз не может быть отрицательным или равным 0!");
                }
                if (this.load - cargo < 0) {
                    throw new IllegalArgumentException("\nВ вагоне нет столько груза! Текущий груз: " + this.load);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage());
                System.out.print("\u001B[0m\nВведите значение повторно: ");
                cargo = scan.nextShort();
            }
        }
        this.load += cargo;
    }

    public void reStation(String station) {
        this.station = station;
    }

    public String toString(){
        return "Название вагона- " + this.name + "\nЗаводской номер вагона- " + this.num +
                "\nТип вагона- " + this.type + "\nГрузоподъемность вагона (тонны)- " + this.weight +
                "\nГод выпуска- " + this.year + "\nЗагрузка вагона (%)- " + this.load + "\nCтанция приписки- " + this.station;
    }
}

