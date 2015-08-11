package org.sample;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLotTest {

    @Test
    public  void shouldTestCarParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10);

        Assert.assertTrue("Can Park",parkingLot.canPark(car));

    }
    @Test
    public  void shouldTestCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0);

        Assert.assertFalse("Cannot Park",parkingLot.canPark(car));

    }


}