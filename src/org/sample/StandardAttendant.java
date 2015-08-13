package org.sample;

/**
 * Created by Randeep on 8/13/2015.
 */
public class StandardAttendant extends Attendant{
    public StandardAttendant(String name) {
        super(name);
    }

    public StandardAttendant() {
    }

    @Override
    public ParkingLot getFreeParkingLot() throws NoSpaceAvailable{
        if(availableParkingLots.size()!=0){
            return availableParkingLots.get(0);}
        else {
            throw new NoSpaceAvailable("No Space Available");
        }
    }
}
