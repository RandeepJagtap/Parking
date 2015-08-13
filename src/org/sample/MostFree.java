package org.sample;

import java.util.List;

/**
 * Created by Randeep on 8/13/2015.
 */
public class MostFree implements SearchCriteria{

    @Override
    public ParkingLot getFreeParkingLot(List<ParkingLot> availableParkingLots) throws NoSpaceAvailable{

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
}
