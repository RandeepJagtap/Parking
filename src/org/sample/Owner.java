package org.sample;

import java.util.ArrayList;

/**
 * Created by Randeep on 8/12/2015.
 */
public class Owner {

    private String name;
    private ArrayList<ParkingLot> parkingLots=new ArrayList<>();

    public Owner(String name) {
        this.name = name;
    }
    public void createParkingLot(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }

    public Boolean notifyParkingIsFull(ParkingLot parkingLot) {
        return true;
    }

    public Boolean notifyParkingAvailable(ParkingLot parkingLot) {
        return false;
    }
}
