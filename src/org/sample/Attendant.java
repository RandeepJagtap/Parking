package org.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Randeep on 8/13/2015.
 */
public class Attendant implements Observer{
    String name;
    List<ParkingLot> availableParkingLots = new ArrayList<ParkingLot>();
    List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    public Attendant(){}
    public Attendant(String name) {
        this.name = name;
    }
    public ParkingLot getFreeParkingLot(SearchCriteria searchCriteria) throws NoSpaceAvailable{

        return searchCriteria.getFreeParkingLot(availableParkingLots);
    }





    public void assignParkingLot(ParkingLot parkingLot){
        availableParkingLots.add(parkingLot);
        parkingLots.add(parkingLot);
    }

    @Override
    public void notification(ParkingLot parkingLot,NotificationType type) {
        if(type==NotificationType.PARKINGFULL){
                availableParkingLots.remove(parkingLot);

        }
        else if(type==NotificationType.PARKINGAVAILABLE){
                availableParkingLots.add(parkingLot);
        }
    }
}
