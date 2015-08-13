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

    public ParkingLot getFreeParkingLot() throws NoSpaceAvailable{
        if(availableParkingLots.size()!=0){
        return availableParkingLots.get(0);}
        else {
            throw new NoSpaceAvailable("No Space Available");
        }
    }
    public ParkingLot getParkingLotWithMostFreeSpace() throws NoSpaceAvailable{
        if(availableParkingLots.size()!=0){
            ParkingLot maxParkingLot=new ParkingLot(0,new Owner(),new Attendant());
            for(ParkingLot tempParkingLot:availableParkingLots){
                if(maxParkingLot.freeSpaces()<tempParkingLot.freeSpaces()){
                    maxParkingLot=tempParkingLot;
                }
            }

            return maxParkingLot;
        }
        else {
            throw new NoSpaceAvailable("No Space Available");
        }
    }

//    public Ticket parkInMax(Car car){
//
//        ParkingLot parkingLot=getParkingLotWithMostFreeSpace();
//        return parkingLot.park(car);
//    }


    public void assignParkingLot(ParkingLot parkingLot){
        availableParkingLots.add(parkingLot);
        parkingLots.add(parkingLot);
    }

    @Override
    public void notification(ParkingLot parkingLot,NotificationType type) {
        if(type==NotificationType.PARKINGFULL){
                availableParkingLots.remove(parkingLot);
                System.out.print("Removed one");
        }
        else if(type==NotificationType.PARKINGAVAILABLE){
                availableParkingLots.add(parkingLot);
        }
    }
}
