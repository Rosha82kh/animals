package com.example.animals;

public class Booking extends customers{
    private int id;
    private int cageNO;
    private int weekNO;

    public Booking(int id,int cageNO, int weekNO, int numOfWeek) {
        this.id=id;
        this.cageNO = cageNO;
        this.weekNO = weekNO;
    }

    public Booking(String name, String address, String phone, int cageNO, int weekNO) {
        super(name, address, phone);
        this.id=id;
        this.cageNO = cageNO;
        this.weekNO = weekNO;
    }
    public Booking() {
        this.id=id;
        this.cageNO = cageNO;
        this.weekNO = weekNO;
    }

    public int getCageNO() {
        return cageNO;
    }

    public void setCageNO(int cageNO) {
        this.cageNO = cageNO;
    }

    public int getWeekNO() {
        return weekNO;
    }

    public void setWeekNO(int weekNO) {
        this.weekNO = weekNO;
    }
}
