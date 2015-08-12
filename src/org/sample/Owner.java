package org.sample;

import java.util.ArrayList;

/**
 * Created by Randeep on 8/12/2015.
 */
public class Owner implements Observer {

    private String name;
    private ArrayList<ParkingLot> parkingLots=new ArrayList<>();
    public Owner(){}
    public Owner(String name) {
        this.name = name;
    }
    public void createParkingLot(ParkingLot parkingLot){
        parkingLots.add(parkingLot);
    }


    @Override
    public void notification(ParkingLot parkingLot) {

    }
}
