package com.example.carpoolbuddy.classDictionaries;

import java.util.ArrayList;

public class ElectricCar extends Vehicle {



    public ElectricCar(){

    }

    public ElectricCar(String locationString, String modelString, ArrayList<String> userIDInput, int capacity, int rating, String ownerString, String spinner2String) {
        super(locationString, modelString, userIDInput, capacity, rating, ownerString, spinner2String);
    }


    @Override
    public String getLocation() {
        return super.getLocation();
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    @Override
    public int getRating() {
        return super.getRating();
    }

    @Override
    public String getVehicleOwner(){
        return super.getVehicleOwner();
    }

    @Override
    public String getVehicleType(){
        return super.getVehicleOwner();
    }

}
