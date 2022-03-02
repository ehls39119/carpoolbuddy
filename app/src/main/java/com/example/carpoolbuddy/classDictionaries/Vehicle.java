package com.example.carpoolbuddy.classDictionaries;

import java.util.ArrayList;

public class Vehicle {
    String location;
    String model;
    int capacityAvailable;
    int rating;
    String vehicleOwner;
    String vehicleType;
    ArrayList<String> riderIDs = new ArrayList<>();



    public Vehicle(String locationString, String modelString, ArrayList<String> passengers, int capacityInput, int ratingInput, String ownerString, String spinnerInput) {
        location = locationString;
        model = modelString;
        capacityAvailable = capacityInput;
        rating = ratingInput;
        vehicleOwner = ownerString;
        vehicleType = spinnerInput;
        riderIDs = passengers;
    }

    public Vehicle(){

    }


    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacityAvailable;
    }

    public int getRating() {
        return rating;
    }

    public String getVehicleOwner(){
        return vehicleOwner;
    }

    public String getVehicleType(){
        return vehicleType;
    }

    public ArrayList<String> getRiderIDs(){
        return riderIDs;
    }


}

