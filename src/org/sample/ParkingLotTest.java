package org.sample;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Randeep on 8/11/2015.
 */
public class ParkingLotTest {

    @Test
    public  void shouldTestCarParking() {

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10);

        Assert.assertTrue("Can Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test(expected = ParkingFullException.class)
         public  void shouldTestCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0);

        Assert.assertFalse("CanNot Park", parkingLot.park(car) instanceof Ticket);

    }
    @Test
    public  void shouldTestDuplicateCarParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5);
        Ticket ticket=parkingLot.park(car);
        Assert.assertEquals(ticket, parkingLot.park(car));

    }
    @Test
    public  void shouldTestDuplicateCarParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(5);
        Ticket ticket=parkingLot.park(car);
        Ticket ticket1=new Ticket(12,12);
        Assert.assertNotSame(ticket1, parkingLot.park(car));

    }
    @Test
    public  void shouldTestCarUnParking(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(10);

        Assert.assertTrue("Can UnPark", parkingLot.unPark(parkingLot.park(car)).equals(car));

    }
    @Test(expected =NoSuchCarParkedException.class)
    public  void shouldTestCarUnParkingFail(){

        Car car=new Car();
        ParkingLot parkingLot=new ParkingLot(0);

        Assert.assertFalse("Cannot UnPark", parkingLot.unPark(new Ticket(200, 300)).equals(car));

    }

    @Test
    public void shouldTestParkingFull(){
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        parkingLot.park(car);
        Assert.assertTrue(parkingLot.isFull());
    }

    @Test
    public void shouldTestParkingNotFull(){
        ParkingLot parkingLot = new ParkingLot(1);;
        Car car = new Car();
        parkingLot.unPark(parkingLot.park(car));

        Assert.assertFalse(parkingLot.isFull());
    }


}