package classes;

import interfaces.AutoIF;

public class Auto implements AutoIF {
    int id, duration;

    public Auto(int id){
        this.id = id;
    }

    public Auto(){}


    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }

}
