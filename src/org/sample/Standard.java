package org.sample;

import java.util.List;

/**
 * Created by Randeep on 8/13/2015.
 */
public class Standard implements SearchCriteria{


    @Override
    public ParkingLot getFreeParkingLot(List<ParkingLot> availableParkingLots) throws NoSpaceAvailable{
        if(availableParkingLots.size()!=0){
            return availableParkingLots.get(0);}
        else {
            throw new NoSpaceAvailable("No Space Available");
        }
    }
}
