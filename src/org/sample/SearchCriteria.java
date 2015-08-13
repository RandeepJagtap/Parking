package org.sample;

import java.util.List;

/**
 * Created by Randeep on 8/13/2015.
 */
public interface SearchCriteria {
    public ParkingLot getFreeParkingLot(List<ParkingLot> list) throws NoSpaceAvailable;
}
