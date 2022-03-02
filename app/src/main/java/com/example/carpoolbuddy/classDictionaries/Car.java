package com.example.carpoolbuddy.classDictionaries;

import java.util.ArrayList;

public class Car extends Vehicle{



    public Car(){

    }


    public Car(String locationString, String modelString, ArrayList<String> userID, int rating, int fakeCapacity, String ownerString, String txtSpinner2) {
        super(locationString, modelString, userID, fakeCapacity, rating, ownerString, txtSpinner2);


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
