package org.sample;

/**
 * Created by Randeep on 8/13/2015.
 */
public class MostFreeAttendant extends Attendant{
    public MostFreeAttendant(String name) {
        super(name);
    }

    public MostFreeAttendant() {
    }

    @Override
    public ParkingLot getFreeParkingLot() throws NoSpaceAvailable{

        if(availableParkingLots.size()!=0){
            ParkingLot maxParkingLot=new ParkingLot(0,new Owner(),new MostFreeAttendant());
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
