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

        Assert.assertNotNull("Can Park", parkingLot.park(car));

    }
    @Test
         public  void shouldTestCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0);

        Assert.assertNull("Cannot Park",parkingLot.park(car));

    }
    @Test
    public  void shouldTestCarUnParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10);

        Assert.assertNotNull("Can UnPark", parkingLot.unPark(parkingLot.park(car)));

    }
    @Test
    public  void shouldTestCarUnParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0);

        Assert.assertNull("Cannot UnPark",parkingLot.unPark(new Ticket(200,300)));

    }


}